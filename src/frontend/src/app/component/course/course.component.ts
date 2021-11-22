import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Course } from 'src/app/interface/course';
import { Faculty } from 'src/app/interface/faculty';
import { CourseService } from 'src/app/service/course.service';
import { FacultyService } from 'src/app/service/faculty.service';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {

  public listCourses: Course[];
  public listFaculty: Faculty[];
  public course: Course;

  constructor(private courseService: CourseService, private facultyService: FacultyService) { }

  ngOnInit(): void {
    this.getAllCourse();
    this.getAllFaculty();
  }

  public getCourse(course: Course)
  {
    this.course = course;
  }

  public getAllCourse():void
  {
    this.courseService.getAllCourse().subscribe(
      (response: Course[]) => {
        this.listCourses = response;
        console.log(this.listCourses);
      },
      (error: HttpErrorResponse) => {
       alert(error.error.message);
      }
    );
  }

  public getAllFaculty():void
  {
    this.facultyService.getAllFaculty().subscribe(
      (response: Faculty[]) => {
        this.listFaculty = response;
        console.log(this.listFaculty);
      },
      (error: HttpErrorResponse) => {
        alert(error.error.message);
      }
    )
  }

  public addCourse(addForm: NgForm): void
  {
    document.getElementById('add-course-close').click();
    this.courseService.addCourse(addForm.value,addForm.value.facultyId).subscribe(
      (response: Course) => {
        console.log(response);
        alert('Added course successfully')
        this.getAllCourse(); //reload list faculty
        addForm.reset();
      },
      (error: HttpErrorResponse) =>
      {
        alert(error.error.message);
      }
    );
  }

  public deleteCourse(courseId: number): void
  {
    document.getElementById('btn-no-delete').click(); //when delete susscess, click close button to close the form
    this.courseService.deleteCourse(courseId).subscribe(
      (response: void) => {
        console.log(response);
        this.getAllCourse(); //reload list faculty
      },
      (error: HttpErrorResponse) =>
      {
        alert(error.error.message);
      }
    )
  }
  public updateCourse(updateForm: NgForm):void
  {
      document.getElementById('btn-edit-close').click();
      this.courseService.updateCourse(updateForm.value.id,updateForm.value.name,updateForm.value.facultyId).subscribe(
        (response: Course) => {
          console.log(response);
          alert('Update successfully');
          this.getAllCourse();
        },
        (error: HttpErrorResponse) =>
        {
          alert(error.error.message);
        }
      )
  }

  public searchCourse(input: string):void
  {
    console.log(input);
    const result: Course[] = [];
    console.log(result);
    for(const course of this.listCourses) //loop of js
    {
      if(course?.name.toLowerCase().indexOf(input.toLowerCase()) !== -1  //if course?.name include input
      || course?.faculty?.name.toLowerCase().indexOf(input.toLowerCase()) !== -1)  //course?.faculty?.name
      {
        result.push(course); //push student in result array
      }

    }
    this.listCourses = result; //filter new list student when input match course?.nameor course?.faculty?.name
    if(result.length === 0 || !input) //if result empty or input empty
    {
      this.getAllCourse(); //reload list student
    }
  }

}
