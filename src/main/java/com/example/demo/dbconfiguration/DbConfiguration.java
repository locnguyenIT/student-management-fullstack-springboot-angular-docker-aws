package com.example.demo.dbconfiguration;

import com.example.demo.classroom.Classroom;
import com.example.demo.classroom.ClassroomRepository;
import com.example.demo.course.Course;
import com.example.demo.course.CourseRepository;
import com.example.demo.result.Result;
import com.example.demo.result.ResultId;
import com.example.demo.faculty.Faculty;
import com.example.demo.faculty.FacultyRepository;
import com.example.demo.result.ResultRepository;
import com.example.demo.librarycard.LibraryCard;
import com.example.demo.librarycard.LibraryCardRepository;
import com.example.demo.student.Gender;
import com.example.demo.student.Student;
import com.example.demo.student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class DbConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository,
                                        ClassroomRepository classroomRepository,
                                        FacultyRepository facultyRepository,
                                        CourseRepository courseRepository,
                                        ResultRepository resultRepository,
                                        LibraryCardRepository libraryCardRepository)
    {
        return args ->  {

            //INSERT Faculty
            Faculty faculty_1  = new Faculty("Information Technology ABCDE") ;
            Faculty faculty_2  = new  Faculty("Business Administration") ;
            facultyRepository.saveAll(List.of(faculty_1,faculty_2));

            //INSERT Classroom
            Classroom classDCT1  = new Classroom("DCT1171",faculty_1);
            Classroom classDCT2  = new Classroom("DCT1172",faculty_1);

            Classroom classDKQ1  = new Classroom("DKQ1171",faculty_2);
            Classroom classDKQ2  = new Classroom("DKQ1172",faculty_2);

            classroomRepository.saveAll(List.of(classDCT1,classDCT2,classDKQ1,classDKQ2));

            //INSERT Student
            Student loc = new Student(
                    "Nguyen Thanh Loc",
                    "ntloc.developer@gmail.com",
                    Gender.Male,
                    LocalDate.of(1999, Month.SEPTEMBER, 28),
                    classDCT1);
            Student linh = new Student(
                    "Tran Ha Linh",
                    "halinh@gmail.com",
                     Gender.Female,
                    LocalDate.of(2002, Month.DECEMBER, 12),
                    classDCT1);
            Student bao = new Student(
                    "Tran Ba  Bao",
                    "babao@gmail.com",
                    Gender.Male,
                    LocalDate.of(1999, Month.NOVEMBER, 18),
                    classDCT2);
            Student minh = new Student(
                    "Nguyen Hoang Minh",
                    "minh@gmail.com",
                    Gender.Male,
                    LocalDate.of(1999, Month.NOVEMBER, 05),
                    classDCT2);
            Student han = new Student(
                    "Nguyen Ngoc Han",
                    "ngochan@gmail.com",
                    Gender.Female,
                    LocalDate.of(2000, Month.MARCH, 22),
                    classDKQ1);
            Student nam = new Student(
                    "Do Nam",
                    "donam@gmail.com",
                    Gender.Male,
                    LocalDate.of(2000, Month.JUNE, 18),
                    classDKQ1);
            Student hien = new Student(
                    "Pham Hien",
                    "hien@gmail.com",
                    Gender.Female,
                    LocalDate.of(2001, Month.JULY, 12),
                    classDKQ2);
            Student phuc = new Student(
                    "Huynh Minh Phuc",
                    "phuc@gmail.com",
                    Gender.Male,
                    LocalDate.of(2000, Month.MAY, 07),
                    classDKQ2);
            studentRepository.saveAll(List.of(loc,linh,bao,minh,han,nam,hien,phuc));

            //INSERT Library_card
            LibraryCard card_1 = new LibraryCard("1001",loc);
            LibraryCard card_2 = new LibraryCard("1003",nam);
            LibraryCard card_3 = new LibraryCard("1010",linh);
            LibraryCard card_4 = new LibraryCard("1005",hien);
            LibraryCard card_5 = new LibraryCard("1020",phuc);
            LibraryCard card_6 = new LibraryCard("1015",han);
            LibraryCard card_7 = new LibraryCard("1030",minh);
            LibraryCard card_8 = new LibraryCard("1018",bao);
            libraryCardRepository.saveAll(List.of(card_1,card_2,card_3,card_4,card_5,card_6,card_7,card_8));

            //INSERT Course
            Course course_1 = new Course("Programming techniques",faculty_1);
            Course course_2 = new Course("Java programming",faculty_1);
            Course course_3 = new Course("Spring Boot project",faculty_1);
            Course course_4 = new Course("Statistical probability",faculty_2);
            Course course_5 = new Course("Corporate governance",faculty_2);
            Course course_6 = new Course("Business plan",faculty_2);
            courseRepository.saveAll(List.of(course_1,course_2,course_3,course_4,course_5,course_6));

            //INSERT Result
            Result result_1 = new Result(new ResultId(1,1),loc,course_1,8) ;
            Result result_2 = new Result(new ResultId(2,2),linh,course_2,5) ;
            Result result_3 = new Result(new ResultId(3,1),bao,course_1,6) ;
            Result result_4 = new Result(new ResultId(1,3),loc,course_3,9) ;
            Result result_5 = new Result(new ResultId(7,4),hien,course_4,7) ;
            Result result_6 = new Result(new ResultId(8,6),phuc,course_6,6) ;
            Result result_7 = new Result(new ResultId(4,3),minh,course_3,8) ;
            Result result_8 = new Result(new ResultId(6,4),nam,course_4,5) ;
            Result result_9 = new Result(new ResultId(7,5),hien,course_5,7) ;
            Result result_10 = new Result(new ResultId(5,5),han,course_5,4) ;
            resultRepository.saveAll(List.of(result_1, result_2, result_3, result_4, result_5, result_6, result_7, result_8, result_9, result_10));

        };
    }
}
