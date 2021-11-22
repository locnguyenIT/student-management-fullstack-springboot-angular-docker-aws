package com.example.demo.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/spring-boot/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourse()
    {
        return courseService.getAllCourse();
    }

    @GetMapping(path = "/get/{courseId}")
    public Course getOneCourse(@PathVariable("courseId") Integer courseId)
    {
        return courseService.getOneCourse(courseId);
    }

    @PostMapping(path = "/add/facultyId/{facultyId}")
    public void addCourse(@RequestBody Course course ,
                          @PathVariable("facultyId") Integer facultyId)
    {
        courseService.addCourse(course,facultyId);
    }

    @DeleteMapping(path = "/delete/{courseId}")
    public void deleteCourse(@PathVariable("courseId") Integer courseId)
    {
        courseService.deleteCourse(courseId);
    }

    @PutMapping(path = "/update/{courseId}")
    public void updateCourse(@PathVariable("courseId") Integer courseId,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) Integer facultyId)
    {
        courseService.updateCourse(courseId,name,facultyId);
    }

}
