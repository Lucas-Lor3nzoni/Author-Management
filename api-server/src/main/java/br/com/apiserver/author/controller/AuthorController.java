package br.com.apiserver.author.controller;

import br.com.apiserver.author.controller.dto.AuthorRequest;
import br.com.apiserver.author.controller.dto.AuthorResponse;
import br.com.apiserver.author.controller.mapper.AuthorMapper;
import br.com.apiserver.author.service.DeleteAuthorService;
import br.com.apiserver.author.service.FindAuthorService;
import br.com.apiserver.author.service.SaveAuthorService;
import br.com.apiserver.author.service.UpdateAuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Tag(
        name = "Author",
        description = "Operations related to authors"
)

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

    @Operation(
            summary = "Save a new author",
            description = "Save a new author in the database"
    )

    @ApiResponses({
            @ApiResponse(
                    responseCode = "201", description = "Author saved successfully"),
            @ApiResponse(
                    responseCode = "400", description = "Invalid request", content = @Content)
    })

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

    @Operation(
            summary = "Find author by id",
            description = "Find author by id"
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Author found successfully"),
            @ApiResponse(responseCode = "404", description = "Author not found", content = @Content)
    })

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> findById(@PathVariable Long id) {
        log.info("REST request to find author by id: {}", id);

        var author = findAuthorService.findAuthorById(id);

        return ResponseEntity.ok(authorMapper.toResponse(author));
    }

    @Operation(
            summary = "Find all authors",
            description = "Find all authors"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Authors found successfully"),
    })

    @GetMapping
    public ResponseEntity<Page<AuthorResponse>> findAll(
            @RequestParam(required = false) String name, Pageable pageable) {
        log.info("REST request to find all authors");

        var authors = findAuthorService.findAuthorByName(name, pageable)
                .map(authorMapper::toResponse);

        return ResponseEntity.ok(authors);
    }

    @Operation(
            summary = "Delete author by id",
            description = "Delete author by id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Author deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Author not found", content = @Content),
            @ApiResponse(responseCode = "409", description = "Author has books", content = @Content)
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("REST request to delete author by id: {}", id);

        deleteAuthorService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Update author by id",
            description = "Update author by id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Author updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Author not found", content = @Content)
    })

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> update(@PathVariable Long id,
                                                 @Valid @RequestBody AuthorRequest request) {
        log.info("REST request to update author by id: {}", id);

        var model = authorMapper.toModel(request);

        var updated = updateAuthorService.update(id, model);

        return ResponseEntity.ok(authorMapper.toResponse(updated));
    }

}