package br.com.apiserver.work.service;

import br.com.apiserver.work.model.Work;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindWorkService {

    Work findWorkById(Long id);

    Page<Work> findWorkByTitle(String title, Pageable pageable);
    Page<Work> findWorkByAuthorName(String name, Pageable pageable);

}