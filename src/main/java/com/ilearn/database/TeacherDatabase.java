package com.ilearn.database;

import com.ilearn.domain.Teacher;
import com.ilearn.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherDatabase {
    private TeacherRepository teacherRepository;

    public TeacherDatabase(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacher(Long id) {
        return teacherRepository.findTeacherById(id).orElse(new Teacher());
    }

    public Teacher saveTeacher(final Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

}
