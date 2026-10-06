package br.com.apiserver.author.controller;

import br.com.apiserver.author.controller.dto.AuthorRequest;
import br.com.apiserver.author.controller.dto.AuthorResponse;
import br.com.apiserver.author.controller.mapper.AuthorMapper;
import br.com.apiserver.author.service.DeleteAuthorService;
import br.com.apiserver.author.service.FindAuthorService;
import br.com.apiserver.author.service.SaveAuthorService;
import br.com.apiserver.author.service.UpdateAuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private final SaveAuthorService saveAuthorService;
    private final FindAuthorService findAuthorService;
    private final DeleteAuthorService deleteAuthorService;
    private final UpdateAuthorService updateAuthorService;
    private final AuthorMapper authorMapper;

    @PostMapping
    public ResponseEntity<AuthorResponse> save(@Valid @RequestBody AuthorRequest request) {
        log.info("REST request to save a new author");

        var saved = saveAuthorService.save(
                authorMapper.toModel(request)
        );

        var response = authorMapper.toResponse(saved);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> findById(@PathVariable Long id) {
        log.info("REST request to find author by id: {}", id);

        var author = findAuthorService.findAuthorById(id);

        return ResponseEntity.ok(authorMapper.toResponse(author));
    }

    @GetMapping
    public ResponseEntity<Page<AuthorResponse>> findAll(
            @RequestParam(required = false) String name, Pageable pageable) {
        log.info("REST request to find all authors");

        var authors = findAuthorService.findAuthorByName(name, pageable)
                .map(authorMapper::toResponse);

        return ResponseEntity.ok(authors);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("REST request to delete author by id: {}", id);

        deleteAuthorService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> update(@PathVariable Long id,
                                                 @Valid @RequestBody AuthorRequest request) {
        log.info("REST request to update author by id: {}", id);

        var model = authorMapper.toModel(request);

        var updated = updateAuthorService.update(id, model);

        return ResponseEntity.ok(authorMapper.toResponse(updated));
    }

}