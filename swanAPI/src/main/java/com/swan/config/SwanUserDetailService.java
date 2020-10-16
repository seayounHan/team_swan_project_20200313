package com.swan.config;

import com.swan.model.Member;
import com.swan.model.Role;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.swan.config.LoggingRunner;

import com.swan.persistence.MemberRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
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
	
	@Autowired
	private PasswordEncoder encoder;
	
	public String save(Member newMember) {
		Gson gson = new Gson();
		JsonObject obj =new JsonObject();
		try {
			newMember.setUSER_PASSWORD(encoder.encode(newMember.getUSER_PASSWORD()));
			SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
			Date time = new Date();
			String m_crt_tm = format1.format(time);
			memberRepo.save(Member.builder()
				.USER_ID(newMember.getUSER_ID())
				.USER_PASSWORD(newMember.getUSER_PASSWORD())
				.USER_NAME(newMember.getUSER_NAME())
		        .EMAIL(newMember.getEMAIL())
		        .EMP_NUMBER(newMember.getEMP_NUMBER())
		        .PHONE(newMember.getPHONE())
		        .CRT_TM(m_crt_tm)
		        .UDT_TM(m_crt_tm)
		        .ROLE(Role.ROLE_USER)
		        .ENABLED(true)
		        .build());
			obj.addProperty("result", "OK");
		}catch(Exception e){
			System.out.println("MEMBER SAVE ERROR : ");
			System.out.println(e);
			obj.addProperty("result", "false");
		}
		
		return gson.toJson(obj);
	}	
	
	//이메일 중복확인 처리
	public boolean isDuplicateEmail(String email) throws Exception {
		Optional<Member> mem = memberRepo.findByEMAIL(email);
		if (mem.isPresent()) { // 이메일 존재
			return true;
		}
		else {
			return false;
		}
	}
	//아이디 중복확인 처리
	public boolean isDuplicateId(String id) throws Exception {
		
		System.out.println(id);
//		System.out.println(memberRepo.findAll());
		System.out.println(memberRepo.findById(id));
		System.out.println("OK");
		Optional<Member> mem = memberRepo.findById(id);
	
		if (mem.isPresent()) { // id 존재
			return true;
		}
		else {
			return false;
		}
	}

}
