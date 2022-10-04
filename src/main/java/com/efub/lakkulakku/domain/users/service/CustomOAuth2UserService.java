package com.efub.lakkulakku.domain.users.service;

import com.efub.lakkulakku.domain.users.entity.OAuthAttributes;
import com.efub.lakkulakku.domain.users.entity.Users;
import com.efub.lakkulakku.domain.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	private final UsersRepository usersRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(userRequest);

		String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
		OAuthAttributes attributes = OAuthAttributes.ofGoogle(userNameAttributeName, oAuth2User.getAttributes());
		Users users = saveOrUpdate(attributes);

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