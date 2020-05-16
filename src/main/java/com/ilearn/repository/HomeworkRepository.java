package com.ilearn.repository;

import com.ilearn.domain.Homework;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface HomeworkRepository extends CrudRepository<Homework, Long> {
    @Override
    List<Homework> findAll();

    List<Homework> findAllByStudentId(Long id);

    List<Homework> findAllByStudentIdAndIsDone(Long id, Boolean isDone);

    Optional<Homework> findById(Long id);

    @Override
    Homework save(Homework homework);

    @Override
    void deleteById(Long id);

    @Override
    long count();
}
