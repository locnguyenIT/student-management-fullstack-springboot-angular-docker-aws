package com.example.demo.classroom;

import com.example.demo.faculty.Faculty;
import com.example.demo.student.Student;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Classroom")
@Table(name = "classroom", uniqueConstraints = {
                                                @UniqueConstraint(name = "classroom_name_unique",
                                                columnNames = "name")
                                                }
)
public class Classroom  {

    @Id
    @SequenceGenerator(name = "classroom_sequence",
                        sequenceName = "classroom_sequence",
                        allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "classroom_sequence")
    @Column(name = "id",
            updatable = false,
            nullable = false)
    private int id;

    @Column(name = "name",nullable = false)
    private String name;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
    private List<Student> student;

    @ManyToOne
    @JoinColumn(name = "faculty_id", //column foreign key
                referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "fk_classroom_faculty")) //name of foreign key
    private Faculty faculty;

    public Classroom() {
    }


    public Classroom(String name, Faculty faculty) {
        this.name = name;
        this.faculty = faculty;
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

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }


    @Override
    public String toString() {
        return "Classroom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", faculty=" + faculty +
                '}';
    }
}
