package com.maistruk.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.maistruk.model.Student;

@Repository
public class StudentDaoImpl implements StudentDao{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private StudentRowMapper studentRowMapper;
    
    private static final String CREATE_STUDENT = "INSERT INTO students (id, first_name, last_name) VALUES (DEFAULT, ?, ?)";
    private static final String UPDATE_STUDENT = "UPDATE students SET first_name = ?, last_name = ? WHERE id = ?";
    private static final String GET_STUDENT_BY_ID = "SELECT * FROM students WHERE id = ?";
    private static final String GET_ALL_STUDENTS = "SELECT * FROM students";
    private static final String DELETE_STUDENT = "DELETE FROM students WHERE id = ?";
    private static final String GET_STUDENT_BY_FULLNAME = "SELECT * FROM students WHERE first_name = ? AND last_name = ?";


    @Override
    public void save(Student student) {
        jdbcTemplate.update(CREATE_STUDENT, student.getFirstName(), student.getLastName());
    }

    @Override
    public void update(Student student) {
        jdbcTemplate.update(UPDATE_STUDENT, student.getFirstName(), student.getLastName(), student.getId());        
    }

    @Override
    public Student findById(Integer id) {
        List<Student> students = jdbcTemplate.query(GET_STUDENT_BY_ID, studentRowMapper, id);
        if (students.isEmpty()) {
            return null;
        }
        return students.get(0);
    }

    @Override
    public Student findByFullName(String firstName, String lastName) {
        List<Student> students = jdbcTemplate.query(GET_STUDENT_BY_FULLNAME, studentRowMapper, firstName, lastName);
        if (students.isEmpty()) {
            return null;
        }
        return students.get(0);
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(DELETE_STUDENT, id);
        
    }

    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query(GET_ALL_STUDENTS, studentRowMapper);

    }

}
