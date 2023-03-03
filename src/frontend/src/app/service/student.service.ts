import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Gender } from '../enum/gender.enum';
import { Student } from '../interface/student';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private backendURL = environment.backendURL;
  constructor(private http: HttpClient) { }

  public getAllStudent(): Observable<Student[]> //get All Student from backend
  {
    return this.http.get<Student[]>(`${this.backendURL}/student`); //localhost:8080/spring-boot/student
  }

  public addStudent(student: Student, classroomId: number): Observable<Student>
  {
    return this.http.post<Student>(`${this.backendURL}/student/add/classroomId/${classroomId}`,student);
  }

  public deleteStudent(studentId: number): Observable<void>
  {
    return this.http.delete<void>(`${this.backendURL}/student/delete/${studentId}`);
  }

  public updateStudent(studentId: number, name: string, email: string,gender: Gender, dob: Date, classroomId: number): Observable<Student>
  {
    const params= new HttpParams().set('name',name).set('email',email).set('gender',gender).set('dob',dob.toString()).set('classroomId',classroomId);
    return this.http.put<Student>(`${this.backendURL}/student/update/${studentId}`,params);
  }
}
