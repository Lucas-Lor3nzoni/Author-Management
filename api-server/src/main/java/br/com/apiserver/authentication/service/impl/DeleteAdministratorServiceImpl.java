package br.com.apiserver.authentication.service.impl;

import br.com.apiserver.authentication.exception.AdministratorNotFoundException;
import br.com.apiserver.authentication.model.Administrator;
import br.com.apiserver.authentication.repository.AdministratorRepository;
import br.com.apiserver.authentication.service.DeleteAdministratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class DeleteAdministratorServiceImpl implements DeleteAdministratorService {

    private final AdministratorRepository administratorRepository;

    @Transactional
    @Override
    public void delete(Long id) {
        log.info("Deleting administrator with id: {}", id);

        var admin = findAdmin(id);

        administratorRepository.delete(admin);
    }

    @Transactional
    @Override
    public void deactivate(Long id) {
        log.info("Deactivating administrator with id: {}", id);

        var admin = findAdmin(id);

        admin.setActive(false);

        administratorRepository.save(admin);
    }

    @Transactional
    @Override
    public void activate(Long id) {
        log.info("Activating administrator with id: {}", id);

        var admin = findAdmin(id);

        admin.setActive(false);

        administratorRepository.save(admin);
    }

    private Administrator findAdmin(Long id) {
        return administratorRepository.findById(id)
                .orElseThrow(() -> {
                    log.debug("Administrator not found with id: {}", id);
                    return new AdministratorNotFoundException("Administrator not found!");
                });
    }

}