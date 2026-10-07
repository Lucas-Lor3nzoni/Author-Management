package br.com.apiserver.work.service.impl;

import br.com.apiserver.author.repository.AuthorRepository;
import br.com.apiserver.work.exception.WorkNotFoundException;
import br.com.apiserver.work.model.Work;
import br.com.apiserver.work.repository.WorkRepository;
import br.com.apiserver.work.service.UpdateWorkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Slf4j
@Service
public class UpdateWorkServiceImpl implements UpdateWorkService {

    private final WorkRepository workRepository;
    private final AuthorRepository authorRepository;

    @Transactional
    @Override
    public Work update(Work work, Long id, Set<Long> authorIds) {

        log.info("Updating work with id: {}", id);

        var existingWork = workRepository.findById(id)
                .orElseThrow(() -> {
                    log.debug("Work with id {} not found", id);
                    return new WorkNotFoundException("Work not found");
                });

        existingWork.setTitle(work.getTitle());
        existingWork.setDescription(work.getDescription());

        var authors = new HashSet<>(authorRepository.findAllById(authorIds)
        );

        existingWork.setAuthors(authors);

        return workRepository.save(existingWork);
    }

}