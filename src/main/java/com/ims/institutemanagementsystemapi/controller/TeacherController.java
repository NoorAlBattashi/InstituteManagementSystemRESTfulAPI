/**
 * This class represents the controller for Teacher entities in the Institute Management System API.
 * It provides endpoints for accessing and modifying teacher information, utilizing the TeacherService class.
 */
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
    private TeacherService teacherService;// The TeacherService used for accessing teacher data

    /**
     * Endpoint for retrieving all teachers.
     * @return List of all teachers
     */
    @GetMapping
    public List<Teacher> getTeachers() {
        return teacherService.getAllTeachers();
    }

    /**
     * Endpoint for retrieving a specific teacher by their ID.
     * @param id The ID of the desired teacher
     * @return The Teacher object associated with the given ID
     */
    @GetMapping(path = "/{id}")
    public Teacher getTeacher(@PathVariable(name = "id") int id) {
        return teacherService.getTeacher(id);
    }

    /**
     * Endpoint for creating a new teacher.
     * @param currTeacher The Teacher object containing the information for the new teacher
     * @return The newly created Teacher object
     */
    @PostMapping
    public Teacher createTeacher(@RequestBody Teacher currTeacher) {
        return teacherService.createTeacher(currTeacher);
    }

    /**
     * Endpoint for updating an existing teacher.
     * @param id The ID of the teacher to be updated
     * @param currTeacher The updated Teacher object
     * @return The updated Teacher object
     */
    @PutMapping(path = "/{id}")
    public Teacher updateTeacher(@PathVariable(name = "id") int id, @RequestBody Teacher currTeacher) {
        return teacherService.updateTeacher(id, currTeacher);
    }

    /**
     * Endpoint for deleting an existing teacher.
     * @param id The ID of the teacher to be deleted
     * @return The deleted Teacher object
     */
    @DeleteMapping(path = "/{id}")
    public Teacher deleteTeacher(@PathVariable(name = "id") int id) {
        return teacherService.deleteTeacher(id);
    }
}
