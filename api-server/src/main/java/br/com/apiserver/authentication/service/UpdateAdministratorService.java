package br.com.apiserver.authentication.service;

import br.com.apiserver.authentication.model.Administrator;

public interface UpdateAdministratorService {

    Administrator update(Administrator administrator, Long id);

}