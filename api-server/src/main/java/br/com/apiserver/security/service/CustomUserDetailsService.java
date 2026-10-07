package br.com.apiserver.security.service;

import br.com.apiserver.authentication.repository.AdministratorRepository;
import br.com.apiserver.security.model.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AdministratorRepository administratorRepository;

    @NullMarked
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return administratorRepository.findByEmail(username)
                .map(UserPrincipal::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}