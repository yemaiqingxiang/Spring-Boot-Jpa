package com.example.demo.Entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Baby {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    private String name;
    @ManyToOne(targetEntity = Husband.class)
    @JoinColumn(name = "husband_Id")
    private Husband husband;
}
