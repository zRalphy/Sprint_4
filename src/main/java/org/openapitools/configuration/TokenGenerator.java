package org.openapitools.configuration;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.SystemProperties;
import org.springframework.stereotype.Component;

@Component
public class TokenGenerator {
	private static final long MILLISECONDS_IN_DAY = 86400;

	private final String secretKey;

	public TokenGenerator() {
		this.secretKey = SystemProperties.get("JWT_SECRET");
	}

	public String generateToken(long userId) {
		return Jwts.builder()
				.setSubject(String.valueOf(userId))
				.setExpiration(Date.from(Instant.now().plusSeconds(MILLISECONDS_IN_DAY)))
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();
	}
}
