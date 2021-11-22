import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Faculty } from 'src/app/interface/faculty';
import { FacultyService } from 'src/app/service/faculty.service';

@Component({
  selector: 'app-faculty',
  templateUrl: './faculty.component.html',
  styleUrls: ['./faculty.component.css']
})
export class FacultyComponent implements OnInit {

  public listFaculty: Faculty[];

  public faculty: Faculty;

  constructor(private facultyService: FacultyService) { }

  ngOnInit(): void {
    this.getAllFaculty();
  }

  public getFaculty(faculty: Faculty)
  {
    this.faculty = faculty;
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
    );
  }

  public addFaculty(addForm: NgForm): void
  {
    document.getElementById('add-faculty-close').click();
    this.facultyService.addFaculty(addForm.value).subscribe(
      (response: Faculty) => {
        console.log(response);
        alert('Added faculty successfully')
        this.getAllFaculty(); //reload list faculty
        addForm.reset();
      },
      (error: HttpErrorResponse) =>
      {
        alert(error.error.message);
      }
    );
  }

  public onDeleteFaculty(facultyId: number): void
  {
    document.getElementById('btn-no-delete').click(); //when delete susscess, click close button to close the form
    this.facultyService.deleteFaculty(facultyId).subscribe(
      (response: void) => {
        console.log(response);
        this.getAllFaculty(); //reload list faculty
      },
      (error: HttpErrorResponse) =>
      {
        alert(error.error.message);
      }
    )
  }

  public onUpdateFaculty(faculty: Faculty):void
  {
      document.getElementById('btn-edit-close').click();
      this.facultyService.updateFaculty(faculty).subscribe(
        (response: Faculty) => {
          console.log(response);
          alert('Update successfully');
          this.getAllFaculty();
        },
        (error: HttpErrorResponse) =>
        {
          alert(error.error.message);
        }
      )
  }

  public searchFaculty(input: string):void
  {
    console.log(input);
    const result: Faculty[] = [];
    console.log(result);
    for(const faculty of this.listFaculty) //loop of js
    {
      if(faculty.name.toLowerCase().indexOf(input.toLowerCase()) !== -1)  //if student.name include input
      {
        result.push(faculty); //push student in result array
      }

    }
    this.listFaculty = result; //filter new list student when input match student.name or student.email
    if(result.length === 0 || !input) //if result empty or input empty
    {
      this.getAllFaculty(); //reload list student
    }
  }

}
