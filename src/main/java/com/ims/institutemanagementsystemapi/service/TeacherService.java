package com.ims.institutemanagementsystemapi.service;

import com.ims.institutemanagementsystemapi.model.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The TeacherService class is a service layer class that provides CRUD operations
 * for managing Teacher objects. This class uses a List data structure to store
 * and manage Teacher objects.
 */
@Service
public class TeacherService {
    // List to store Teacher objects
    private List<Teacher> listOfTeachers = new CopyOnWriteArrayList<>();
    // Logger object to log messages
    private static final Logger logger = LoggerFactory.getLogger(TeacherService.class);
    // Counter to generate unique ids for Teachers
    private int currId = 1;

    /**
     * Returns a list of all Teachers.
     *
     * @return a list of all Teachers
     */
    public List<Teacher> getAllTeachers() {
        logger.info("Getting all the teachers");
        return listOfTeachers;
    }

    /**
     * Returns a Teacher object with the specified id.
     *
     * @param id the id of the Teacher to retrieve
     * @return the Teacher object with the specified id, or null if no such Teacher exists
     */
    public Teacher getTeacher(int id) {
        Optional<Teacher> foundTeacher = listOfTeachers.stream().filter(
                (currentTeacher) -> {
                    return currentTeacher.id == id;
                }
        ).findFirst();
        logger.info("Getting the Teacher with the id: " + id);
        return foundTeacher.orElse(null);


    }

    /**
     * Creates a new Teacher object.
     *
     * @param currTeacher the Teacher object to create
     * @return the created Teacher object
     */
    public Teacher createTeacher(Teacher currTeacher) {
        currTeacher.id = this.currId++;
        listOfTeachers.add(currTeacher);
        logger.info("Created Teacher with the id: " + currTeacher.id);
        return currTeacher;
    }

    /**
     * Updates the information of an existing Teacher object.
     *
     * @param id              the id of the Teacher object to update
     * @param upToDateTeacher the updated Teacher object
     * @return the updated Teacher object
     */
    public Teacher updateTeacher(int id, Teacher upToDateTeacher) {
        Teacher foundTeacher = getTeacher(id);
        foundTeacher.name = upToDateTeacher.name;
        foundTeacher.email = upToDateTeacher.email;
        logger.info("Update the Teacher info the id: " + id);
        return foundTeacher;
    }

    /**
     * Deletes an existing Teacher object.
     *
     * @param id the id of the Teacher object to delete
     * @return the deleted Teacher object
     */
    public Teacher deleteTeacher(int id) {
        Teacher foundTeacher = getTeacher(id);
        listOfTeachers.remove(foundTeacher);
        logger.info("Delete the Teacher the id: " + id);
        return foundTeacher;
    }
}
