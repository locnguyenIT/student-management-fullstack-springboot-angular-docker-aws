package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //Data Access Layer
public interface StudentRepository extends JpaRepository<Student, Integer> { //Spring Data JPA

    //JPQL
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

    @Query(value = "SELECT * " +
            "FROM Student s JOIN Classroom c JOIN Faculty f " +
            "ON s.classroom_id = c.id and c.faculty_id = f.id " +
            "WHERE f.id = ?1",nativeQuery = true)
    List<Student> findStudentInWhichFaculty(int facultyId);




}
