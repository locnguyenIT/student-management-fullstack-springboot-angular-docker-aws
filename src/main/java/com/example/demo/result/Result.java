package com.example.demo.result;

import com.example.demo.course.Course;
import com.example.demo.student.Student;

import javax.persistence.*;

@Entity(name = "Result")
@Table
public class Result {

    @EmbeddedId
    ResultId id;

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id",
                referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "fk_student_result")) //foreign key
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id", // column foreign key
                referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "fk_course_result")) //name of foreign key
    private Course course;

    @Column(name = "grade",columnDefinition = "INT",nullable = false)
    private int grade;

    public Result() {
    }

    public Result(ResultId id, Student student, Course course, int grade) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.grade = grade;
    }

    public Result(Student student, Course course, int grade) {
        this.student = student;
        this.course = course;
        this.grade = grade;
    }

    public ResultId getId() {
        return id;
    }

    public void setId(ResultId id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", student=" + student +
                ", course=" + course +
                ", grade=" + grade +
                '}';
    }
}
