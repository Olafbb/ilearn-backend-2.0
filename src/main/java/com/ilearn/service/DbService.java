package com.ilearn.service;

import com.ilearn.database.*;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class DbService {
    private LessonDatabase lessonDatabase;
    private StudentDatabase studentDatabase;
    private TeacherDatabase teacherDatabase;
    private UserDatabase userDatabase;
    private HomeworkDatabase homeworkDatabase;
    private MarkDatabase markDatabase;

    public DbService(LessonDatabase lessonDatabase,
                     StudentDatabase studentDatabase,
                     TeacherDatabase teacherDatabase,
                     UserDatabase userDatabase,
                     HomeworkDatabase homeworkDatabase,
                     MarkDatabase markDatabase) {
        this.lessonDatabase = lessonDatabase;
        this.studentDatabase = studentDatabase;
        this.teacherDatabase = teacherDatabase;
        this.userDatabase = userDatabase;
        this.homeworkDatabase = homeworkDatabase;
        this.markDatabase = markDatabase;
    }
}
