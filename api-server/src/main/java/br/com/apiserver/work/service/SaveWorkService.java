package br.com.apiserver.work.service;

import br.com.apiserver.work.model.Work;

import java.util.Set;

public interface SaveWorkService {

    Work save(Work work, Set<Long> authorIds);

}