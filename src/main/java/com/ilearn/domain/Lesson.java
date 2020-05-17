package com.ilearn.domain;

import com.ilearn.domain.interfaces.Students;
import com.ilearn.domain.interfaces.Teachers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "LESSON")
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class Lesson implements Students, Teachers {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DATE")
    private Date date;

    @Column(name = "DURATION")
    private int duration;

    @Column(name = "TOPIC")
    private String topic;

    @Column(name = "DAY")
    private String day;

    @Column(name = "LESSONNR")
    private Integer lessonNr;

    @Column(name = "LINK")
    private String link;

    @Column(name = "CLASSNUMBER")
    private String classNumber;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "lessons")
    private List<Teacher> teachers;

    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "JOIN_LESSON_STUDENTS",
            joinColumns = {@JoinColumn(name = "LESSON_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")}
    )
    private List<Student> students = new ArrayList<>();

}
