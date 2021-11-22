package com.example.demo.classroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/spring-boot/classroom")
public class ClassroomController {

    private final ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping
    public List<Classroom> getAllClassroom()
    {
        return classroomService.getAllClassroom();
    }

    @GetMapping(path = "/get/{classroomId}")
    public Classroom getOneClassroom(@PathVariable("classroomId") Integer classroomId)
    {
        return classroomService.getOneClassroom(classroomId);
    }

    @PostMapping(path = "/add/facultyId/{facultyId}")
    public void addClassroom(@RequestBody Classroom classroom,@PathVariable("facultyId") Integer facultyId)
    {
        classroomService.addClassroom(classroom,facultyId);
    }
    @DeleteMapping(path = "/delete/{classroomId}")
    public void deleteClassroom(@PathVariable("classroomId") Integer classroomId)
    {
        classroomService.deleteClassroom(classroomId);
    }

    @PutMapping(path = "/update/{classroomId}") //(http://localhost:8080/api/spring-boot/classroom/update/"id"?name=""&facultyId="")
    public void updateClassroom(@PathVariable("classroomId") Integer classroomId,
                                @RequestParam(required = false) String name,
                                @RequestParam(required = false) Integer facultyId)
    {
        classroomService.updateClassroom(classroomId,name,facultyId);
    }

}
