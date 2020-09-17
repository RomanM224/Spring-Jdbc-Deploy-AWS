package com.maistruk.dao;

import java.util.List;

import com.maistruk.model.Student;

public interface StudentDao {
    
    void save(Student student);
    
    void update(Student student);
    
    Student findById(Integer id);
    
    Student findByFullName(String firstName, String lastName);
    
    void delete(Integer id);
    
    List<Student> findAll();

}
