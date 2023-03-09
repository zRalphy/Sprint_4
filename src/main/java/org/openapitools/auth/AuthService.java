package org.openapitools.auth;

import java.util.Optional;

import org.openapitools.api.ApiException;
import org.openapitools.configuration.TokenGenerator;
import org.openapitools.model.User;
import org.openapitools.model.UserLoginRequest;
import org.openapitools.model.UserRegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final TokenGenerator tokenGenerator;

	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenGenerator tokenGenerator) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.tokenGenerator = tokenGenerator;
	}

	public User createUser(UserRegisterRequest userLoginRequest) throws ApiException {
		Optional<User> existingUser = userRepository.findUserByUserName(userLoginRequest.getUserName());
		User newUser = new User();
		if (existingUser.isEmpty()) {
			newUser.setName(userLoginRequest.getUserName());
			newUser.setPassword((passwordEncoder.encode(userLoginRequest.getPassword())));
			userRepository.save(newUser);
			return newUser;
		} else {
			throw new ApiException(HttpStatus.CONFLICT.value());
		}

	}

	public User loginUser(final UserLoginRequest userLoginRequest) throws ApiException {
		Optional<User> existingUser = userRepository.findUserByUserName(userLoginRequest.getUserName());
		if (existingUser.isPresent()) {
			String password = existingUser.get().getPassword();
			if (passwordEncoder.matches(password, passwordEncoder.encode(userLoginRequest.getPassword()))) {
				String token = tokenGenerator.generateToken(existingUser.get().getId());
				existingUser.get().setToken(token);
				userRepository.save(existingUser.get());
				return existingUser.get();
			} else {
				throw new ApiException(HttpStatus.UNAUTHORIZED.value());
			}
		} else {
			throw new ApiException(HttpStatus.NOT_FOUND.value());
		}
	}
}

