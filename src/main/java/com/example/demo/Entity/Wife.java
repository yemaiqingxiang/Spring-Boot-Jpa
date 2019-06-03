package com.example.demo.Entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Wife {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    private String name;
    @OneToOne(mappedBy = "wife")
    private Husband husband;
}
