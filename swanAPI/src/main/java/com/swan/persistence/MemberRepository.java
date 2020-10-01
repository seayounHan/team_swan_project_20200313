package com.swan.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.swan.model.Member;

public interface MemberRepository extends CrudRepository<Member, String>{
	public Optional<Member> findByEMAIL(String EMAIL);
}
