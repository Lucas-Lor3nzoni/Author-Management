package br.com.apiserver.author.controller.mapper;

import br.com.apiserver.author.controller.dto.AuthorRequest;
import br.com.apiserver.author.controller.dto.AuthorResponse;
import br.com.apiserver.author.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "id", ignore = true)
    Author toModel(AuthorRequest request);
    AuthorResponse toResponse(Author author);

}