package com.accenture.empportal.entity;

import java.util.LinkedHashSet;
import java.util.Set;

public enum Role {

	ASSOCIATE,SENIOR,TEAM_LEAD,MANAGER;
	

	private static final Set<String> roleSet = new LinkedHashSet<String>(Role.values().length);

	static {
		for (Role statusEnum : Role.values())
			roleSet.add(statusEnum.name());
	}

	public static boolean contains(String value) {
		return roleSet.contains(value);
		
	}
	
}
