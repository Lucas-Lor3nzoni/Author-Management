package br.com.apiserver.security.model;

import br.com.apiserver.authentication.model.Administrator;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@NullMarked
public record UserPrincipal(

        Administrator administrator

) implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return administrator.getRoles();
    }

    @Override
    public String getPassword() {
        return administrator().getPassword();
    }

    @Override
    public String getUsername() {
        return administrator().getEmail();
    }

    @Override
    public boolean isEnabled() {
        return administrator.isActive();
    }

}