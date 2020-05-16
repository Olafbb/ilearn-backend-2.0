package com.ilearn.repository;

import com.ilearn.domain.Student;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface StudentRepository extends CrudRepository<Student, Long> {
    @Override
    List<Student> findAll();

    Optional<Student> findStudentById(Long id);

    List<Student> findAllById(Long id);

    @Override
    Student save(Student student);

    @Override
    void deleteById(Long id);

    @Override
    long count();

    boolean existsById(Long id);
}
