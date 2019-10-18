package com.incl.content.schedular.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.incl.content.schedular.bean.User;
 

public class TokenAuthenticationFilter extends OncePerRequestFilter {

	private TokenHelper tokenHelper;

	public TokenAuthenticationFilter(TokenHelper tokenHelper) {
		// TODO Auto-generated constructor stub

		this.tokenHelper = tokenHelper;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		User user = tokenHelper.validateToken(request);

		if (user != null && user.getIsAdmin()) {
			  Authentication auth = new ImportsAuthenticationToken(user);
		      SecurityContextHolder.getContext().setAuthentication(auth);    
		}else {
			String method = request.getMethod() ;
			if(!method.equalsIgnoreCase(HttpMethod.OPTIONS.name()))
			throw new SecurityException();
		}
	

        filterChain.doFilter(request, response);
		
	}

}
