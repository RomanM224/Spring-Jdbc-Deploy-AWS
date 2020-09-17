package com.maistruk.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.maistruk.dao.StudentDao;
import com.maistruk.exception.StudentException;
import com.maistruk.model.Student;
import com.maistruk.service.StudentService;

@Controller
public class StudentController {
    
    @Autowired
    StudentDao repository;
    
    @Autowired
    StudentService service;
    
    @RequestMapping("/")
    public ModelAndView home() {
        return new ModelAndView("/home.html");
    }
    
    @GetMapping(value = "/create")
    public ModelAndView create() {
        return new ModelAndView("/create.html");
    }
    
    @PostMapping(value = "/create")
    public ModelAndView create(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        try {
            service.create(student);
            ModelAndView modelAndView = new ModelAndView("/info");
            return modelAndView.addObject("info", "Student created");
        } catch (StudentException exception) {
            ModelAndView modelAndView = new ModelAndView("/create");
            return modelAndView.addObject("info", exception.getMessage());
        }
    }
    
    @GetMapping(value = "/update")
    public ModelAndView update() {
        return new ModelAndView("/update.html");
    }
    
    @PostMapping(value = "/update")
    public ModelAndView update(@RequestParam("id") Integer id, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Student student = new Student();
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        try {
            service.update(student);
            ModelAndView modelAndView = new ModelAndView("/info");
            return modelAndView.addObject("info", "Student updated");
        } catch (StudentException exception) {
            ModelAndView modelAndView = new ModelAndView("/update");
            return modelAndView.addObject("info", exception.getMessage());
        }
    }
    
    @GetMapping(value = "/showAll")
    public ModelAndView showAll() {
        ModelAndView modelAndView = new ModelAndView("/showAll.html");
        List<Student> students = service.getAll();
        modelAndView.addObject("students", students);
        return modelAndView;
    }
    
    @GetMapping(value = "/getById")
    public ModelAndView getById() {
        return new ModelAndView("/getById.html");
    }
    
    @PostMapping(value = "/getById")
    public ModelAndView getById(@RequestParam("id") Integer id) {
        List<Student> students = new ArrayList<>();
        Student student;
        try {
            student = service.getById(id);
            students.add(student);
            ModelAndView modelAndView = new ModelAndView("/showAll.html");
            return modelAndView.addObject("students", students);
        } catch (StudentException exception) {
            ModelAndView modelAndView = new ModelAndView("/getById.html");
            return modelAndView.addObject("info", exception.getMessage());
        }
    }
    
    @GetMapping(value = "/delete")
    public ModelAndView delete() {
        return new ModelAndView("/delete.html");
    }
    
    @PostMapping(value = "/delete")
    public ModelAndView delete(@RequestParam("id") Integer id) {
        try {
            service.delete(id);
            ModelAndView modelAndView = new ModelAndView("/info.html");
            return modelAndView.addObject("info", "Student deleted");
        } catch (StudentException e) {
            ModelAndView modelAndView = new ModelAndView("/delete.html");
            return modelAndView.addObject("info", "Student not found");
        } 
    }
}
