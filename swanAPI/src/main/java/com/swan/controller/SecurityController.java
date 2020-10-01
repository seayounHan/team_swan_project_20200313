package com.swan.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.swan.config.SwanUserDetailService;
import com.swan.model.Member;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Controller
@ResponseBody
public class SecurityController {
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	private final SwanUserDetailService userService;
	
	@GetMapping("/index")
	public String index() {
		System.out.println("모든사용자");
		
		return "index";
	}

	@GetMapping("/user")
	public String member() {
		System.out.println("일반사용자");
		try {
		String username = authentication.getName();
		return username;
		}catch(Exception e){
			System.out.println(e);
			return "user";
		}

//		return "user";
	}

	@GetMapping("/admin")
	public String admin() {
		System.out.println("관리자");
		return "admin";
	}
	
	@GetMapping("/test")
	public static String currentUserName() { 
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		return user.getUsername();
	}


	//로그인 화면 또는 로그인 실패시 다시 돌아오는 화면
	@GetMapping("/login")
	public String login() {
		System.out.println("login 완료");
		String username = authentication.getName();
		return (username);
	}

	//로그인 성공시 각 유저에게 보이는 화면
	@GetMapping("/userMain")
	public String userMain() {
		String info = (String)authentication.getPrincipal();
		System.out.println(info);
		System.out.println("SUSU");
		return ("userMain");
	}

	//
	@GetMapping("/accessDenied")
	public void accessDenied() {

	}

	@PostMapping("/join")
	public String signup(@RequestBody Map<String, Object> param) { // 회원 추가
		Gson gson = new Gson();
		System.out.println(param);
		String temp = param.toString();
		Member newMember = new Member();
		System.out.println(newMember);
		newMember = gson.fromJson(temp, Member.class);

		String result = userService.save(newMember);
		return result;
	}

	//이메일 중복확인 체크 요청
	@PostMapping("/emailCheck")
	public String confirmEmail(@RequestBody Map<String, Object> param) throws Exception {
		//클라이언트에 비동기 통신으로 받아줘야하니까 @ReqeustBody를 사용한다.
		Gson gson = new Gson();
		JsonObject obj =new JsonObject();

		String userEmail = param.get("USER_EMAIL").toString();
		System.out.println("중복 확인 요청된 아이디 : " + userEmail);

		//서비스 측에 요청
		boolean result = userService.isDuplicateEmail(userEmail);

		if (result == true) {
			System.out.println("아이디 사용 불가!");
			obj.addProperty("result", "NO");
		} else {
			System.out.println("아이디 사용 가능!");
			obj.addProperty("result", "OK");
		}

		return gson.toJson(obj);
	}

	//아이디 중복확인 체크 요청
	@PostMapping("/idCheck")
	public String confirmId(@RequestBody Map<String, Object> param) throws Exception {
		//클라이언트에 비동기 통신으로 받아줘야하니까 @ReqeustBody를 사용한다.
		
		Gson gson = new Gson();
		JsonObject obj =new JsonObject();

		String userId = param.get("USER_ID").toString();
		System.out.println("중복 확인 요청된 아이디 : " + userId);
		//서비스 측에 요청
		boolean result = userService.isDuplicateId(userId);
		System.out.println("결과 : " + result);
		
		if (result == true) {
			System.out.println("아이디 사용 불가!");
			obj.addProperty("result", "NO");
		} else {
			System.out.println("아이디 사용 가능!");
			obj.addProperty("result", "OK");
		}

		return gson.toJson(obj);
	}

}
