package com.example.demo.faculty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,Integer> {

    @Query("SELECT f FROM Faculty f WHERE f.name = ?1")
    Optional<Faculty> findFacultyByName(String name);
}
