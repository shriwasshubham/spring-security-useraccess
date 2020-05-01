package com.shubham.springsecurityuseraccess.custom;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.shubham.springsecurityuseraccess.custom.dto.LoginRequest;
import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
public class CustomUsernamePasswordAuthenticationFilter extends
    UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		log.info(
				"############ Inside CustomUsernamePasswordAuthenticationFilter#attemptAuthentication method ########");
		UsernamePasswordAuthenticationToken authRequest = null;

		LoginRequest loginRequest = this.getLoginRequest(request);

		authRequest = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	private LoginRequest getLoginRequest(HttpServletRequest request) {
		BufferedReader reader = null;
		LoginRequest loginRequest = null;

		ObjectMapper jsonMapper = new ObjectMapper();
		try {
			reader = request.getReader();
			loginRequest = jsonMapper.readValue(reader, LoginRequest.class);
		} catch (RuntimeException e) {
			throw new RuntimeException("JsonParserException " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				logger.error("Exception Ocuured while closing the reader", ex);
			}
		}

		if (loginRequest == null) {
			loginRequest = new LoginRequest();
		}

		return loginRequest;
	}

}
