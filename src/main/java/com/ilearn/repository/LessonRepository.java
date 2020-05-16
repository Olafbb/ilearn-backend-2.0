package com.ilearn.repository;

import com.ilearn.domain.Lesson;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface LessonRepository extends CrudRepository<Lesson, Long> {
    @Override
    @Transactional
    List<Lesson> findAll();

    Optional<Lesson> findLessonById(Long id);

    @Override
    Lesson save(Lesson lesson);

    @Override
    void deleteById(Long id);

    @Override
    long count();

    List<Lesson> findAllByStudentsId(Long id);

    List<Lesson> findAllByStudentsIdAndDayAndClassNumber(Long Id, String day, String classNumber);
}
