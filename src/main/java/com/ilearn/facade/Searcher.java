package com.ilearn.facade;

import com.ilearn.domain.*;
import com.ilearn.domain.dto.LessonDto;
import com.ilearn.domain.dto.StudentDto;
import com.ilearn.domain.dto.TeacherDto;
import com.ilearn.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Searcher {
    @Autowired
    DbService dbService;

    public List<Student> searchStudents(LessonDto lessonDto) {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < lessonDto.getStudentsId().size(); i++) {
            students.add(dbService.getStudentDatabase().getStudent(lessonDto.getStudentsId().get(i)));
        }
        return students;
    }

    public List<Teacher> searchTeachers(LessonDto lessonDto) {
        List<Teacher> teachers = new ArrayList<>();
        for (int i = 0; i < lessonDto.getStudentsId().size(); i++) {
            teachers.add(dbService.getTeacherDatabase().getTeacher(lessonDto.getTeachersId().get(i)));
        }
        return teachers;
    }

    public List<Lesson> searchLessons(StudentDto studentDto) {
        List<Lesson> lessons = new ArrayList<>();
        for (int i = 0; i < studentDto.getLessonsId().size(); i++) {
            lessons.add(dbService.getLessonDatabase().getLesson(studentDto.getLessonsId().get(i)));
        }
        return lessons;
    }

    public List<Lesson> searchLessons(TeacherDto teacherDto) {
        List<Lesson> lessons = new ArrayList<>();
        for (int i = 0; i < teacherDto.getLessonsId().size(); i++) {
            lessons.add(dbService.getLessonDatabase().getLesson(teacherDto.getLessonsId().get(i)));
        }
        return lessons;
    }

    public List<Student> searchStudents(TeacherDto teacherDto) {
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < teacherDto.getStudentsId().size(); i++) {
            students.add(dbService.getStudentDatabase().getStudent(teacherDto.getStudentsId().get(i)));
        }
        return students;
    }

    public List<Teacher> searchTeachers(StudentDto studentDto) {
        List<Teacher> teachers = new ArrayList<>();
        for (int i = 0; i < studentDto.getLessonsId().size(); i++) {
            teachers.add(dbService.getTeacherDatabase().getTeacher(studentDto.getTeachersId().get(i)));
        }
        return teachers;
    }

    public List<Long> searchStudentsId(Lesson lesson) {
        List<Long> studentsId = new ArrayList<>();
        for (int i = 0; i < lesson.getStudents().size(); i++)
            studentsId.add(lesson.getStudents().get(i).getId());
        return studentsId;
    }

    //
//    public List<String> searchStudentsNames(Lesson lesson) {
//        List<String> studentsNames = new ArrayList<>();
//        lesson.getStudents().forEach(student -> {
//            studentsNames.add(student.getName() + " " + student.getClassNumber());
//        });
//        return studentsNames;
//    }
//    public Homework searchHomework(LessonDto lessonDto) {
//        return dbService.getHomeworkDatabase().getHomework(lessonDto.getHomeworkId());
//    }
//
//    public Long searchHomeworkId(Lesson lesson) {
//        return lesson.getHomework().getId();
//    }

    public List<Mark> searchMarks(StudentDto studentDto) {
        List<Mark> marks = new ArrayList<>();
        for (int i = 0; i < studentDto.getLessonsId().size(); i++) {
            marks.add(dbService.getMarkDatabase().getMark(studentDto.getMarksId().get(i)));
        }
        return marks;
    }

    public List<Homework> searchHomeworkA(StudentDto studentDto) {
        return dbService.getHomeworkDatabase().getAllHomeworkByStudentId(studentDto.getId());
    }
}
