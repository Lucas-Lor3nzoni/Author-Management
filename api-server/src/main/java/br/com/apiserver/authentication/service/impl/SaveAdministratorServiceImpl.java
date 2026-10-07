package br.com.apiserver.authentication.service.impl;

import br.com.apiserver.authentication.exception.AdministratorAlreadyExistsException;
import br.com.apiserver.authentication.model.Administrator;
import br.com.apiserver.authentication.model.Roles;
import br.com.apiserver.authentication.repository.AdministratorRepository;
import br.com.apiserver.authentication.service.SaveAdministratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RequiredArgsConstructor
@Slf4j
@Service
public class SaveAdministratorServiceImpl implements SaveAdministratorService {

    private final AdministratorRepository administratorRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Administrator save(Administrator administrator) {

        log.info("Saving new administrator");

        verifyIfAdministratorExists(administrator.getEmail());

        var admin = Administrator.builder()
                .name(administrator.getName())
                .email(administrator.getEmail())
                .password(passwordEncoder.encode(administrator.getPassword()))
                .roles(Set.of(Roles.ADMIN))
                .active(true)
                .build();

        var saved = administratorRepository.save(admin);

        log.debug("Administrator saved successfully");

        return saved;
    }

    private void verifyIfAdministratorExists(String email) {
        if (administratorRepository.existsByEmail(email)) {
            log.debug("Administrator already exists");
            throw new AdministratorAlreadyExistsException("Administrator already exists");
        }
    }

}