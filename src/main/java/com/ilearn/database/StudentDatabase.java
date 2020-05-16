package com.ilearn.database;

import com.ilearn.domain.Student;
import com.ilearn.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentDatabase {
    private StudentRepository studentRepository;

    public StudentDatabase(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudent(Long id) {
        return studentRepository.findStudentById(id).orElse(new Student());
    }

    public Student saveStudent(final Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> getAllStudentsById(Long id) {
        return studentRepository.findAllById(id);
    }

    public boolean isIdTaken(Long id) {
        return studentRepository.existsById(id);
    }

}
