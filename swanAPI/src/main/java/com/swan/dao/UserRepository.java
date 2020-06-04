package com.swan.dao;

import org.springframework.stereotype.Repository;

import com.swan.model.Member;

@Repository
public interface UserRepository {

	public Member getUserInfo(String userId);
	
	//DB에 유저 생성 테스트한 메소드
	public void insertUser(Member user1);
	
	public void init();
}
