package br.com.apiserver.work.service;

import br.com.apiserver.work.model.Work;

import java.util.Set;

public interface UpdateWorkService {

    Work update(Work work, Long id, Set<Long> authorIds);

}