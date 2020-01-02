package com.sensei.app.web.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sensei.app.security.jwt.JWTConfigurer;
import com.sensei.app.security.jwt.TokenProvider;
import com.sensei.app.web.rest.vm.LoginVM;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
public class UserJWTController {

	private final TokenProvider tokenProvider;

	private final AuthenticationManager authenticationManager;

	public UserJWTController(TokenProvider tokenProvider, AuthenticationManager authenticationManager) {
		this.tokenProvider = tokenProvider;
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/authenticate")
	@Timed
	public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginVM loginVM, HttpServletRequest request) {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				loginVM.getUsername(), loginVM.getPassword());

		Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		boolean rememberMe = (loginVM.isRememberMe() == null) ? false : loginVM.isRememberMe();
		String jwt = tokenProvider.createToken(authentication, rememberMe);
		String refreshToken = tokenProvider.createRefereshToken(loginVM.getUsername());
		HttpSession session = request.getSession();
		session.setAttribute("refreshToken", refreshToken);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
		return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
	}

	/**
	 * Object to return as body in JWT Authentication.
	 */
	static class JWTToken {

		private String idToken;

		JWTToken(String idToken) {
			this.idToken = idToken;
		}

		@JsonProperty("id_token")
		String getIdToken() {
			return idToken;
		}

		void setIdToken(String idToken) {
			this.idToken = idToken;
		}
	}

	@PostMapping("/getAccessToken")
	@Timed
	public ResponseEntity<Object> authorize(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(StringUtils.isNotEmpty(session.getAttribute("refreshToken").toString())) {
			SecurityContext securityContext = SecurityContextHolder.getContext();
			String jwt = tokenProvider.createToken(securityContext.getAuthentication(), false);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add(JWTConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
			return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
		}
			return new ResponseEntity<>("Invalid Session, Please login to Continue", HttpStatus.UNAUTHORIZED);
		
	}
}
