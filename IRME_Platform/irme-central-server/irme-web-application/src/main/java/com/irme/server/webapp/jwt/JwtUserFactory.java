package com.irme.server.webapp.jwt;

import com.irme.common.dto.AuthUserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    private JwtUserFactory() {}

    public static JwtUser createJWTUser(AuthUserDto user) {

        return new JwtUser(user.getId(), user.getFirstName(), user.getLastName(),
                user.getPasswordHash(), user.getEmail(), Boolean.TRUE,
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles())));

    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> userRoles) {
        return userRoles.stream().map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }
}

