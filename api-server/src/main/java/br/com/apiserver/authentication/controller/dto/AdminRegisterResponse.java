package br.com.apiserver.authentication.controller.dto;

import java.util.Set;

public record AdminRegisterResponse(

        Long id, String name, String email, Set<String> roles

) { }