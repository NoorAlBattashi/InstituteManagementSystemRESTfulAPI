/**
 * This class represents the controller for Student entities in the Institute Management System API.
 * It provides endpoints for accessing and modifying student information, utilizing the StudentService class.
 */
package com.ims.institutemanagementsystemapi.controller;

import com.ims.institutemanagementsystemapi.model.Student;
import com.ims.institutemanagementsystemapi.service.StudentService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }

    /**
     * Endpoint for retrieving a specific student by their ID.
     * @param id The ID of the desired student
     * @return The Student object associated with the given ID
     */
    @GetMapping(path = "/{id}")
    public Student getStudent(@PathVariable(name = "id") int id) {
        return studentService.getStudent(id);
    }

    /**
     * Endpoint for creating a new student.
     * @param currStudent The Student object containing the information for the new student
     * @return The newly created Student object
     */
    @PostMapping
    public Student createStudent(@RequestBody Student currStudent) {
        return studentService.createStudent(currStudent);
    }

    /**
     * Endpoint for updating an existing student.
     * @param id The ID of the student to be updated
     * @param currStudent The updated Student object
     * @return The updated Student object
     */
    @PutMapping(path = "/{id}")
    public Student updateStudent(@PathVariable(name = "id") int id, @RequestBody Student currStudent) {
        return studentService.updateStudent(id, currStudent);
    }

    /**
     * Endpoint for deleting an existing student.
     * @param id The ID of the student to be deleted
     * @return The deleted Student object
     */
    @DeleteMapping(path = "/{id}")
    public Student deleteStudent(@PathVariable(name = "id") int id) {
        return studentService.deleteStudent(id);
    }
}
