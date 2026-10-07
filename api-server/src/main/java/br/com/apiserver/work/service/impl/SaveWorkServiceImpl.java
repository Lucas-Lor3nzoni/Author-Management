package br.com.apiserver.work.service.impl;

import br.com.apiserver.author.repository.AuthorRepository;
import br.com.apiserver.work.model.Work;
import br.com.apiserver.work.repository.WorkRepository;
import br.com.apiserver.work.service.SaveWorkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Slf4j
@Service
public class SaveWorkServiceImpl implements SaveWorkService {

    private final WorkRepository workRepository;
    private final AuthorRepository authorRepository;

    @Transactional
    @Override
    public Work save(Work work, Set<Long> authorIds) {
        log.info("Saving work {}", work.getTitle());

        if (authorIds == null || authorIds.isEmpty()) {
            throw new IllegalArgumentException("At least one author is necessary");
        }

        var authors = authorRepository.findAllById(authorIds);

        if (authors.size() != authorIds.size()) {
            throw new IllegalArgumentException("One or more authors not found");
        }

        work.setAuthors(new HashSet<>(authors));

        var saved = workRepository.save(work);

        log.info("Work {} saved successfully", saved.getId());

        return saved;
    }

}