package com.example.demo.result;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResultRepository extends JpaRepository<Result,Integer> {

    //Check Enrolment is present
    Optional<Result> findResultByStudentIdAndCourseId(int studentId, int courseId);

    //Find Result
    @Query(value = "SELECT * FROM Result result WHERE result.student_id = ?1 AND result.course_id=?2",nativeQuery = true)
    Result findResult(int studentId, int courseId);

    //Delete Enrolment
    @Modifying
    @Query(value = "DELETE FROM Result r WHERE r.student_id = ?1 AND r.course_id = ?2",nativeQuery = true)
    void deleteResultByStudentIdAndCourseId(int studentId,int courseId);

    //SELECT e FROM Enrolment e WHERE e.grade >= ?1
    List<Result> findResultByGradeGreaterThanEqual(int grade);
}
