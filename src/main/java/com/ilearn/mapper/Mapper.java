package com.ilearn.mapper;

import com.ilearn.domain.*;
import com.ilearn.domain.dto.*;
import com.ilearn.facade.Searcher;
import com.ilearn.service.DbService;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Mapper {
    DbService dbService;
    Searcher searcher;

    public Mapper(DbService dbService, Searcher searcher) {
        this.dbService = dbService;
        this.searcher = searcher;
    }

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
                searcher.searchTeachers(lessonDto),
                searcher.searchStudents(lessonDto)
        );
    }

    public LessonDto mapToLessonDto(Lesson lesson) {
        List<String> teachersNames = new ArrayList<>();
        List<Long> teachersId = new ArrayList<>();
        List<String> studentsNames = new ArrayList<>();
        List<Long> studentsId = new ArrayList<>();
        lesson.getTeachers().forEach(teacher -> {
            teachersNames.add(teacher.getName() + " " + teacher.getLastname());
            teachersId.add(teacher.getId());
        });
        lesson.getStudents().forEach(student -> {
            studentsNames.add(student.getName() + " " + student.getClassNumber());
            studentsId.add(student.getId());
        });
//        Long lessonId = 0L;
//        if (!isNull(lesson.getHomework().getId()))
//            lesson.getHomework().getId();
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
                studentsNames,
                studentsId,
                teachersNames,
                teachersId);
    }

    public List<LessonDto> mapToLessonDtoList(final List<Lesson> lessons) {
        List<LessonDto> lessonsDto = new ArrayList<>();
        lessons.forEach(lesson -> lessonsDto.add(new Mapper(dbService, searcher).mapToLessonDto(lesson)));
        return lessonsDto;
    }

    public Student mapToStudent(final StudentDto studentDto) {
        return new Student(
                studentDto.getId(),
                studentDto.getName(),
                studentDto.getLastname(),
                studentDto.getClassNumber(),
                searcher.searchLessons(studentDto),
                searcher.searchTeachers(studentDto),
                dbService.getUserDatabase().getUser(studentDto.getUserId()),
                searcher.searchMarks(studentDto),
                searcher.searchHomeworkA(studentDto)
        );
    }

    public StudentDto mapToStudentDto(final Student student) {
        List<String> teachersNames = new ArrayList<>();
        List<String> lessonsNames = new ArrayList<>();
        List<Long> teachersId = new ArrayList<>();
        List<Long> lessonsId = new ArrayList<>();
        List<Long> marksId = new ArrayList<>();

        student.getTeachers().forEach(teacher -> {
            teachersNames.add(teacher.getName() + " " + teacher.getLastname());
            teachersId.add(teacher.getId());
        });
        student.getLessons().forEach(lesson -> {
            lessonsNames.add(lesson.getName() + " " + lesson.getClassNumber());
            lessonsId.add(lesson.getId());
        });
        student.getMarks().forEach(mark -> {
            marksId.add(mark.getId());
        });
        Long userId;
        if (student.getUser()==null) {
            userId = 0L;
        } else {
            userId = student.getUser().getId();
        }
        return new StudentDto(
                student.getId(),
                student.getName(),
                student.getLastname(),
                student.getClassNumber(),
                lessonsNames,
                teachersNames,
                userId,
                lessonsId,
                teachersId,
                marksId
        );
    }

    public List<StudentDto> mapToStudentDtoList(final List<Student> students) {
        List<StudentDto> studentsDto = new ArrayList<>();
        students.forEach(student -> studentsDto.add(new Mapper(dbService, searcher).mapToStudentDto(student)));
        return studentsDto;
    }


    public Teacher mapToTeacher(final TeacherDto teacherDto) {

        return new Teacher(
                teacherDto.getId(),
                teacherDto.getName(),
                teacherDto.getLastname(),
                searcher.searchLessons(teacherDto),
                searcher.searchStudents(teacherDto)
        );
    }

    public TeacherDto mapToTeacherDto(Teacher teacher) {
        List<String> lessonNames = new ArrayList<>();
        List<String> studentsNames = new ArrayList<>();
        List<Long> lessonsIdList = new ArrayList<>();
        List<Long> studentsIdList = new ArrayList<>();

        teacher.getStudents().forEach(student -> {
            studentsNames.add(student.getName() + " " + student.getLastname());
            studentsIdList.add(student.getId());
        });
        teacher.getLessons().forEach(lesson -> {
            lessonNames.add(lesson.getName() + " " + lesson.getClassNumber());
            lessonsIdList.add(lesson.getId());
        });
        return new TeacherDto(
                teacher.getId(),
                teacher.getName(),
                teacher.getLastname(),
                lessonNames,
                studentsNames,
                lessonsIdList,
                studentsIdList
        );
    }

    public List<TeacherDto> mapToTeacherDtoList(final List<Teacher> teachers) {
        List<TeacherDto> teachersDto = new ArrayList<>();
        teachers.forEach(teacher -> teachersDto.add(new Mapper(dbService, searcher).mapToTeacherDto(teacher)));
        return teachersDto;
    }

    public Mark mapToMark(MarkDto markDto) {
        return new Mark(
                markDto.getId(),
                markDto.getValue(),
                markDto.getDescription(),
                markDto.getSubject(),
                Date.from(Instant.now()),
                dbService.getStudentDatabase().getStudent(markDto.getStudentId()));
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
        List<MarkDto> marksDto = new ArrayList<>();
        marks.forEach(mark -> marksDto.add(new Mapper(dbService, searcher).mapToMarkDto(mark)));
        return marksDto;
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
        List<HomeworkDto> homeworkDtoList = new ArrayList<>();
        homeworkList.forEach(homework -> homeworkDtoList.add(new Mapper(dbService, searcher).mapToHomeworkDto(homework)));
        return homeworkDtoList;
    }

    public String mapToMembers(String members) {
        return members.replace("[", "")
                .replace("]", "");
    }

    public User mapToUser(UserDto userDto) {
        return new User(userDto.getId(), userDto.getEmail(), userDto.getPassword(), dbService.getStudentDatabase().getStudent(userDto.getStudentId()));
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getPassword(), user.getStudent().getId());
    }

    public List<UserDto> mapToUserDtoList(List<User> users) {
        List<UserDto> userDtoList = new ArrayList<>();
        users.forEach(user -> userDtoList.add(new Mapper(dbService, searcher).mapToUserDto(user)));
        return userDtoList;
    }
}
