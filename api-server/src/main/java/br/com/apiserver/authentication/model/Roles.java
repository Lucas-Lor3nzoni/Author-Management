package br.com.apiserver.authentication.model;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    ADMIN, SUPER_ADMIN;

    @Override
    public String getAuthority() {
        return "ROLE_" +  name();
    }

}