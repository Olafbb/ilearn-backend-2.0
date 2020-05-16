package com.ilearn.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "TEACHER")
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class Teacher {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LASTNAME")
    private String lastname;

    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "JOIN_TEACHER_LESSONS",
            joinColumns = {@JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "LESSONS_ID", referencedColumnName = "ID")})
    private List<Lesson> lessons;

    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "JOIN_TEACHER_STUDENTS",
            joinColumns = {@JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")})
    private List<Student> students = new ArrayList<>();

}
