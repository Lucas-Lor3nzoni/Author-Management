package br.com.apiserver.authentication.service;

import br.com.apiserver.authentication.model.Administrator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindAdministratorService {

    Administrator findById(Long id);
    Page<Administrator> findAll(Pageable pageable);

}
