package com.ilearn.repository;

import com.ilearn.domain.Mark;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MarkRepository extends CrudRepository<Mark, Long> {
    @Override
    List<Mark> findAll();

    Optional<Mark> findById(Long id);

    @Override
    Mark save(Mark mark);

    @Override
    void deleteById(Long id);

    @Override
    long count();

    List<Mark> findAllByStudentId(Long id);
}
