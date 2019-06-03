package com.example.demo.Entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Student {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    @ManyToMany()
    @JoinTable(name = "stu_course",
            joinColumns = {@JoinColumn(name = "c_id")},
            inverseJoinColumns = {@JoinColumn(name = "s_id")})
    private List<Course> courses;
}
