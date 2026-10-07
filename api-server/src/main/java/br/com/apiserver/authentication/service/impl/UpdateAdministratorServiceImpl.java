package br.com.apiserver.authentication.service.impl;

import br.com.apiserver.authentication.exception.AdministratorAlreadyExistsException;
import br.com.apiserver.authentication.exception.AdministratorNotFoundException;
import br.com.apiserver.authentication.model.Administrator;
import br.com.apiserver.authentication.repository.AdministratorRepository;
import br.com.apiserver.authentication.service.UpdateAdministratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UpdateAdministratorServiceImpl implements UpdateAdministratorService {

    private final AdministratorRepository administratorRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Administrator update(Administrator administrator, Long id) {

        log.info("Updating administrator with id: {}", id);

        var adminToUpdate = findAdmin(id);

        if (administrator.getEmail() != null
                && !administrator.getEmail().isBlank()
                && !administrator.getEmail().equals(adminToUpdate.getEmail())) {

            verifyEmail(administrator.getEmail());

            adminToUpdate.setEmail(administrator.getEmail());
        }

        if (administrator.getName() != null
                && !administrator.getName().isBlank()) {

            adminToUpdate.setName(administrator.getName());
        }

        if (administrator.getPassword() != null
                && !administrator.getPassword().isBlank()) {

            adminToUpdate.setPassword(
                    passwordEncoder.encode(administrator.getPassword())
            );
        }

        return adminToUpdate;
    }

    private void verifyEmail(String email) {

        if (administratorRepository.findByEmail(email).isPresent()) {

            log.debug("Administrator with email {} already exists", email);

            throw new AdministratorAlreadyExistsException("Administrator already exists");
        }
    }

    private Administrator findAdmin(Long id) {
        return administratorRepository.findById(id)
                .orElseThrow(() -> {
                    log.debug("Administrator not found with id: {}", id);
                    return new AdministratorNotFoundException("Administrator not found!");
                });
    }
}
