package br.com.apiserver.author.service.impl;

import br.com.apiserver.author.exception.AuthorNotFoundException;
import br.com.apiserver.author.repository.AuthorRepository;
import br.com.apiserver.author.service.DeleteAuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class DeleteAuthorServiceImpl implements DeleteAuthorService {

    private final AuthorRepository authorRepository;

    @Transactional
    @Override
    public void delete(Long id) {
        log.info("Deleting author with id {}", id);

        var author = authorRepository.findById(id)
                .orElseThrow(() -> {
                    log.debug("Author not found with id {}", id);
                    return new AuthorNotFoundException("Author not found!");
                });

        if (!author.getWorks().isEmpty()) {
            log.debug("Author has works associated with it!");
            throw new AuthorNotFoundException("Author has works associated with it!");
        }

        authorRepository.delete(author);
    }

}