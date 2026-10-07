package br.com.apiserver.security.auth.service;

public interface SignInService {

    String signIn(String email, String password);

}