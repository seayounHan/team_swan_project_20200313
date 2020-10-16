package com.swan.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class GroupPk implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	private String owneruserId;

	private String userId;
}
