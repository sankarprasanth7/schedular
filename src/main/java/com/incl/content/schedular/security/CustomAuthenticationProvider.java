package com.incl.content.schedular.security;



import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		ImportsAuthenticationToken demoAuthentication = (ImportsAuthenticationToken) auth;
		 
		return demoAuthentication;
	 
	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(ImportsAuthenticationToken.class);
	}
}