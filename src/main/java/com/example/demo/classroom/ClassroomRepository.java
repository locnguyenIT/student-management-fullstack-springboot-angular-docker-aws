package com.example.demo.classroom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom,Integer> { //Spring Data JPA

    @Query("SELECT c FROM Classroom c WHERE c.name = ?1")
    Optional<Classroom> findClassroomByName(String name);

}
