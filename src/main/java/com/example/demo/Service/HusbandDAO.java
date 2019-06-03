package com.example.demo.Service;

import com.example.demo.Entity.Baby;
import com.example.demo.Entity.Husband;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface HusbandDAO extends JpaRepository<Husband,String> {
    List<Husband> findAllByWifeNameLike(String wifeName);

}
