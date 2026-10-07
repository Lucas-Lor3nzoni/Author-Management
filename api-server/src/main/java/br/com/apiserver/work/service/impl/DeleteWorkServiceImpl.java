package br.com.apiserver.work.service.impl;

import br.com.apiserver.work.exception.WorkNotFoundException;
import br.com.apiserver.work.repository.WorkRepository;
import br.com.apiserver.work.service.DeleteWorkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class DeleteWorkServiceImpl implements DeleteWorkService {

    private final WorkRepository workRepository;

    @Transactional
    @Override
    public void deleteWorkById(Long id) {
        log.debug("Deleting work with id {}", id);

        var work = workRepository.findById(id)
                .orElseThrow(() -> {
                    log.debug("Work with id {} not found", id);
                    return new WorkNotFoundException("Work not found");
                });

        workRepository.delete(work);
    }

}