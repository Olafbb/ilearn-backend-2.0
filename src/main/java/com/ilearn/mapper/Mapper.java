package com.ilearn.mapper;

import com.ilearn.domain.*;
import com.ilearn.domain.dto.*;
import com.ilearn.domain.dto.interfaces.LessonsId;
import com.ilearn.domain.dto.interfaces.MarksId;
import com.ilearn.domain.dto.interfaces.StudentsId;
import com.ilearn.domain.dto.interfaces.TeachersId;
import com.ilearn.domain.interfaces.Lessons;
import com.ilearn.domain.interfaces.Marks;
import com.ilearn.domain.interfaces.Students;
import com.ilearn.domain.interfaces.Teachers;
import com.ilearn.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    private List<Student> getStudents(StudentsId studentsId) {
        return studentsId.getStudentsId().stream()
                .map(id -> dbService.getStudentDatabase().getStudent(id))
                .collect(Collectors.toList());
    }

    private List<Teacher> getTeachers(TeachersId teachersId) {
        return teachersId.getTeachersId().stream()
                .map(id -> dbService.getTeacherDatabase().getTeacher(id))
                .collect(Collectors.toList());
    }

    private List<Lesson> getLessons(LessonsId lessonsId) {
        return lessonsId.getLessonsId().stream()
                .map(id -> dbService.getLessonDatabase().getLesson(id))
                .collect(Collectors.toList());
    }

    private List<Mark> getMarks(MarksId marksId) {
        return marksId.getMarksId().stream()
                .map(id -> dbService.getMarkDatabase().getMark(id))
                .collect(Collectors.toList());
    }

    private List<Homework> getHomework(StudentDto studentDto) {
        return dbService.getHomeworkDatabase().getAllHomeworkByStudentId(studentDto.getId());
    }

    private List<String> getTeachersNames(Teachers teachers) {
        return teachers.getTeachers().stream()
                .map(teacher -> teacher.getName() + " " + teacher.getLastname())
                .collect(Collectors.toList());
    }

    private List<String> getStudentsNames(Students students) {
        return students.getStudents().stream()
                .map(student -> student.getName() + " " + student.getClassNumber())
                .collect(Collectors.toList());
    }

    private List<Long> getTeachersId(Teachers teachers) {
        return teachers.getTeachers().stream()
                .map(Teacher::getId)
                .collect(Collectors.toList());
    }

    private List<Long> getStudentsId(Students students) {
        return students.getStudents().stream()
                .map(Student::getId)
                .collect(Collectors.toList());
    }

    private List<String> getLessonsNames(Lessons lessons) {
        return lessons.getLessons().stream()
                .map(Lesson::getName)
                .collect(Collectors.toList());
    }

    private List<Long> getLessonsId(Lessons lessons) {
        return lessons.getLessons().stream()
                .map(Lesson::getId)
                .collect(Collectors.toList());
    }

    private List<Long> getMarksId(Marks marks) {
        return marks.getMarks().stream()
                .map(Mark::getId)
                .collect(Collectors.toList());
    }
}
