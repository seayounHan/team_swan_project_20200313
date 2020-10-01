package com.swan.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
//import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	private AuthenticationEntryPoint authenticationEntryPoint;
	
// JPA 사용으로 인한 주석 처리
//	@Autowired
//	private DataSource dataSource;
	
	@Autowired
	private SwanUserDetailService swanUserDetailService;
	
	@Override
	protected void configure(HttpSecurity security) throws Exception{
		
		security.csrf().disable();
		security.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
		security.authorizeRequests().antMatchers("/login").permitAll();
		security.formLogin().disable();
		
		security.addFilterAt(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
		
		security.authorizeRequests().antMatchers("/index/**").permitAll();
		security.authorizeRequests().antMatchers("/member/**").hasRole("MEMBER");
		security.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
		
//		security.formLogin().loginPage("/login").defaultSuccessUrl("/userMain", true);
//		security.exceptionHandling().accessDeniedPage("/accessDenied");
		security.logout().invalidateHttpSession(true).logoutSuccessUrl("/login");
		
		security.userDetailsService(swanUserDetailService);
	}
	
	protected CustomUserAuthenticationFilter getAuthenticationFilter() {
		CustomUserAuthenticationFilter authFilter = new CustomUserAuthenticationFilter();
		CustomSimpleUrlAuthenticationSuccessHandler csuas = new CustomSimpleUrlAuthenticationSuccessHandler();

		try {
		authFilter.setFilterProcessesUrl("/login");
		authFilter.setAuthenticationManager(this.authenticationManagerBean());
		authFilter.setUsernameParameter("USER_ID");
		authFilter.setPasswordParameter("USER_PASSWORD");
//		authFilter.setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler("/userMain")); // 로그인 성공시 이동하는 url. 이때 어떤 정보를 json에 담아 뿌려줄건지 협의 필요.
		authFilter.setAuthenticationSuccessHandler(new CustomSimpleUrlAuthenticationSuccessHandler());
//		authFilter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login")); // 로그인 실패시 다시 login 화면으로. 로그인 실패했다는 팝업을 위한 메세지를 json에 담아 전송하는것 구현 예정 
		authFilter.setAuthenticationFailureHandler(new CustomSimpleUrlAuthenticationFailureHandler()); // 401 Unauthorized
		}catch(Exception e) {
			System.out.println("EXCEPTION DURING LOGIN : " + e);
			e.printStackTrace();
		}
		return authFilter;
	}

////////////////////////////////////////* JPA 연동으로 사용하지 않아 주석처리 */////////////////////////////////////////////////////////
//	@Autowired
//	public void authenticate(AuthenticationManagerBuilder auth) throws Exception{
//		
//		String query1 = "select USER_ID id, concat('',USER_PASSWORD) password, true enabled from R_USER_MAS where USER_ID=?";
//		String query2 = "select USER_ID id, USER_NAME role from R_USER_MAS where USER_ID=?";
//		
////		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(query1).authoritiesByUsernameQuery(query2);
//		
////		auth.inMemoryAuthentication().withUser("member").password("{noop}member123").roles("MEMBER");
////		auth.inMemoryAuthentication().withUser("admin").password("{noop}admin123").roles("ADMIN");
//		
//		
//	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}

