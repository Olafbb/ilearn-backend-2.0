package com.ilearn.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Table(name = "HOMEWORK")
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class Homework {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "DEADLINE")
    private Date deadline;

    @Column(name = "ISDONE")
    private Boolean isDone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENT_ID")
    private Student student;

    @Column(name = "SUBJECT")
    private String subject;
}
