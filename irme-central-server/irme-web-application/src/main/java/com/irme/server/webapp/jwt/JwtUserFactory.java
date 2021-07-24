package com.irme.server.webapp.jwt;

public final class JwtUserFactory {

    private JwtUserFactory() {}

    // public static JwtUser createJWTUser(UserDto user) {

    //     return new JwtUser(user.getId(), user.getFirstName(), user.getLastName(),
    //             user.getPasswordHash(), user.getEmail(), Boolean.TRUE,
    //             mapToGrantedAuthorities(new ArrayList<>(user.getRoles())));

    // }

    // private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> userRoles) {
    //     return userRoles.stream().map(role -> new SimpleGrantedAuthority(role))
    //             .collect(Collectors.toList());
    // }
}

