package com.example.demo.classroom;

import com.example.demo.exception.APIEntityNotFoundException;
import com.example.demo.exception.BadRequestException;
import com.example.demo.faculty.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final FacultyRepository facultyRepository;

    @Autowired
    public ClassroomService(ClassroomRepository classroomRepository, FacultyRepository facultyRepository) {
        this.classroomRepository =     classroomRepository;
        this.facultyRepository = facultyRepository;
    }

    public List<Classroom> getAllClassroom()
    {
        return classroomRepository.findAll();
    }

    public Classroom getOneClassroom(Integer classroomId)
    {
        return classroomRepository.findById(classroomId).orElseThrow(()-> new APIEntityNotFoundException("Classroom with id "+classroomId+ " was not found"));
    }

    public void addClassroom(Classroom classroom,Integer facultyId)
    {
        Optional<Classroom> classroomByName = classroomRepository.findClassroomByName(classroom.getName());
        if(classroomByName.isPresent()) //if name of classroom is present in database
        {
            throw new BadRequestException("Name already exist in database.ss");
        }
        boolean exists = facultyRepository.existsById(facultyId);
        if(!exists)
        {
            throw new APIEntityNotFoundException("Faculfdsty with id "+facultyId+ " was not found");
        }
        classroom.setFaculty(facultyRepository.getById(facultyId));
        classroomRepository.save(classroom);
        System.out.println(classroom);
    }

    public void deleteClassroom(Integer classroomId)
    {
        boolean exists = classroomRepository.existsById(classroomId);
        if(!exists)
        {
            throw new BadRequestException("classroom with id = "+classroomId+ " does not exist in database");
        }
        System.out.println("Classroom id = "+classroomId+" has been delete");
        classroomRepository.deleteById(classroomId);
    }

    public void updateClassroom(Integer classroomId, String name, Integer facultyId)
    {
        //Check studentId in database
        Classroom classroom = classroomRepository.findById(classroomId).orElseThrow(() -> new APIEntityNotFoundException("Classroom by id "+classroomId+" was not found"));
        if(name != null && name.length() > 0 && !Objects.equals(classroom.getName(),name)) //if the new name has been provided is not the same name in database
        {
            Optional<Classroom> classroomByName = classroomRepository.findClassroomByName(name);
            if(classroomByName.isPresent()) //if new name of classroom is present in database
            {
                throw new BadRequestException("Classroom "+name+" already exist in database");
            }
            classroom.setName(name);
        }
        if(facultyId != null && !Objects.equals(classroom.getFaculty().getId(), facultyId)) //if the new name has been provided is not the same name in database
        {
            boolean exists = facultyRepository.existsById(facultyId);
            if(!exists)
            {
                throw new BadRequestException("Faculty "+facultyId+" doesn't exist in database");
            }
            classroom.setFaculty(facultyRepository.getById(facultyId));
        }
        classroomRepository.save(classroom);
    }


}
