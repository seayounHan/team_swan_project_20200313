package com.swan.dao;

import org.springframework.stereotype.Repository;

import com.swan.model.User;

@Repository
public interface UserRepository {

	public User getUserInfo(String userId);

	public void init();
}
