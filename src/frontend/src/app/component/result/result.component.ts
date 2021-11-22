import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Course } from 'src/app/interface/course';
import { Result } from 'src/app/interface/result';
import { Student } from 'src/app/interface/student';
import { CourseService } from 'src/app/service/course.service';
import { ResultService } from 'src/app/service/result.service';
import { StudentService } from 'src/app/service/student.service';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {

  public listCourse: Course[];
  public listStudent: Student[];
  public listResult: Result[];
  public result: Result;
  public listGrade : Array<number> = [1,2,3,4,5,6,7,8,9,10];

  constructor(private resultService: ResultService,
              private studentService: StudentService,
              private courseService: CourseService) { }

  ngOnInit(): void {
    this.getAllResult();
    this.getAllStudent();
    this.getAllCourse();
  }

  public getResult(result: Result)
  {
    this.result = result;
  }
  public getAllResult():void
  {
    this.resultService.getAllResult().subscribe(
      (response: Result[]) => {
        this.listResult = response;
        console.log(this.listResult);
      },
      (error: HttpErrorResponse) => {
        alert(error.error.message);
      }
    );
  }
  public getAllCourse():void
  {
    this.courseService.getAllCourse().subscribe(
      (response: Course[]) => {
        this.listCourse = response;
        console.log(this.listCourse);
      },
      (error: HttpErrorResponse) => {
        alert(error.error.message);
      }
    );
  }

  public getAllStudent():void
  {
      this.studentService.getAllStudent().subscribe(
        (response: Student[]) => {
          this.listStudent = response;
          console.log(this.listStudent);
        },
        (error: HttpErrorResponse) => {
          alert(error.error.message);
        }
      );
  }

  public addResult(addForm: NgForm): void
  {
    document.getElementById('add-result-close').click();
    this.resultService.addResult(addForm.value,addForm.value.studentId,addForm.value.courseId).subscribe(
      (response: Result) => {
        console.log(response);
        alert('Added result successfully')
        this.getAllResult(); //reload list Result
        addForm.reset();
      },
      (error: HttpErrorResponse) =>
      {
        alert(error.error.message);
      }
    );
  }

  public deleteResult(studentId: number, courseId: number):void
  {
    document.getElementById('btn-no-delete').click();
    this.resultService.deleteResult(studentId,courseId).subscribe(
      (response: void) => {
        console.log(response);
        this.getAllResult();
      },
      (error: HttpErrorResponse) =>
      {
        alert(error.error.message);
      }
    );
  }

  public updateResult(updateForm: NgForm):void
  {
    document.getElementById('btn-edit-close').click();
    this.resultService.updateResult(updateForm.value.studentId,
                                    updateForm.value.courseId,
                                    updateForm.value.grade).subscribe(
      (response: Result) => {
        console.log(response);
        alert('Update successfully');
        this.getAllResult();
      },
      (error: HttpErrorResponse) =>
      {
        alert(error.error.message);
      }
    );
  }

  public searchResult(input: string):void
  {
    console.log(input);
    const result: Result[] = [];
    console.log(result);
    for(const getResult of this.listResult) //loop of js
    {
      if(getResult?.student?.name.toLowerCase().indexOf(input.toLowerCase()) !== -1 //if getResult.student.name include input
        || getResult?.course?.name.toLowerCase().indexOf(input.toLowerCase()) !== -1) //if getResult.course.name include input
      {
        result.push(getResult); //push getResult in result array
      }

    }
    this.listResult = result; //filter new list result when input match getResult.student.name or getResult.course.name
    if(result.length === 0 || !input) //if result empty or input empty
    {
      this.getAllResult(); //reload list result
    }
  }


}
