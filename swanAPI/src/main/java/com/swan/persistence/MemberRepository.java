package com.swan.persistence;

import org.springframework.data.repository.CrudRepository;

import com.swan.model.Member;

public interface MemberRepository extends CrudRepository<Member, String>{
	
}
