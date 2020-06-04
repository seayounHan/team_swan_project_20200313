package com.swan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swan.model.Member;

@Controller
@ResponseBody
public class SecurityController {
	
	@GetMapping("/index")
	public String index() {
		System.out.println("모든사용자");
		return "index";
	}
	
	@GetMapping("/user")
	public String member() {
		System.out.println("일반사용자");
		return "user";
	}
	
	@GetMapping("/admin")
	public String admin() {
		System.out.println("관리자");
		return "admin";
	}
	
	//로그인 화면 또는 로그인 실패시 다시 돌아오는 화면
	@GetMapping("/login")
    public String login(){
		return ("login");
    }
	//로그인 성공시 각 유저에게 보이는 화면
	@GetMapping("/userMain")
	public String userMain() {
		return("userMain");
	}
	
	//
	@GetMapping("/accessDenied")
	public void accessDenied() {
		
	}
	
	
}
