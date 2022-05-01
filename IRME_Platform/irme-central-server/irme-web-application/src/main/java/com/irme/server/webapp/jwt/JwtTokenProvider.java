package com.irme.server.webapp.jwt;

import com.irme.common.dto.AuthUserDto;
import com.irme.server.business_entities.UserBusinessLogic;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String secret;

    @Value("${jwt.token.expired}")
    private long validityInMilliseconds;

    @Autowired
    private UserBusinessLogic userBusinessLogic;

    @SuppressWarnings({ "deprecation" })
    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder passwordEncoder = org.springframework.security.crypto.password.NoOpPasswordEncoder
                .getInstance();
        return passwordEncoder;
    }

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(String username, Collection<String> roles) {

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        String token = Jwts
                .builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        return token;
    }

    public Optional<String> extendToken(String oldToken) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(oldToken).getBody();

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        String token = Jwts
                .builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        return Optional.ofNullable(token);

    }

    public Authentication generateAuthentication(String token) {

        String userName = getUsername(token);
        AuthUserDto user = userBusinessLogic.getUserByEmail(userName);
        UserDetails userDetails = JwtUserFactory.createJWTUser(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, "",
                userDetails.getAuthorities());

        return auth;
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public Optional<String> resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
            return Optional.ofNullable(bearerToken.substring(7, bearerToken.length()));
        }

        return Optional.ofNullable(null);
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            Date now = new Date();

            if (claims.getBody().getExpiration().before(now)) {
                return false;
            }

            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }
    }

}
