package com.swan.model;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.swan.model.Role;

import org.hibernate.annotations.ColumnDefault;

import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//User 라는 이름의 spring boot 내장 클래스가 존재하기 때문에 혼선을 방지하기 위해 User -> Member 클래스 이름 변경

@ToString
@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@IdClass(GroupPk.class)
@Table(name = "R_NEW_GRP_MAS")
public class Group{

	@Id
	@Column(name = "OWNERUSER_ID", nullable = false, updatable = true)
	private String owneruserId;
	@Id
	@Column(name = "USER_ID", nullable = false, updatable = true)
	private String userId;

	
	private String USER_NAME;
	private String PHONE;

	@Builder
	public Group(String OWNERUSER_ID, String USER_ID, String USER_NAME, String PHONE) {
		
		this.owneruserId = OWNERUSER_ID;
	    this.userId = USER_ID;
	    this.USER_NAME = USER_NAME;
	    this.PHONE = PHONE;
	  }

}
