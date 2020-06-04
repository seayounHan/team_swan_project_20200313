package com.swan.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swan.dao.UserRepository;
import com.swan.model.Member;


@Controller
@RequestMapping("/main")
@ResponseBody
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	UserRepository userRepository;

	/**
	 * test method
	 * @param model
	 * @return
	 */
	@ResponseBody
    @RequestMapping("")
	public Map<String, Object> getTestData() {

		Map<String, Object> test = new HashMap();
		
		test.put("test", "hello world");

		return test;
	}
	
//	/**
//	 * test method
//	 * @param model
//	 * @return
//	 */
//	@ResponseBody
//    @RequestMapping("/{userId}")
//	public Map<String, Object> getTestData(@PathVariable("userId") String userId) {
//
//		Map<String, Object> test = new HashMap();
//		Member user = userRepository.getUserInfo(userId);
//		logger.info(userId);
//		
//		test.put("user", user);
//
//		return test;
//	}
	


//////////////////* DB에 유저 생성 테스트 */////////////////////
//	@GetMapping("/insertUser")
//	public void insertUserDataForTest() {
//		String userId1 = "user2";
//		String userPassword1 = "userPw2";
//		String userName1 = "userName2";
//		String empNumber1 = "empNum2";
//		String email1 = "userEmail2";
//		String phone1 = "userPhone2";
//		String Role1 = "ROLE_MEMBER";
//		Boolean enabled1 = true;
//		
//		Member user1 = new Member();
//		user1.setUSER_ID(userId1);
//		user1.setUSER_PASSWORD(userPassword1);
//		user1.setUSER_NAME(userName1);
//		user1.setEMP_NUMBER(empNumber1);
//		user1.setEMAIL(email1);
//		user1.setPHONE(phone1);
//		user1.setROLE(Role1);
//		user1.setENABLED(true);
//		
//		userRepository.insertUser(user1);
//
//		
//	}
/////////////////////////////////////////////////////
	
	
//	/**
//	 * 검색했을 때의 메인 list화면으로 넘어가는 method
//	 * @param model
//	 * @param word : 사용자가 검색한 단어
//	 * @param nicknameCookie
//	 * @param sectionCookie
//	 * @return
//	 */
//	@RequestMapping(value = "/search", method = RequestMethod.GET)
//	public String searchResult(Model model, @RequestParam String word,
//		@CookieValue(value = Constant.COOKIE_AUTH, required = false) String nicknameCookieValue,
//		@CookieValue(value = Constant.COOKIE_SECTION, required = false) String sectionCookieValue) {
//
//		//main화면으로 넘겨줄 list를 받아옴
//		List<StoreParam> storeList = storeManageBO.getStoreList(nicknameCookieValue, sectionCookieValue, word);
//		List<Tag> tagCountList = searchBO.getTagCountList(word, sectionCookieValue);
//
//		//넘겨줄 list를 model 객체에 담음
//		model.addAttribute("tagCountList", tagCountList);
//		model.addAttribute("storeList", storeList);
//
//		return "home";
//	}

}
