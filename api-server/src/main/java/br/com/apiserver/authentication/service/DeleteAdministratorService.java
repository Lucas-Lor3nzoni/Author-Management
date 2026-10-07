package br.com.apiserver.authentication.service;

public interface DeleteAdministratorService {

    void delete(Long id);
    void deactivate(Long id);
    void activate(Long id);


}