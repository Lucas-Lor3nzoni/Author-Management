package br.com.apiserver.authentication.controller;

import br.com.apiserver.authentication.controller.dto.AdminRegisterRequest;
import br.com.apiserver.authentication.controller.dto.AdminRegisterResponse;
import br.com.apiserver.authentication.controller.mapper.AdministratorMapper;
import br.com.apiserver.authentication.service.DeleteAdministratorService;
import br.com.apiserver.authentication.service.FindAdministratorService;
import br.com.apiserver.authentication.service.SaveAdministratorService;
import br.com.apiserver.authentication.service.UpdateAdministratorService;
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
@RequestMapping("/api/v1/administrators")
public class AdministratorController {

    private final SaveAdministratorService saveAdministratorService;
    private final FindAdministratorService findAdministratorService;
    private final DeleteAdministratorService deleteAdministratorService;
    private final UpdateAdministratorService updateAdministratorService;
    private final AdministratorMapper administratorMapper;

    @PostMapping
    public ResponseEntity<AdminRegisterResponse> save(@Valid @RequestBody AdminRegisterRequest request) {
        log.info("REST request to save new administrator");

        var model = administratorMapper.toModel(request);
        var response = administratorMapper
                .toResponse(saveAdministratorService.save(model));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminRegisterResponse> findById(@PathVariable Long id) {
        log.info("REST request to find administrator by id");

        var model = findAdministratorService.findById(id);
        var response = administratorMapper.toResponse(model);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<AdminRegisterResponse>> findAll(Pageable pageable) {
        log.info("REST request to find all administrators");

        var models = findAdministratorService.findAll(pageable)
                .map(administratorMapper::toResponse);

        return ResponseEntity.ok(models);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("REST request to delete administrator by id");

        deleteAdministratorService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        log.info("REST request to deactivate administrator with id: {}", id);

        deleteAdministratorService.deactivate(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity<Void> activate(@PathVariable Long id) {
        log.info("REST request to activate administrator with id: {}", id);

        deleteAdministratorService.activate(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminRegisterResponse> update(@PathVariable Long id,
                                                        @Valid @RequestBody AdminRegisterRequest request) {
        log.info("REST request to update administrator with id: {}", id);

        var model = administratorMapper.toModel(request);
        var response = administratorMapper
                .toResponse(updateAdministratorService.update(model, id));

        return ResponseEntity.ok(response);
    }

}