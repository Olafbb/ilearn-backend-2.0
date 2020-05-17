package com.ilearn.domain;

import com.ilearn.domain.interfaces.Lessons;
import com.ilearn.domain.interfaces.Marks;
import com.ilearn.domain.interfaces.Teachers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Table(name = "STUDENT")
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class Student implements Lessons, Teachers, Marks {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LASTNAME")
    private String lastname;

    @Column(name = "CLASSNUMBER")
    private String classNumber;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "students")
    private List<Lesson> lessons;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "students")
    private List<Teacher> teachers;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Mark> marks;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Homework> homeworkList;

}
