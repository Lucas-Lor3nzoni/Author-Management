package br.com.apiserver.author.service.impl;

import br.com.apiserver.author.model.Author;
import br.com.apiserver.author.repository.AuthorRepository;
import br.com.apiserver.author.service.SaveAuthorService;
import br.com.apiserver.author.exception.AuthorAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class SaveAuthorImpl implements SaveAuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional
    public Author save(Author author) {
        log.info("Saving author {}", author.getName());

        validateIfAuthorExists(author.getEmail(), author.getCpf());

        var saved = authorRepository.save(author);

        log.info("Author saved successfully");

        return saved;
    }

    private void validateIfAuthorExists(String email, String cpf) {
        if (email != null
                && !email.isBlank()
                && authorRepository.existsByEmail(email)) {

            log.info("Author already exists by email {}", email);
            throw new AuthorAlreadyExistsException("Author already exists by email " + email);
        }

        if (cpf != null
                && !cpf.isBlank()
                && authorRepository.existsByCpf(cpf)) {

            log.info("Author already exists by cpf");
            throw new AuthorAlreadyExistsException("Author already exists by cpf");
        }

    }

}