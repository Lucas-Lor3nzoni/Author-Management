package br.com.apiserver.authentication.service.impl;

import br.com.apiserver.authentication.exception.AdministratorNotFoundException;
import br.com.apiserver.authentication.model.Administrator;
import br.com.apiserver.authentication.repository.AdministratorRepository;
import br.com.apiserver.authentication.service.FindAdministratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class FindAdministratorServiceImpl implements FindAdministratorService {

    private final AdministratorRepository administratorRepository;

    @Transactional(readOnly = true)
    @Override
    public Administrator findById(Long id) {
        log.info("Finding administrator with id {}", id);

        return administratorRepository.findById(id)
                .orElseThrow(() -> {
                    log.debug("Administrator with id {} not found", id);
                    return new AdministratorNotFoundException("Administrator not found!");
                });
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Administrator> findAll(Pageable pageable) {
        log.info("Finding all administrator");
        return administratorRepository.findAll(pageable);
    }

}