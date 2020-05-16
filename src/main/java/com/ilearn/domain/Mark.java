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
@Table(name = "MARK")
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class Mark {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "VALUE")
    private Integer value;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "DATE")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDENT_ID")
    private Student student;

}
