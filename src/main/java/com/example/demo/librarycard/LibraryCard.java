package com.example.demo.librarycard;

import com.example.demo.student.Student;

import javax.persistence.*;

@Entity(name = "LibraryCard")
@Table(name = "library_card", uniqueConstraints = {
                                                  @UniqueConstraint(
                                                        name = "library_card_number_unique",
                                                        columnNames = "card_number")
                                                  }
)
public class LibraryCard {

    @Id
    @SequenceGenerator(name = "library_card_sequence",
                        sequenceName = "library_card_sequence",
                        allocationSize = 1) //generate sequence with id auto increment begin 1
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "library_card_sequence") //use sequence is just defined above
    @Column(name = "id",
            updatable = false,
            nullable = false)
    private int id;

    @Column(name = "card_number",nullable = false)
    private String card_number;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "student_id",   //foreign key column
                referencedColumnName = "id",
                foreignKey = @ForeignKey(name = "fk_student_library_card")) //name of foreign key
    private Student student;

    public LibraryCard() {
    }

    public LibraryCard(String cardNumber,Student student) {
        this.card_number = cardNumber;
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    @Override
    public String toString() {
        return "LibraryCard{" +
                "id=" + id +
                ", card_number='" + card_number + '\'' +
                ", student=" + student +
                '}';
    }
}
