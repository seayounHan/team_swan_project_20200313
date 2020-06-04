package com.swan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.swan.model.Member;
import com.swan.model.Role;
import com.swan.persistence.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Commit
public class PasswordEncoderTest {
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Test
	public void testInsert() {
		Member member = new Member();
		member.setUSER_ID("tempuser");
		member.setUSER_PASSWORD(encoder.encode("temp01"));
		member.setUSER_NAME("jy");
		member.setEMAIL("email1");
		member.setPHONE("123435234");
		member.setROLE(Role.ROLE_USER);
		member.setEMP_NUMBER("I0101111");
		memberRepo.save(member);
	}
}
