import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { stringify } from 'querystring';
import { Classroom } from 'src/app/interface/classroom';
import { Faculty } from 'src/app/interface/faculty';
import { ClassroomService } from 'src/app/service/classroom.service';
import { FacultyService } from 'src/app/service/faculty.service';

@Component({
  selector: 'app-classroom',
  templateUrl: './classroom.component.html',
  styleUrls: ['./classroom.component.css']
})
export class ClassroomComponent implements OnInit {

  public listClassroom: Classroom[];
  public listFaculty: Faculty[];
  public classroom: Classroom;


  constructor(private classroomService: ClassroomService, private facultyService: FacultyService) { }

  ngOnInit(): void {
    this.getAllClassroom();
    this.getAllFaculty();
  }

  public getClassroom(classroom: Classroom)
  {
    this.classroom = classroom;
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

  public getAllClassroom():void
  {
    this.classroomService.getAllClassroom().subscribe(
      (response: Classroom[]) => {
        this.listClassroom = response;
        console.log(this.listClassroom);
      },
      (error: HttpErrorResponse) => {
        alert(error.error.message);
      }
    )
  }

  public addClassroom(addForm: NgForm): void
  {
    document.getElementById('add-classroom-close').click();
    this.classroomService.addClassroom(addForm.value,addForm.value.facultyId).subscribe(
      (response: Classroom) => {
        console.log(response);
        alert('Added classroom successfully')
        this.getAllClassroom(); //reload list faculty
        addForm.reset();
      },
      (error: HttpErrorResponse) =>
      {
        alert(error.error.message);
      }
    );
  }

  public deleteClassroom(classroomId: number): void
  {
    document.getElementById('btn-no-delete').click(); //when delete susscess, click close button to close the form
    this.classroomService.deleteClassroom(classroomId).subscribe(
      (response: void) => {
        console.log(response);
        this.getAllClassroom(); //reload list faculty
      },
      (error: HttpErrorResponse) =>
      {
       alert(error.error.message);
      }
    )
  }
  public updateClassroom(updateForm: NgForm):void
  {
      document.getElementById('btn-edit-close').click();
      this.classroomService.updateClassroom(updateForm.value.id,updateForm.value.name,updateForm.value.facultyId).subscribe(
        (response: Classroom) => {
          console.log(response);
          alert('Update successfully');
          this.getAllClassroom();
        },
        (error: HttpErrorResponse) =>
        {
          alert(error.error.message);
        }
      )
  }

  public searchClassroom(input: string):void
  {
    console.log(input);
    const result: Classroom[] = [];
    console.log(result);
    for(const classroom of this.listClassroom) //loop of js
    {
      if(classroom.name.toLowerCase().indexOf(input.toLowerCase()) !== -1  //if classroom.name include input
      || classroom?.faculty?.name.toLowerCase().indexOf(input.toLowerCase()) !== -1)  //classroom?.faculty?.name
      {
        result.push(classroom); //push student in result array
      }

    }
    this.listClassroom = result; //filter new list student when input match  classroom.name or classroom?.faculty?.name
    if(result.length === 0 || !input) //if result empty or input empty
    {
      this.getAllClassroom(); //reload list student
    }
  }

}
