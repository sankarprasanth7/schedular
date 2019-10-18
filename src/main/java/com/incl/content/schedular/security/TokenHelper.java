package com.incl.content.schedular.security;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.incl.content.schedular.bean.User;
import com.incl.content.schedular.config.AppConfig;

@Service
public class TokenHelper {

	 

 

	@Value("${jwt.header}")
	private String AUTH_HEADER;

	@Value("${BASIC_AUTH_USERNAME}")
	private String BASIC_AUTH_USERNAME;

	@Value("${BASIC_AUTH_PASSWORD}")
	private String BASIC_AUTH_PASSWORD;

 

	@Autowired
	@Qualifier("authRestClient")
	RestTemplate restAuthTemplate;

	@Autowired
	AppConfig appConfig;

	public User validateToken(HttpServletRequest request) {

		String authHeader = getAuthHeaderFromHeader(request);

		return validateToken(authHeader);
	}

	public User validateToken(String accessToken) {

		if (accessToken == null) {
			return null;
		}

		// Onix Basic Auth authentication.
		if (accessToken.equals(getBase64EncodedToken(BASIC_AUTH_USERNAME, BASIC_AUTH_PASSWORD))) {
			System.out.println("Basic Auth");
			User user = new User();
			user.setUsername(BASIC_AUTH_USERNAME);
			user.setIsAdmin(true);
			return user;
		}

		URI targetUrl = UriComponentsBuilder.fromUriString(appConfig.getApi()).path("/auth/v1/me").build()
				.toUri();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set(HttpHeaders.AUTHORIZATION, accessToken);

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<User> responseEntity = restAuthTemplate.exchange(targetUrl, HttpMethod.GET, entity, User.class);
		System.out.println("Response :" + responseEntity.getBody().toString());
		User user = responseEntity.getBody();
		return user;

	}

	public String getAuthHeaderFromHeader(HttpServletRequest request) {
		return request.getHeader(AUTH_HEADER);
	}

	public String getBase64EncodedToken(String username, String password) {
		if (password.equals("")) {
			System.out.println("No BASIC PASSWORD SET:: CAN NOT COMMUNICATE WITH ONIX SERVICE");
			return "no_onix_basic_password";
		}
		String auth = username + ":" + password;
		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("ISO-8859-1")));
		String authHeader = "Basic " + new String(encodedAuth);
		System.out.println("Basic Auth :" + authHeader);

		return authHeader;
	}

}