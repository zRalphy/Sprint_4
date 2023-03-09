package org.openapitools.auth;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.annotation.Generated;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.openapitools.api.ApiException;
import org.openapitools.model.User;
import org.openapitools.model.UserLoginRequest;
import org.openapitools.model.UserRegisterRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-03-05T22:03:09.131657600+01:00[Europe/Warsaw]")
@Controller
@RequestMapping("${openapi.time.base-path:}")
class AuthController implements AuthApi {

	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	private final ObjectMapper objectMapper;
	private final HttpServletRequest request;
	private final AuthService authService;

	@org.springframework.beans.factory.annotation.Autowired
	public AuthController(ObjectMapper objectMapper, HttpServletRequest request, AuthService authService) {
		this.objectMapper = objectMapper;
		this.request = request;
		this.authService = authService;
	}

	public ResponseEntity<Void> login(@Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody
			UserLoginRequest body) {
		try {
			User user = authService.loginUser(body);
			MultiValueMapAdapter<String, String> headers = new LinkedMultiValueMap<>();
			headers.add("Authorization", "Barear" + user.getToken());
			return new ResponseEntity<Void>(headers, HttpStatus.OK);
		} catch (ApiException ex) {
			return new ResponseEntity<Void>(HttpStatus.valueOf(ex.getCode()));
		}
	}

	public ResponseEntity<Void> register(@Parameter(in = ParameterIn.DEFAULT, description = "", required = true, schema = @Schema()) @Valid @RequestBody
			UserRegisterRequest body) {
		try {
			User user = authService.createUser(body);
			MultiValueMapAdapter<String, String> headers = new LinkedMultiValueMap<>();
			headers.add("userId", String.valueOf(user.getId()));
			return new ResponseEntity<Void>(headers, HttpStatus.OK);
		} catch (ApiException ex) {
			return new ResponseEntity<Void>(HttpStatus.valueOf(ex.getCode()));
		}
	}
}
