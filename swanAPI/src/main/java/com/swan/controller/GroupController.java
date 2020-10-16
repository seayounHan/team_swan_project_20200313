package com.swan.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.swan.config.GroupInfoService;
import com.swan.config.SwanUserDetailService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@ResponseBody
public class GroupController {
	@Autowired
	GroupInfoService gis = new GroupInfoService();

//	@PostMapping("/registerGroup")
//	public String registerGroup(@RequestBody Map<String, Object> param) throws Exception {
//		//클라이언트에 비동기 통신으로 받아줘야하니까 @ReqeustBody를 사용한다.
//		
//		Gson gson = new Gson();
//		JsonObject obj =new JsonObject();
//		String owneruserId = param.get("OWNERUSER_ID").toString();
//		String userId = param.get("USER_ID").toString();
//		String userNm = param.get("USER_NAME").toString();
//		String phone = param.get("PHONE").toString();
//
//		//서비스 측에 요청
//		boolean result = gis.doRegisterGroup(owneruserId, userId, userNm, phone);
//		System.out.println("결과 : " + result);
//		
//		if (result == true) {
//			System.out.println("아이디 사용 불가!");
//			obj.addProperty("result", "false");
//		} else {
//			System.out.println("아이디 사용 가능!");
//			obj.addProperty("result", "true");
//		}
//
//		return gson.toJson(obj);
//	}
	
	@PostMapping("/registerGroup")
	public String registerGroup(@RequestBody Map<String, Object> param) throws Exception {
		
		Gson gson = new Gson();
		JsonObject obj =new JsonObject();
		String owneruserId = param.get("OWNERUSER_ID").toString();
		String userId = param.get("USER_ID").toString();
		String userNm = param.get("USER_NAME").toString();
		String phone = param.get("PHONE").toString();
		System.out.println("OWNERUSER_ID : ");
		System.out.println(owneruserId);
		
		//서비스 측에 요청
		String result = gis.save(owneruserId, userId, userNm, phone);
		System.out.println("결과 : " + result);
		if (result =="true") {
			obj.addProperty("result", "true");
		}
		else {
			obj.addProperty("result", "false");
		}


		return gson.toJson(obj);
	}
	@PostMapping("/getGroup")
	public String getGroup(@RequestBody Map<String, Object> param) throws Exception {
		//클라이언트에 비동기 통신으로 받아줘야하니까 @ReqeustBody를 사용한다.
		Gson gson = new Gson();
		JsonObject obj =new JsonObject();

		String owneruserId = param.get("OWNERUSER_ID").toString();

		//서비스 측에 요청
		List<String> result = gis.getGroupInfo(owneruserId);

//		if (result == true) {
//			System.out.println("아이디 사용 불가!");
//			obj.addProperty("result", "NO");
//		} else {
//			System.out.println("아이디 사용 가능!");
//			obj.addProperty("result", "OK");
//		}
		obj.addProperty("result", result.toString());

		return gson.toJson(obj);
	}

}
