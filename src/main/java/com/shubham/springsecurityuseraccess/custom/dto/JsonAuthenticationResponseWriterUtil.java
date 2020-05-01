package com.shubham.springsecurityuseraccess.custom.dto;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.json.JsonParseException;
import org.springframework.security.authentication.AuthenticationServiceException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonAuthenticationResponseWriterUtil {

	public static void writeJsonModelToHttpServletResponse(HttpServletResponse response, Object model)
			throws AuthenticationServiceException {
		PrintWriter writer = null;
		ObjectMapper jsonMapper = new ObjectMapper();
		try {
			writer = response.getWriter();
			jsonMapper.writeValue(writer, model);
			response.flushBuffer();

		} catch (JsonParseException e) {
			throw new AuthenticationServiceException("JsonParserException " + e.getMessage());
		} catch (JsonMappingException e) {
			throw new AuthenticationServiceException("JsonMappingException " + e.getMessage());
		} catch (IOException e) {
			throw new AuthenticationServiceException("HttpResponse PrintWriter " + e.getMessage());
		} finally {
			writer.close();
		}
	}
}
