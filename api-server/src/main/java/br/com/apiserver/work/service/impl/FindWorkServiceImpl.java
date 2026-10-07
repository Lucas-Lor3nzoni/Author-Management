package br.com.apiserver.work.service.impl;

import br.com.apiserver.work.exception.WorkNotFoundException;
import br.com.apiserver.work.model.Work;
import br.com.apiserver.work.repository.WorkRepository;
import br.com.apiserver.work.service.FindWorkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class FindWorkServiceImpl implements FindWorkService {

    private final WorkRepository workRepository;

    @Transactional(readOnly = true)
    @Override
    public Work findWorkById(Long id) {
        log.info("Finding work by id: {}", id);

        return workRepository.findById(id)
                .orElseThrow(() -> {
                    log.debug("Work with id {} not found", id);
                    return new WorkNotFoundException("Work not found!");
                });
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Work> findWorkByTitle(String title, Pageable pageable) {
        log.info("Finding work by title: {}", title);

        if (title == null || title.isBlank()) {
            return workRepository.findAll(pageable);
        }

        return workRepository.findByTitleContainingIgnoreCase(title, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Work> findWorkByAuthorName(String name, Pageable pageable) {
        log.info("Finding Work by Author: {}", name);
        return workRepository.findByAuthorsNameContainingIgnoreCase(name, pageable);
    }
}
