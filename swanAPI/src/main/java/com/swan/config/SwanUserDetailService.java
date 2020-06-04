package com.swan.config;

import com.swan.model.Member;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.swan.config.LoggingRunner;

import com.swan.persistence.MemberRepository;

@Service
public class SwanUserDetailService implements UserDetailsService{
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		//MemberRepository로 Optional<T>회하여 userdetail 타입 객체로 리턴
		Optional<Member> optional = memberRepo.findById(username);
		if(!optional.isPresent()) {
			throw new UsernameNotFoundException(username+" 사용자 없음");
		}else {
			Member member = optional.get();
			System.out.println(member);
			return new SecurityUser(member);
		}
	}

}
