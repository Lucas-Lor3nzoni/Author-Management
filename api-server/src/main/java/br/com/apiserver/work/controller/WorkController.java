package br.com.apiserver.work.controller;

import br.com.apiserver.work.controller.dto.WorkRequest;
import br.com.apiserver.work.controller.dto.WorkResponse;
import br.com.apiserver.work.controller.mapper.WorkMapper;
import br.com.apiserver.work.service.DeleteWorkService;
import br.com.apiserver.work.service.FindWorkService;
import br.com.apiserver.work.service.SaveWorkService;
import br.com.apiserver.work.service.UpdateWorkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashSet;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/works")
public class WorkController {

    private final SaveWorkService saveWorkService;
    private final FindWorkService findWorkService;
    private final DeleteWorkService deleteWorkService;
    private final UpdateWorkService updateWorkService;
    private final WorkMapper workMapper;

    @PostMapping
    public ResponseEntity<WorkResponse> saveWork(@Valid @RequestBody WorkRequest request) {
        log.info("REST request to save a new work");

        var work = workMapper.toModel(request);

        var authorIds = new HashSet<>(request.authorIds());

        var saved = saveWorkService.save(work, authorIds);

        var response = workMapper.toResponse(saved);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkResponse> findById(@PathVariable Long id) {
        log.info("REST request to find work by id: {}", id);

        var work = findWorkService.findWorkById(id);

        return ResponseEntity.ok().body(workMapper.toResponse(work));
    }

    @GetMapping
    public ResponseEntity<Page<WorkResponse>> findAll(
            @RequestParam(required = false) String title, Pageable pageable) {
        log.info("REST request to find all works");

        var works = findWorkService.findWorkByTitle(title, pageable)
                .map(workMapper::toResponse);

        return ResponseEntity.ok(works);
    }

    @GetMapping("/author")
    public ResponseEntity<Page<WorkResponse>> findAllByAuthor(
            @RequestParam(required = false) String name, Pageable pageable) {
        log.info("REST request to find all works by author");

        var works = findWorkService.findWorkByAuthorName(name, pageable)
                .map(workMapper::toResponse);

        return ResponseEntity.ok(works);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkById(@PathVariable Long id) {
        log.info("REST request to delete work by id: {}", id);

        deleteWorkService.deleteWorkById(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkResponse> updateWorkById(@PathVariable Long id,
                                                       @Valid @RequestBody WorkRequest request) {
        log.info("REST request to update work by id: {}", id);

        var work = workMapper.toModel(request);

        var authorIds = new HashSet<>(request.authorIds());

        var updatedWork = updateWorkService.update(work, id, authorIds);

        return ResponseEntity.ok().body(workMapper.toResponse(updatedWork));
    }

}