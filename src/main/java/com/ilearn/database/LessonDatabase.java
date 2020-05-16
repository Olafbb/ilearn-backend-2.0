package com.ilearn.database;

import com.ilearn.domain.Lesson;
import com.ilearn.repository.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonDatabase {
    private LessonRepository lessonRepository;

    public LessonDatabase(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public Lesson getLesson(Long id) {
        return lessonRepository.findLessonById(id).orElse(new Lesson());
    }

    public Lesson saveLesson(final Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }

    public List<Lesson> findAllByStudentId(Long id) {
        return lessonRepository.findAllByStudentsId(id);
    }

    public List<Lesson> getAllLessonsForStudent(Long id, String day, String classNumber) {
        return lessonRepository.findAllByStudentsIdAndDayAndClassNumber(id, day, classNumber);
    }

}

