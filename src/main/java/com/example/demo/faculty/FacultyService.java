package com.example.demo.faculty;

import com.example.demo.course.Course;
import com.example.demo.exception.APIEntityNotFoundException;
import com.example.demo.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository repository) {
        this.facultyRepository = repository;
    }

    public List<Faculty> getFaculty()
    {
        return facultyRepository.findAll();
    }

    public Faculty getOneFaculty(Integer facultyId)
    {
        return facultyRepository.findById(facultyId).orElseThrow(()-> new APIEntityNotFoundException("Faculty by id "+facultyId+" was not found"));
    }

    public void addFaculty(Faculty faculty) {
        Optional<Faculty> facultyByName = facultyRepository.findFacultyByName(faculty.getName());
        if(facultyByName.isPresent()) //if name of faculty is present in database
        {
            throw new BadRequestException("Name already exist in database");
        }
        facultyRepository.save(faculty);
        System.out.println(faculty);
    }

    public void deleteFaculty(Integer facultyId)
    {
        boolean exists = facultyRepository.existsById(facultyId);
        if(!exists)
        {
            throw new APIEntityNotFoundException("faculty with id = "+facultyId+ " does not exist in database");
        }
        System.out.println("faculty with id = "+facultyId+" has been delete");
        facultyRepository.deleteById(facultyId);
    }

    public void updateFaculty(Faculty faculty)
    {
        Faculty faculty_db = facultyRepository.findById(faculty.getId()).orElseThrow(() -> new APIEntityNotFoundException("Faculty by id "+faculty.getId()+" was not found"));
        if(faculty.getName() != null &&
           faculty.getName().length() > 0 &&
           !Objects.equals(faculty_db.getName(),faculty.getName())) //if the new name has been provided is not the same name in database
        {
            Optional<Faculty> facultyByName = facultyRepository.findFacultyByName(faculty.getName());
            if(facultyByName.isPresent()) //if new name of classroom is present in database
            {
                throw new BadRequestException("Faculty "+faculty.getName()+" already exist in database");
            }
            faculty.setName(faculty.getName());
        }
        facultyRepository.save(faculty);
    }
//    @Transactional
//    public void updateFaculty(Integer facultyId, String name)
//    {
//        Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(() -> new APIEntityNotFoundException("Faculty by id "+facultyId+" was not found"));
//        if(name != null && name.length() > 0 && faculty.getName() != name) //if the new name has been provided is not the same name in database
//        {
//            Optional<Faculty> facultyByName = facultyRepository.findFacultyByName(name);
//            if(facultyByName.isPresent()) //if new name of classroom is present in database
//            {
//                throw new IllegalStateException("Faculty "+name+" already exist in database");
//            }
//            faculty.setName(name);
//        }
//    }



}
