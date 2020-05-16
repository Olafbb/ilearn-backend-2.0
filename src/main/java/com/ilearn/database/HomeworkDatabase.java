package com.ilearn.database;

import com.ilearn.domain.Homework;
import com.ilearn.repository.HomeworkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeworkDatabase {
    private HomeworkRepository homeworkRepository;

    public HomeworkDatabase(HomeworkRepository homeworkRepository) {
        this.homeworkRepository = homeworkRepository;
    }

    public List<Homework> getAllHomework() {
        return homeworkRepository.findAll();
    }

    public Homework getHomework(Long id) {
        return homeworkRepository.findById(id).orElse(new Homework());
    }

    public Homework saveHomework(final Homework homework) {
        return homeworkRepository.save(homework);
    }

    public void deleteHomework(Long id) {
        homeworkRepository.deleteById(id);
    }

    public List<Homework> getAllHomeworkByStudentId(Long id) {
        return homeworkRepository.findAllByStudentId(id);
    }

    public List<Homework> getAllHomeworkByStudentIdAndStatus(Long id, Boolean status) {
        return homeworkRepository.findAllByStudentIdAndIsDone(id, status);
    }

}
