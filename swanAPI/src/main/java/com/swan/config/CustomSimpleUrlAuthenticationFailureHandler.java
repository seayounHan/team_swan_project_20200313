package com.swan.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Component
public class CustomSimpleUrlAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{

	@Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
		Gson gson = new Gson();
		JsonObject obj =new JsonObject();
		
		obj.addProperty("result", "NO");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // 실패 시 response를 json 형태로 결과값 전달
        response.getWriter().print(gson.toJson(obj));
        response.getWriter().flush();
    }



}