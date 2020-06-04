package com.swan.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.swan.model.Role;

import org.hibernate.annotations.ColumnDefault;

import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

//User 라는 이름의 spring boot 내장 클래스가 존재하기 때문에 혼선을 방지하기 위해 User -> Member 클래스 이름 변경

@ToString
@Getter
@Setter
@Entity
@Table(name = "R_USER_MAS")
public class Member {

	@Id
	private String USER_ID;
	private String USER_PASSWORD;
	private String USER_NAME;
	private String EMP_NUMBER;
	private String EMAIL;
	private String PHONE;
	private String CRT_TM;
	private String UDT_TM;
	@Enumerated(EnumType.STRING)
	private Role ROLE;
	private boolean ENABLED;

////////////////* lombok 적용하여 아래롤 삭제해도 무방 *///////////////	
//	public String getUSER_ID() {
//		return USER_ID;
//	}
//	public void setUSER_ID(String uSER_ID) {
//		USER_ID = uSER_ID;
//	}
//	public String getUSER_PASSWORD() {
//		return USER_PASSWORD;
//	}
//	public void setUSER_PASSWORD(String uSER_PASSWORD) {
//		USER_PASSWORD = uSER_PASSWORD;
//	}
//	public String getUSER_NAME() {
//		return USER_NAME;
//	}
//	public void setUSER_NAME(String uSER_NAME) {
//		USER_NAME = uSER_NAME;
//	}
//	public String getEMP_NUMBER() {
//		return EMP_NUMBER;
//	}
//	public void setEMP_NUMBER(String eMP_NUMBER) {
//		EMP_NUMBER = eMP_NUMBER;
//	}
//	public String getEMAIL() {
//		return EMAIL;
//	}
//	public void setEMAIL(String eMAIL) {
//		EMAIL = eMAIL;
//	}
//	public String getPHONE() {
//		return PHONE;
//	}
//	public void setPHONE(String pHONE) {
//		PHONE = pHONE;
//	}
//	public String getCRT_TM() {
//		return CRT_TM;
//	}
//	public void setCRT_TM(String cRT_TM) {
//		CRT_TM = cRT_TM;
//	}
//	public String getUDT_TM() {
//		return UDT_TM;
//	}
//	public void setUDT_TM(String uDT_TM) {
//		UDT_TM = uDT_TM;
//	}
//	public Role getRole() {
//		return ROLE;
//	}
//	public void setRole(Role rOLE) {
//		ROLE = rOLE;
//	}
	
	
	
}
