package br.com.apiserver.author.service;

import br.com.apiserver.author.model.Author;

public interface UpdateAuthorService {

    Author update(Long id, Author author);

}
