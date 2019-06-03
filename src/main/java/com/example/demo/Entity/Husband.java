package com.example.demo.Entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Husband {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    private String name;
    @OneToOne()
    @JoinColumn(name = "wife_id")
    private Wife wife;
    @OneToMany(targetEntity = Baby.class,mappedBy = "husband")
    private List<Baby> baby;



}

