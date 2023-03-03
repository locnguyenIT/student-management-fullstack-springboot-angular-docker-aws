import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Course } from '../interface/course';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  private backendURL = environment.backendURL;
  constructor(private http:HttpClient) { }

  public getAllCourse(): Observable<Course[]>
  {
    return this.http.get<Course[]>(`${this.backendURL}/course`);
  }

  public addCourse(course: Course, facultyId: number): Observable<Course>
  {
    return this.http.post<Course>(`${this.backendURL}/course/add/facultyId/${facultyId}`,course);
  }

  public deleteCourse(facultyId: number): Observable<void>
  {
    return this.http.delete<void>(`${this.backendURL}/course/delete/${facultyId}`);
  }

  public updateCourse(courseId: number, name: string, facultyId: number): Observable<Course>
  {
    const params= new HttpParams().set('name',name).set('facultyId',facultyId);
    return this.http.put<Course>(`${this.backendURL}/course/update/${courseId}`,params);
  }
}
