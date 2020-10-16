package com.swan.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


import com.swan.model.Group;
import com.swan.model.GroupPk;
import com.swan.model.Member;


public interface GroupRepository extends CrudRepository<Group, String>{
	public List<Group> findByOwneruserId(String owneruserid);
}
