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
    private StudentService studentService;

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(path = "/{id}")
    public Student getStudent(@PathVariable(name = "id") int id) {
        return studentService.getStudent(id);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student currStudent) {
        return studentService.createStudent(currStudent);
    }

    @PutMapping(path = "/{id}")
    public Student updateStudent(@PathVariable(name = "id") int id, @RequestBody Student currStudent) {
        return studentService.updateStudent(id, currStudent);
    }

    @DeleteMapping(path = "/{id}")
    public Student deleteStudent(@PathVariable(name = "id") int id) {
        return studentService.deleteStudent(id);
    }
}
