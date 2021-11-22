package com.example.demo.result;

import com.example.demo.course.CourseRepository;
import com.example.demo.exception.APIEntityNotFoundException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ResultService {

    private final ResultRepository resultRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired //dependency injection
    public ResultService(ResultRepository resultRepository, StudentRepository studentRepository, CourseRepository courseRepository)
    {
        this.resultRepository = resultRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<Result> getAllResult()
    {
        return resultRepository.findAll();
    }

    public List<Result> getResultByGradeGreaterThanEqual(Integer grade)
    {
        return resultRepository.findResultByGradeGreaterThanEqual(grade);
    }

    public Result getOneResult(Integer resultId) {
        return resultRepository.findById(resultId).orElseThrow(()-> new APIEntityNotFoundException("Enrolment with id "+resultId+" was not found"));
    }

    public void addResult(Result result, Integer studentId, Integer courseId)
    {
        Optional<Result> findResult = resultRepository.findResultByStudentIdAndCourseId(studentId,courseId);
        if(findResult.isPresent()) //if new name of classroom is present in database
        {
            throw new BadRequestException("Result "+" already exist in database");
        }
        boolean existsStudent = studentRepository.existsById(studentId);
        if(!existsStudent)
        {
            throw new APIEntityNotFoundException("Student with id "+studentId+" was not found");
        }
        boolean existsCourse = courseRepository.existsById(courseId);
        if(!existsCourse)
        {
            throw new APIEntityNotFoundException("Course with id "+courseId+" was not found");
        }
        int student_facultyId = studentRepository.getById(studentId).getClassroom().getFaculty().getId(); //find this student belongs to which faculty
        String student_facultyName = studentRepository.getById(studentId).getClassroom().getFaculty().getName();
        int course_facultyId = courseRepository.getById(courseId).getFaculty().getId(); //find this course belongs to which faculty
        String course_facultyName = courseRepository.getById(courseId).getFaculty().getName();
        if(student_facultyId != course_facultyId) // if the student and course different in the faculty
        {
            throw new BadRequestException("Student with "+studentId+" is faculty of "+student_facultyName+" can't enroll. Because this courseId "+courseId+" belong to faculty of "+course_facultyName);
        }
        result.setId(new ResultId(studentId,courseId));
        result.setStudent(studentRepository.getById(studentId));
        result.setCourse(courseRepository.getById(courseId));
        resultRepository.save(result);
    }

    @Transactional
    public void deleteResult(Integer studentId, Integer courseId)
    {
        boolean existsStudentById = studentRepository.existsById(studentId);
        if(!existsStudentById)
        {
            throw new APIEntityNotFoundException("Student with id "+studentId+" was not found");
        }
        boolean existCourseById = courseRepository.existsById(courseId);
        if(!existCourseById)
        {
            throw new APIEntityNotFoundException("Course with id "+courseId+" was not found");
        }
        resultRepository.deleteResultByStudentIdAndCourseId(studentId,courseId);
    }

    public void updateResult(Integer studentId, Integer courseId, Integer grade)
    {
        Result findResult = resultRepository.findResult(studentId,courseId);
        //update grade
        if(findResult.getGrade() != grade)
        {
              findResult.setGrade(grade);
         }
        resultRepository.save(findResult);
    }



}
