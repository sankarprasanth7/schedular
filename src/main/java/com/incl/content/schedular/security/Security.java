package com.incl.content.schedular.security;

import org.springframework.security.core.Authentication;

import com.incl.content.schedular.bean.User;

public class Security {

	
	public static String getName(Authentication auth) {
		
		try {
			  return  ( (User) auth.getPrincipal()).getUsername();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
}
