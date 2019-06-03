package com.example.demo.Service;

import com.example.demo.Entity.Baby;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface BabyDAO extends JpaRepository<Baby,String> {
    List<Baby> findAllByName(String id);
    List<Baby> findAllByHusbandId(String id);
}
