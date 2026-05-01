package com.github.rogerioja89.service;

import com.github.rogerioja89.dto.TokenResponse;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotAuthorizedException;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.Duration;
import java.time.Instant;
import java.util.Set;

@ApplicationScoped
public class AuthServiceImpl implements AuthService {

    private static final String FIXED_USERNAME = "rogerio";
    private static final String FIXED_PASSWORD = "1234567";
    private static final Set<String> FIXED_GROUPS = Set.of("USER");
    private static final long EXPIRES_IN_SECONDS = Duration.ofMinutes(30).toSeconds();

    @ConfigProperty(name = "mp.jwt.verify.issuer")
    String issuer;

    @Override
    public TokenResponse autenticar(String username, String password) {
        if (!FIXED_USERNAME.equals(username) || !FIXED_PASSWORD.equals(password)) {
            throw new NotAuthorizedException("Usuario ou senha invalidos.");
        }

        Instant now = Instant.now();
        String token = Jwt.issuer(issuer)
                .subject(username)
                .groups(FIXED_GROUPS)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(EXPIRES_IN_SECONDS))
                .sign();

        return new TokenResponse(token, "Bearer", EXPIRES_IN_SECONDS);
    }
}

