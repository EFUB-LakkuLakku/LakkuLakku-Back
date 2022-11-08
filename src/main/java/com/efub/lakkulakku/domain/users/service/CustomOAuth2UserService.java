package com.efub.lakkulakku.domain.users.service;

import com.efub.lakkulakku.domain.users.entity.OAuthAttributes;
import com.efub.lakkulakku.domain.users.entity.Role;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	private final UsersRepository usersRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(userRequest);
		log.info(oAuth2User.getAttributes().toString());

		/* OAuth2 로그인 진행시 키가 되는 필드 값 (PK) (구글의 기본 코드는 "sub") */
		String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

		/* OAuth2UserService */
		OAuthAttributes attributes = OAuthAttributes.ofGoogle(userNameAttributeName, oAuth2User.getAttributes());
		Users users = saveOrUpdate(attributes);

		System.out.println("userRequest = " + userRequest.getAccessToken().getTokenValue());
		// 구글 로그인버튼 클릭 -> 구글 로그인창 -> 로그인을 완료 -> code 를 리턴(Oauth-client 라이브러리) -> AccessToken 요청
		// userRequest 정보 -> loadUser함수 호출 -> 구글로부터 회원프로필을 받아준다.
		System.out.println("userRequest = " + userRequest.getClientRegistration());
		System.out.println("oAuth2User = " + userRequest.getAdditionalParameters());
		System.out.println("oAuth2User.getAttributes() = " + oAuth2User.getAttributes());
		System.out.println("oAuth2User.getAuthorities() = " + oAuth2User.getAuthorities());
		System.out.println("oAuth2User.getName() = " + oAuth2User.getName());
		String provider = userRequest.getClientRegistration().getRegistrationId(); // google
		String providerId = oAuth2User.getAttribute("sub");
		String username = provider + "_" + providerId; // google_1923912312312
		String password = "dodokong";
		String email = oAuth2User.getAttribute("email");


		/*Users user = usersRepository.findByNickname();
		if (user == null) {
			System.out.println("최초 가입");
			user = AuthUsers.builder()
					.nickname(user.getNickname())
					.password(password)
					.email(email)
					.provider(provider)
					.providerId(providerId)
					.build();
			memberService.joinUserWithMember(member);
		}else {
			System.out.println("이미 가입한 적이 있습니다.");
		}*/

		return new DefaultOAuth2User(
				Collections.singleton(new SimpleGrantedAuthority(users.getRole())),
				attributes.getAttributes(),
				attributes.getNameAttributeKey()
		);
	}

	public Users saveOrUpdate(OAuthAttributes attributes) {
		if(usersRepository.findByEmail(attributes.getEmail()).isEmpty()){
			Users users = attributes.toEntity();
			return usersRepository.save(users);
		} else{
			return usersRepository.findByEmail(attributes.getEmail()).get();
		}
	}
}