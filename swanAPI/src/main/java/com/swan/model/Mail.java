package com.swan.model;
import javax.persistence.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class Mail{

	private String fromMail;
	private String toMail;
	private String mailSubject;
	private String mailContent;

	@Builder
	public Mail(String FROM_MAIL, String TO_MAIL, String MAIL_SUBJECT, String MAIL_CONTENT) {
		
		this.fromMail = FROM_MAIL;
	    this.toMail = TO_MAIL;
	    this.mailSubject = MAIL_SUBJECT;
	    this.mailContent = MAIL_CONTENT;
	  }

}
