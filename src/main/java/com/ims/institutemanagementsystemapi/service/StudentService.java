package com.ims.institutemanagementsystemapi.service;

import com.ims.institutemanagementsystemapi.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class StudentService {

    private List<Student> listOfStudents = new CopyOnWriteArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private int currId = 1;

    public List<Student> getAllStudents() {
        logger.info("Getting all the students");
        return listOfStudents;
    }

    public Student getStudent(int id) {
        Optional<Student> foundStudent = listOfStudents.stream().filter(
                (currentStudent) -> {
                    return currentStudent.id == id;
                }
        ).findFirst();
        logger.info("Getting the student with the id: "+id);
        return foundStudent.orElse(null);


    }

    public Student createStudent(Student currStudent) {
        currStudent.id = this.currId++;
        listOfStudents.add(currStudent);
        logger.info("Created student with the id: " + currStudent.id);
        return currStudent;
    }

    public Student updateStudent(int id, Student upToDateStudent) {
        Student foundStudent = getStudent(id);
        foundStudent.name = upToDateStudent.name;
        foundStudent.email = upToDateStudent.email;
        logger.info("Update the student info the id: " + id);
        return foundStudent;
    }

    public Student deleteStudent(int id) {
        Student foundStudent = getStudent(id);
        listOfStudents.remove(foundStudent);
        logger.info("Delete the student the id: " + id);
        return foundStudent;
    }
}
