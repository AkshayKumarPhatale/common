package com.accenture.empportal.entity;

import java.util.LinkedHashSet;
import java.util.Set;



public enum Gender {
	
	MALE,FEMALE;
	
	
	private static final Set<String> genderSet = new LinkedHashSet<String>(Gender.values().length);

	static {
		for (Gender genderEnum : Gender.values())
			genderSet.add(genderEnum.name());
		
	}

	public static boolean contains(String value) {
		return genderSet.contains(value);
		
	}
}
