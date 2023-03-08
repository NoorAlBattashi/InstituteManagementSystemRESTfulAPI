/**
 * This package contains the service layer of the Institute Management System API.
 * The StudentService class is responsible for managing the list of students and
 * providing CRUD operations for student data.
 */
package com.ims.institutemanagementsystemapi.service;

import com.ims.institutemanagementsystemapi.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * The StudentService class provides CRUD operations for student data.
 * This class manages the list of students and interacts with the DAO layer.
 * This class is annotated with @Service, making it a Spring-managed bean.
 */
@Service
public class StudentService {

    private List<Student> listOfStudents = new CopyOnWriteArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private int currId = 1;

    /**
     * Returns a list of all students in the system.
     *
     * @return a list of all students
     */
    public List<Student> getAllStudents() {
        logger.info("Getting all the students");
        return listOfStudents;
    }

    /**
     * Retrieves a student by their unique id.
     *
     * @param id the unique id of the student
     * @return the student object associated with the id, or null if no such student exists
     */
    public Student getStudent(int id) {
        Optional<Student> foundStudent = listOfStudents.stream().filter(
                (currentStudent) -> {
                    return currentStudent.id == id;
                }
        ).findFirst();
        logger.info("Getting the student with the id: " + id);
        return foundStudent.orElse(null);


    }

    /**
     * Adds a new student to the system.
     *
     * @param currStudent the student object to add to the system
     * @return the student object that was added to the system, including its generated unique id
     */
    public Student createStudent(Student currStudent) {
        currStudent.id = this.currId++;
        listOfStudents.add(currStudent);
        logger.info("Created student with the id: " + currStudent.id);
        return currStudent;
    }

    /**
     * Updates an existing student in the system.
     *
     * @param id              the unique id of the student to update
     * @param upToDateStudent the updated student object to replace the existing one
     * @return the updated student object
     */
    public Student updateStudent(int id, Student upToDateStudent) {
        Student foundStudent = getStudent(id);
        foundStudent.name = upToDateStudent.name;
        foundStudent.email = upToDateStudent.email;
        logger.info("Update the student info the id: " + id);
        return foundStudent;
    }

    /**
     * Deletes a student from the system.
     *
     * @param id the unique id of the student to delete
     * @return the student object that was deleted from the system
     */
    public Student deleteStudent(int id) {
        Student foundStudent = getStudent(id);
        listOfStudents.remove(foundStudent);
        logger.info("Delete the student the id: " + id);
        return foundStudent;
    }
}
