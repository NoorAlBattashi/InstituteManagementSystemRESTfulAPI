/**
 * This class represents the controller for Student entities in the Institute Management System API.
 * It provides endpoints for accessing and modifying student information, utilizing the StudentService class.
 */
package com.ims.institutemanagementsystemapi.controller;

import com.ims.institutemanagementsystemapi.model.Student;
import com.ims.institutemanagementsystemapi.service.StudentService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;// The StudentService used for accessing student data

    /**
     * Endpoint for retrieving all students.
     * @return List of all students
     */
    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        try {
            List<Student> students = studentService.getAllStudents();
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoint for retrieving a specific student by their ID.
     * @param id The ID of the desired student
     * @return The Student object associated with the given ID
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable(name = "id") int id) {
        try {
            Student student = studentService.getStudent(id);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoint for creating a new student.
     * @param currStudent The Student object containing the information for the new student
     * @return The newly created Student object
     */
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student currStudent) {
        try {
            Student createdStudent = studentService.createStudent(currStudent);
            return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoint for updating an existing student.
     * @param id The ID of the student to be updated
     * @param currStudent The updated Student object
     * @return The updated Student object
     */
    @PutMapping(path = "/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(name = "id") int id, @RequestBody Student currStudent) {
        try {
            Student updatedStudent = studentService.updateStudent(id, currStudent);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Endpoint for deleting an existing student.
     * @param id The ID of the student to be deleted
     * @return The deleted Student object
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable(name = "id") int id) {
        try {
            Student deletedStudent = studentService.deleteStudent(id);
            return new ResponseEntity<>(deletedStudent, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
