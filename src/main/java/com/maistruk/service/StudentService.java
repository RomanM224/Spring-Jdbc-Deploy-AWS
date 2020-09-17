package com.maistruk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maistruk.dao.StudentDao;
import com.maistruk.exception.StudentException;
import com.maistruk.model.Student;

@Service
public class StudentService {

    @Autowired
    StudentDao repository;

    public void create(Student student) throws StudentException {
        if (repository.findByFullName(student.getFirstName(), student.getLastName()) != null) {
            throw new StudentException("Student with this name already exist");
        }
        repository.save(student);
    }
    
    public void update(Student student) throws StudentException {
        if(repository.findById(student.getId()) == null) {
            throw new StudentException("Student not found");
        }
        if (repository.findByFullName(student.getFirstName(), student.getLastName()) != null) {
            throw new StudentException("Student with this name already exist");
        }
        repository.save(student);
    }

    public Student getById(Integer id) throws StudentException {
        Student student = repository.findById(id);
        if(student == null) {
            throw new StudentException("Student not found");
        }
        return student;
    }
    
    public List<Student> getAll(){
        return repository.findAll();
    }
    
    public void delete(Integer id) throws StudentException {
        if(repository.findById(id) == null) {
            throw new StudentException("Student not found");
        }
        repository.delete(id);
    }

}
