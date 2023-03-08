package com.ims.institutemanagementsystemapi.controller;

import com.ims.institutemanagementsystemapi.model.Teacher;
import com.ims.institutemanagementsystemapi.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public List<Teacher> getTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping(path = "/{id}")
    public Teacher getTeacher(@PathVariable(name = "id") int id) {
        return teacherService.getTeacher(id);
    }

    @PostMapping
    public Teacher createTeacher(@RequestBody Teacher currTeacher) {
        return teacherService.createTeacher(currTeacher);
    }

    @PutMapping(path = "/{id}")
    public Teacher updateTeacher(@PathVariable(name = "id") int id, @RequestBody Teacher currTeacher) {
        return teacherService.updateTeacher(id, currTeacher);
    }

    @DeleteMapping(path = "/{id}")
    public Teacher deleteTeacher(@PathVariable(name = "id") int id) {
        return teacherService.deleteTeacher(id);
    }
}
