package com.ilearn.mapper;

import com.ilearn.domain.*;
import com.ilearn.domain.dto.*;
import com.ilearn.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {
    @Autowired
    private DbService dbService;

    public Lesson mapToLesson(final LessonDto lessonDto) {
        return new Lesson(
                lessonDto.getId(),
                lessonDto.getName(),
                lessonDto.getDate(),
                lessonDto.getDuration(),
                lessonDto.getTopic(),
                lessonDto.getDay(),
                lessonDto.getLessonNr(),
                lessonDto.getLink(),
                lessonDto.getClassNumber(),
                getTeachers(lessonDto),
                getStudents(lessonDto)
        );
    }
    public LessonDto mapToLessonDto(Lesson lesson) {
        return new LessonDto(
                lesson.getId(),
                lesson.getName(),
                lesson.getDate(),
                lesson.getDuration(),
                lesson.getTopic(),
                lesson.getDay(),
                lesson.getLessonNr(),
                lesson.getLink(),
                lesson.getClassNumber(),
                getStudentsNames(lesson),
                getStudentsId(lesson),
                getTeachersNames(lesson),
                getTeachersId(lesson));
    }

    public List<LessonDto> mapToLessonDtoList(final List<Lesson> lessons) {
        return lessons.stream()
                .map(this::mapToLessonDto)
                .collect(Collectors.toList());
    }

    public Student mapToStudent(final StudentDto studentDto) {
        return new Student(
                studentDto.getId(),
                studentDto.getName(),
                studentDto.getLastname(),
                studentDto.getClassNumber(),
                getLessons(studentDto),
                getTeachers(studentDto),
                dbService.getUserDatabase().getUser(studentDto.getUserId()),
                getMarks(studentDto),
                getHomework(studentDto)
        );
    }

    public StudentDto mapToStudentDto(final Student student) {
        return new StudentDto(
                student.getId(),
                student.getName(),
                student.getLastname(),
                student.getClassNumber(),
                getLessonsNames(student),
                getTeachersNames(student),
                student.getUser().getId(),
                getLessonsId(student),
                getTeachersId(student),
                getMarksId(student)
        );
    }

    public List<StudentDto> mapToStudentDtoList(final List<Student> students) {
        return students.stream()
                .map(this::mapToStudentDto)
                .collect(Collectors.toList());
    }


    public Teacher mapToTeacher(final TeacherDto teacherDto) {
        return new Teacher(
                teacherDto.getId(),
                teacherDto.getName(),
                teacherDto.getLastname(),
                getLessons(teacherDto),
                getStudents(teacherDto)
        );
    }

    public TeacherDto mapToTeacherDto(Teacher teacher) {
        return new TeacherDto(
                teacher.getId(),
                teacher.getName(),
                teacher.getLastname(),
                getLessonsNames(teacher),
                getStudentsNames(teacher),
                getLessonsId(teacher),
                getStudentsId(teacher)
        );
    }

    public List<TeacherDto> mapToTeacherDtoList(final List<Teacher> teachers) {
        return teachers.stream()
                .map(this::mapToTeacherDto)
                .collect(Collectors.toList());
    }

//    public Mark mapToMark(MarkDto markDto) {
//        return new Mark(
//                markDto.getId(),
//                markDto.getValue(),
//                markDto.getDescription(),
//                markDto.getSubject(),
//                Date.from(Instant.now()),
//                dbService.getStudentDatabase().getStudent(markDto.getStudentId()));
//    }

    public MarkDto mapToMarkDto(Mark mark) {
        return new MarkDto(
                mark.getId(),
                mark.getValue(),
                mark.getDescription(),
                mark.getSubject(),
                mark.getDate(),
                mark.getStudent().getId());
    }

    public List<MarkDto> mapToMarkDtoList(List<Mark> marks) {
        return marks.stream()
                .map(this::mapToMarkDto)
                .collect(Collectors.toList());
    }

    public Homework mapToHomework(HomeworkDto homeworkDto) {
        return new Homework(
                homeworkDto.getId(),
                homeworkDto.getContent(),
                homeworkDto.getDeadline(),
                homeworkDto.getIsDone(),
                dbService.getStudentDatabase().getStudent(homeworkDto.getStudentId()),
                homeworkDto.getSubject());
    }

    public HomeworkDto mapToHomeworkDto(Homework homework) {
        return new HomeworkDto(
                homework.getId(),
                homework.getContent(),
                homework.getDeadline(),
                homework.getIsDone(),
                homework.getStudent().getId(),
                homework.getSubject());
    }

    public List<HomeworkDto> mapToHomeworkDtoList(List<Homework> homeworkList) {
        return homeworkList.stream()
                .map(this::mapToHomeworkDto)
                .collect(Collectors.toList());
    }

    public User mapToUser(UserDto userDto) {
        return new User(userDto.getId(), userDto.getEmail(), userDto.getPassword(), dbService.getStudentDatabase().getStudent(userDto.getStudentId()));
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getPassword(), user.getStudent().getId());
    }

    public List<UserDto> mapToUserDtoList(List<User> users) {
        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    private List<Student> getStudents(LessonDto lessonDto) {
        return lessonDto.getStudentsId().stream()
                .map(id -> dbService.getStudentDatabase().getStudent(id))
                .collect(Collectors.toList());
    }

    private List<Teacher> getTeachers(LessonDto lessonDto) {
        return lessonDto.getTeachersId().stream()
                .map(id -> dbService.getTeacherDatabase().getTeacher(id))
                .collect(Collectors.toList());
    }
    private List<Lesson> getLessons(StudentDto studentDto) {
        return studentDto.getLessonsId().stream()
                .map(id -> dbService.getLessonDatabase().getLesson(id))
                .collect(Collectors.toList());
    }

    private List<Lesson> getLessons(TeacherDto teacherDto) {
        return teacherDto.getLessonsId().stream()
                .map(id -> dbService.getLessonDatabase().getLesson(id))
                .collect(Collectors.toList());
    }

    private List<Student> getStudents(TeacherDto teacherDto) {
        return teacherDto.getStudentsId().stream()
                .map(id -> dbService.getStudentDatabase().getStudent(id))
                .collect(Collectors.toList());
    }

    private List<Teacher> getTeachers(StudentDto studentDto) {
        return studentDto.getTeachersId().stream()
                .map(id -> dbService.getTeacherDatabase().getTeacher(id))
                .collect(Collectors.toList());
    }

    private List<Mark> getMarks(StudentDto studentDto) {
        return studentDto.getMarksId().stream()
                .map(id -> dbService.getMarkDatabase().getMark(id))
                .collect(Collectors.toList());
    }

    private List<Homework> getHomework(StudentDto studentDto) {
        return dbService.getHomeworkDatabase().getAllHomeworkByStudentId(studentDto.getId());
    }

    private List<String> getTeachersNames(Lesson lesson){
        return lesson.getTeachers().stream()
                .map(teacher -> teacher.getName() + " " + teacher.getLastname())
                .collect(Collectors.toList());
    }

    private List<String> getStudentsNames(Lesson lesson){
        return lesson.getStudents().stream()
                .map(student -> student.getName() + " " + student.getClassNumber())
                .collect(Collectors.toList());
    }
    private List<Long> getTeachersId(Lesson lesson) {
        return lesson.getTeachers().stream()
                .map(Teacher::getId)
                .collect(Collectors.toList());
    }
    private List<Long> getStudentsId(Lesson lesson) {
        return lesson.getStudents().stream()
                .map(Student::getId)
                .collect(Collectors.toList());
    }
    private List<String> getTeachersNames(Student student){
        return student.getTeachers().stream()
                .map(teacher -> teacher.getName() + " " + teacher.getLastname())
                .collect(Collectors.toList());
    }
    private List<String> getLessonsNames(Student student){
        return student.getLessons().stream()
                .map(Lesson::getName)
                .collect(Collectors.toList());
    }
    private List<Long> getTeachersId(Student student) {
        return student.getTeachers().stream()
                .map(Teacher::getId)
                .collect(Collectors.toList());
    }
    private List<Long> getLessonsId(Student student) {
        return student.getLessons().stream()
                .map(Lesson::getId)
                .collect(Collectors.toList());
    }
    private List<Long> getMarksId(Student student) {
        return student.getMarks().stream()
                .map(Mark::getId)
                .collect(Collectors.toList());
    }
    private List<Long> getStudentsId(Teacher teacher) {
        return teacher.getStudents().stream()
                .map(Student::getId)
                .collect(Collectors.toList());
    }

    private List<Long> getLessonsId(Teacher teacher) {
        return teacher.getLessons().stream()
                .map(Lesson::getId)
                .collect(Collectors.toList());
    }

    private List<String> getStudentsNames(Teacher teacher) {
        return teacher.getStudents().stream()
                .map(student -> student.getName() + " " + student.getLastname())
                .collect(Collectors.toList());
    }

    private List<String> getLessonsNames(Teacher teacher) {
        return teacher.getLessons().stream()
                .map(Lesson::getName)
                .collect(Collectors.toList());
    }
}
