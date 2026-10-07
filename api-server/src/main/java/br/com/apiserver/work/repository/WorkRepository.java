package br.com.apiserver.work.repository;

import br.com.apiserver.work.model.Work;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {

    Page<Work> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Work> findByAuthorsNameContainingIgnoreCase(String name, Pageable pageable);

}