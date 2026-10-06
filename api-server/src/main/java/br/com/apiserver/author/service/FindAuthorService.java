package br.com.apiserver.author.service;

import br.com.apiserver.author.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindAuthorService {

    Author findAuthorById(Long id);
    Page<Author> findAuthorByName(String name, Pageable pageable);

}