package com.irme.server.webapp.filter;

import com.irme.server.webapp.jwt.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@Slf4j
public class JwtExtractorFilter extends OncePerRequestFilter {
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public JwtExtractorFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {

        try {
            Optional<String> token = jwtTokenProvider.resolveToken(request);
            if (token.isPresent() && jwtTokenProvider.validateToken(token.get())) {

                Authentication auth = jwtTokenProvider.generateAuthentication(token.get());

                if (auth != null) {
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        } finally {
            filterChain.doFilter(request, response);
        }
    }

}
