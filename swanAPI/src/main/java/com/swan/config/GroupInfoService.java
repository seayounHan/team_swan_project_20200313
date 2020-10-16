package com.swan.config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.swan.model.Group;
import com.swan.model.GroupPk;
import com.swan.model.Member;
import com.swan.model.Role;
import com.swan.persistence.GroupRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GroupInfoService {
	
	@Autowired
	private GroupRepository gr;
	
	public String save(String owneruserId, String userId, String userNm, String phone) {

		String str;
		
		try {
			System.out.println(owneruserId);
			System.out.println(userId);
			System.out.println(userNm);
			System.out.println(phone);
			gr.save(Group.builder()
				.OWNERUSER_ID(owneruserId)
				.USER_ID(userId)
				.USER_NAME(userNm)
		        .PHONE(phone)
		        .build());
			System.out.println("GROUP SAVE SUCCESS");

			str = "true";
		}catch(Exception e){
			System.out.println("GROUP SAVE ERROR : ");
			System.out.println(e);
			str="false";

		}
		

		return str;
	}
	
	public List<String> getGroupInfo(String owneruserid) throws Exception {
		
		System.out.println(owneruserid);
//		System.out.println(memberRepo.findAll());
		System.out.println("OK");
		List<Group> grp = gr.findByOwneruserId(owneruserid);
		List<String> result = new ArrayList<>();
		try {
			if (!grp.isEmpty()) { // id 존재
				Iterator<Group> it = grp.iterator();
				while(it.hasNext()) {
					Group str = it.next();
					System.out.println(str.getUserId());
					result.add(str.getUserId());
				}
			
				return result;
			}
			else {
				result.add("");
			}
		}catch (Exception e) {
			System.out.println(e);
			result.add("");
		}
		return result;
	}
	


}
