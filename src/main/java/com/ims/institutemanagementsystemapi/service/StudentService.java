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
        try {
            logger.info("Getting all the students");
            return listOfStudents;
        } catch (Exception e) {
            logger.error("Error occurred while getting all students: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves a student by their unique id.
     *
     * @param id the unique id of the student
     * @return the student object associated with the id, or null if no such student exists
     */
    public Student getStudent(int id) {
        try {
            Optional<Student> foundStudent = listOfStudents.stream().filter(
                    (currentStudent) -> {
                        return currentStudent.id == id;
                    }
            ).findFirst();
            if (foundStudent.isPresent()) {
                logger.info("Getting the student with the id: " + id);
                return foundStudent.get();
            } else {
                logger.error("Error occurred while getting Student: Student with id " + id + " not found");
                return null;
            }
        } catch (Exception e) {
            logger.error("Error occurred while getting Student: " + e.getMessage());
            return null;
        }
    }

    /**
     * Adds a new student to the system.
     *
     * @param currStudent the student object to add to the system
     * @return the student object that was added to the system, including its generated unique id
     */
    public Student createStudent(Student currStudent) {
        try {
            currStudent.id = this.currId++;
            listOfStudents.add(currStudent);
            logger.info("Created student with the id: " + currStudent.id);
            return currStudent;
        } catch (Exception e) {
            logger.error("Error occurred while creating Student: " + e.getMessage());
            return null;
        }
    }

    /**
     * Updates an existing student in the system.
     *
     * @param id              the unique id of the student to update
     * @param upToDateStudent the updated student object to replace the existing one
     * @return the updated student object
     */
    public Student updateStudent(int id, Student upToDateStudent) {
        try {
            Student foundStudent = getStudent(id);
            foundStudent.name = upToDateStudent.name;
            foundStudent.email = upToDateStudent.email;
            logger.info("Update the student info the id: " + id);
            return foundStudent;
        } catch (Exception e) {
            logger.error("Error occurred while updating Student: " + e.getMessage());
            return null;
        }
    }

    /**
     * Deletes a student from the system.
     *
     * @param id the unique id of the student to delete
     * @return the student object that was deleted from the system
     */
    public Student deleteStudent(int id) throws Exception {
        Student foundStudent = getStudent(id);
        if (foundStudent == null) {
            throw new Exception("Student with id " + id + " not found");
        }
        boolean removed = listOfStudents.remove(foundStudent);
        if (!removed) {
            throw new Exception("Failed to delete student with id " + id);
        }
        logger.info("Deleted student with id: " + id);
        return foundStudent;
    }
}
