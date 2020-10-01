package com.swan.config;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.swan.model.Member;

public class SecurityUser extends User{
	
	private static final long serialVersionUID = 1L;
	
/* 생성자 호출시 검색 결과로 얻은 member 객체 값을 전달한다. */
	public SecurityUser(Member member) {
		super(member.getUSER_ID(), member.getUSER_PASSWORD(), AuthorityUtils.createAuthorityList(member.getROLE().toString()));
// {noop} 추가하면 암호화 없이 그대로 사용 가능
//		super(member.getUSER_ID(), "{noop}"+member.getUSER_PASSWORD(), AuthorityUtils.createAuthorityList("ROLE_USER".toString()));
	}
	
}
