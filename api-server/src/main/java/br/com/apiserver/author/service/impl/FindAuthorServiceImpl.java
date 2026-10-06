package br.com.apiserver.author.service.impl;

import br.com.apiserver.author.exception.AuthorNotFoundException;
import br.com.apiserver.author.model.Author;
import br.com.apiserver.author.repository.AuthorRepository;
import br.com.apiserver.author.service.FindAuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class FindAuthorServiceImpl implements FindAuthorService {

    private final AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    @Override
    public Author findAuthorById(Long id) {
        log.info("Finding author by id: {}", id);

        return authorRepository.findById(id)
                .orElseThrow(() -> {
                    log.debug("Author not found with id: {}", id);
                    return new AuthorNotFoundException("Author not found!");
                });
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Author> findAuthorByName(String name, Pageable pageable) {
        log.info("Finding author by name: {}", name);

        if (name == null || name.isBlank()) {
            return authorRepository.findAll(pageable);
        }

        return authorRepository.findByNameContainingIgnoreCase(name.trim(), pageable);
    }
}
