package com.ilearn.repository;

import com.ilearn.domain.Teacher;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    @Override
    List<Teacher> findAll();

    Optional<Teacher> findTeacherById(Long id);

    @Override
    Teacher save(Teacher teacher);

    @Override
    void deleteById(Long id);

    @Override
    long count();
}
