package br.com.apiserver.authentication.controller.mapper;

import br.com.apiserver.authentication.controller.dto.AdminRegisterRequest;
import br.com.apiserver.authentication.controller.dto.AdminRegisterResponse;
import br.com.apiserver.authentication.model.Administrator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdministratorMapper {

    @Mapping(target = "id", ignore = true)
    Administrator toModel(AdminRegisterRequest request);
    AdminRegisterResponse toResponse(Administrator administrator);

}
