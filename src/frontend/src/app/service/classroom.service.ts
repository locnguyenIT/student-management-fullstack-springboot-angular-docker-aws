import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Classroom } from '../interface/classroom';

@Injectable({
  providedIn: 'root'
})
export class ClassroomService {

  private backendURL = environment.backendURL;
  constructor(private http:HttpClient) { }

  public getAllClassroom(): Observable<Classroom[]>
  {
    return this.http.get<Classroom[]>(`${this.backendURL}/classroom`);
  }

  public addClassroom(classroom: Classroom, facultyId: number): Observable<Classroom>
  {
    return this.http.post<Classroom>(`${this.backendURL}/classroom/add/facultyId/${facultyId}`,classroom);
  }

  public deleteClassroom(classroomId: number): Observable<void>
  {
    return this.http.delete<void>(`${this.backendURL}/classroom/delete/${classroomId}`);
  }

  public updateClassroom(classroomId: number, name: string, facultyId: number): Observable<Classroom>
  {
    const params= new HttpParams().set('name',name).set('facultyId',facultyId);
    return this.http.put<Classroom>(`${this.backendURL}/classroom/update/${classroomId}`,params);
  }

}
