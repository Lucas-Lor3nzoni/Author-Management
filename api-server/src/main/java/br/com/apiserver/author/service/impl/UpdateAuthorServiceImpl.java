package br.com.apiserver.author.service.impl;

import br.com.apiserver.author.exception.AuthorAlreadyExistsException;
import br.com.apiserver.author.exception.AuthorNotFoundException;
import br.com.apiserver.author.model.Author;
import br.com.apiserver.author.repository.AuthorRepository;
import br.com.apiserver.author.service.UpdateAuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@RequiredArgsConstructor
@Slf4j
@Service
public class UpdateAuthorServiceImpl implements UpdateAuthorService {

    private final AuthorRepository authorRepository;

    @Transactional
    @Override
    public Author update(Long id, Author author) {
        log.info("Updating author with id {}", id);

        var authorToUpdate = authorRepository.findById(id)
                .orElseThrow(() -> {
                    log.debug("Author with id {} not found", id);
                    return new AuthorNotFoundException("Author not found!");
                });

        authorToUpdate.setName(author.getName());

        authorToUpdate.setBirthDate(author.getBirthDate());

        var countryCode = author.getCountryCode().toUpperCase(Locale.ROOT);

        authorToUpdate.setCountryCode(countryCode);

        updateEmail(authorToUpdate, author.getEmail(), id);

        updateCpf(authorToUpdate, author.getCpf(), id);

        log.info("Author with id {} updated", id);

        return authorToUpdate;
    }

    private void updateEmail(Author author, String email, Long id) {
        if (email == null || email.equals(author.getEmail())) {
            return;
        }

        if (authorRepository.existsByEmailAndIdNot(email, id)) {
            throw new AuthorAlreadyExistsException("Author already exists by email");
        }

        author.setEmail(email);
    }

    private void updateCpf(Author author, String cpf, Long id) {
        if ("BR".equals(author.getCountryCode())) {
            if (cpf == null || cpf.isBlank()) {
                throw new IllegalArgumentException(
                        "CPF cannot be empty for Brazilian authors!"
                );
            }

            if (!cpf.equals(author.getCpf())
                    && authorRepository.existsByCpfAndIdNot(cpf, id)) {

                throw new AuthorAlreadyExistsException(
                        "Author already exists by cpf"
                );
            }

            author.setCpf(cpf);
            return;
        }

        author.setCpf(null);
    }

}
