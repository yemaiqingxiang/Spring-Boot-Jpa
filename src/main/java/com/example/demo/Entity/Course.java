package com.example.demo.Entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Data
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Course {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;
}
