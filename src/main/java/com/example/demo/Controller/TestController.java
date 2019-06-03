package com.example.demo.Controller;


import com.example.demo.Util.Result;
import com.example.demo.Util.ResultGenerator;
import com.example.demo.Entity.Baby;
import com.example.demo.Entity.Husband;
import com.example.demo.Entity.Student;
import com.example.demo.Service.BabyDAO;
import com.example.demo.Service.HusbandDAO;
import com.example.demo.Service.StudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    private HusbandDAO husbandDAO;
    @Autowired
    private BabyDAO babyDAO;
    @Autowired
    private StudentDAO studentDAO;

    /**
     * 保存
     */
    @GetMapping("/add")
    public Husband add() {
        Husband husband = new Husband();
        husband.setName("husband");
        return husbandDAO.save(husband);
    }


    /**
     * 一对多 , 一对一
     */
    @GetMapping("/get")
    @Transactional
    public Result get() {
        List<Husband> husband = husbandDAO.findAll();
        for (Husband husband1 : husband) {
            husband1.getBaby().size();
        }
        return ResultGenerator.genSuccessResult(husband);
    }

    /**
     * 条件查询
     */
    @GetMapping("/getById/{id}")
    @Transactional
    public Result getById(@PathVariable("id") String id) {
        Optional<Husband> husband = husbandDAO.findById(id);
        return ResultGenerator.genSuccessResult(husband.get());
    }

    /**
     * 多对一
     */
    @GetMapping("/getBaby")
    @Transactional
    public Result getBaby() {
        List<Baby> babyDAOAll = babyDAO.findAll();
        return ResultGenerator.genSuccessResult(babyDAOAll);
    }

    /**
     * 多对多
     */
    @GetMapping("/getStudent")
    @Transactional
    public Result getStudent() {
        List<Student> babyDAOAll = studentDAO.findAll();
        for (Student student : babyDAOAll) {
            student.getCourses().size();
        }
        return ResultGenerator.genSuccessResult(babyDAOAll);
    }

    /**
     * 分页
     */
    @GetMapping("/getPage")
    @Transient
    public Result getPage() {
        PageRequest pageRequest =  PageRequest.of(0, 2);
        Page<Husband> all = husbandDAO.findAll(pageRequest);
        return ResultGenerator.genSuccessResult(all);
    }

    /**
     * 关联条件查询(模糊查询)
     */
    @GetMapping("/getAllByWifeName/{name}")
    @Transient
    public Result getAllByWifeName(@PathVariable("name") String name) {
        List<Husband> all = husbandDAO.findAllByWifeNameLike("%" + name + "%");
        return ResultGenerator.genSuccessResult(all);
    }
}

