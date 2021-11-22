package com.example.demo.faculty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/spring-boot/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public List<Faculty> getFacultyList()
    {
        return facultyService.getFaculty();
    }

    @GetMapping(path = "/get/{facultyId}")
    public Faculty getOneFaculty(@PathVariable("facultyId") Integer facultyId)
    {
        return facultyService.getOneFaculty(facultyId);
    }

    @PostMapping(path = "/add")
    public void addFaculty(@RequestBody Faculty faculty)
    {
        facultyService.addFaculty(faculty);
    }

    @DeleteMapping(path = "/delete/{facultyId}")
    public void deleteFaculty(@PathVariable("facultyId") Integer facultyId)
    {
        facultyService.deleteFaculty(facultyId);
    }

    @PutMapping(path = "/update") //(http://localhost:8080/api/spring-boot/faculty/update/"id"?name="")
    public void updateFaculty(@RequestBody Faculty faculty)
    {
        facultyService.updateFaculty(faculty);
    }

//    @PutMapping(path = "/update/{facultyId}") //(http://localhost:8080/api/spring-boot/faculty/update/"id"?name="")
//    public void updateFaculty(@PathVariable("facultyId") Integer facultyId,
//                              @RequestParam(required = false) String name)
//    {
//        facultyService.updateFaculty(facultyId,name);
//    }
}
