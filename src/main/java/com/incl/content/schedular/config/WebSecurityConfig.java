package com.incl.content.schedular.config;

 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

import com.incl.content.schedular.security.CorsConfig;
import com.incl.content.schedular.security.CustomAuthenticationProvider;
import com.incl.content.schedular.security.RestAuthenticationEntryPoint;
import com.incl.content.schedular.security.TokenAuthenticationFilter;
import com.incl.content.schedular.security.TokenHelper;
 
 
 
 

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Value("${BASIC_AUTH_USERNAME}")
	private String BASIC_AUTH_USERNAME;

	@Value("${BASIC_AUTH_PASSWORD}")
	private String BASIC_AUTH_PASSWORD;
 
	@Autowired
	TokenHelper tokenHelper;
	
    @Autowired
    CustomAuthenticationProvider customAuthProvider;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	 
    
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		  List<RequestMatcher> csrfMethods = new ArrayList<>();
	        Arrays.asList( "POST", "PUT", "PATCH", "DELETE", "OPTIONS" )
	                .forEach( method -> csrfMethods.add( new AntPathRequestMatcher( "/**", method ) ) );
	        
	        		http.cors()
	        			.configurationSource(request -> new CorsConfiguration(new CorsConfig()).applyPermitDefaultValues())
	        			.and()
	                .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS ).and()
	                .exceptionHandling().authenticationEntryPoint( restAuthenticationEntryPoint ).and()
	                .authorizeRequests()
	                	.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
	                	.and()
	                	.csrf().disable()
	                	.authorizeRequests()
	                	.and()
	                	.httpBasic()
	                	.and()
	                	.authorizeRequests()
	                	.antMatchers("/imports/**")
	                	.authenticated();
		final TokenAuthenticationFilter tokenFilter = new TokenAuthenticationFilter(tokenHelper);
        http.addFilterBefore(tokenFilter, BasicAuthenticationFilter.class);
 
      
	}
	 
	 
	@Override
    public void configure(AuthenticationManagerBuilder auth) 
      throws Exception {
 
        auth.authenticationProvider(customAuthProvider);
       
        
        
        auth.inMemoryAuthentication()
            .withUser(BASIC_AUTH_USERNAME)
            .password(BASIC_AUTH_PASSWORD)
            .roles("ADMIN");
    }
 
}
