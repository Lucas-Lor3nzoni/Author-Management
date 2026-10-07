package br.com.apiserver.work.controller.mapper;

import br.com.apiserver.work.controller.dto.WorkRequest;
import br.com.apiserver.work.controller.dto.WorkResponse;
import br.com.apiserver.work.model.Work;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authors", ignore = true)
    Work toModel(WorkRequest request);
    WorkResponse toResponse(Work work);

}