package com.efub.lakkulakku.domain.users.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

	private Map<String, Object> attributes;
	private String nameAttributeKey;
	private String nickname;
	private String email;

	@Builder
	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String nickname, String email) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.nickname = nickname;
		this.email = email;
	}

	public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes){
		return OAuthAttributes.builder()
				.nickname((String) attributes.get("nickname"))
				.email((String) attributes.get("email"))
				.attributes(attributes)
				.nameAttributeKey(userNameAttributeName)
				.build();
	}

	public Users toEntity(){
		return Users.builder()
				.nickname(nickname)
				.email(email)
				.build();
	}
}