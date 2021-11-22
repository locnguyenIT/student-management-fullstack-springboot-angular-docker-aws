package com.example.demo.faculty;

import com.example.demo.classroom.Classroom;
import com.example.demo.course.Course;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Faculty")
@Table(name = "faculty", uniqueConstraints = {
                                            @UniqueConstraint(
                                             name = "faculty_name_unique",
                                             columnNames = "name")
                                            }
)
public class Faculty  {

    @Id //Primary Key
    @SequenceGenerator(name = "faculty_sequence",sequenceName = "faculty_sequence",allocationSize = 1) //generate sequence with id auto increment begin 1
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "faculty_sequence") //use sequence is just defined above
    @Column(name = "id",
            updatable = false,
            nullable = false)
    private int id;

    @Column(name = "name",nullable = false)
    private String name;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    private List<Classroom> classroom;

    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    private List<Course> course;

    public Faculty() {
    }

    public Faculty(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
