package br.com.apiserver.security.auth.controller.mapper;

import br.com.apiserver.security.auth.controller.dto.SignInResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    SignInResponse toResponse(String token);
}
