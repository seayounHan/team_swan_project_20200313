package com.swan.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Component
public class CustomSimpleUrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws ServletException, IOException {
		Gson gson = new Gson();
		JsonObject obj =new JsonObject();
		
		obj.addProperty("result", "OK");
		
		response.setStatus(HttpServletResponse.SC_OK);
	        // 성공 시 response를 json형태로 반환
		System.out.println("SUCCESS");
	        response.getWriter().print(gson.toJson(obj));
	        response.getWriter().flush();
	    }
	 

}
