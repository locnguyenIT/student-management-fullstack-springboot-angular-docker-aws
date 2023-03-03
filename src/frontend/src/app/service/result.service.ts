import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Result } from '../interface/result';

@Injectable({
  providedIn: 'root'
})
export class ResultService {

  private backendURL = environment.backendURL;
  constructor(private http: HttpClient) { }

  public getAllResult(): Observable<Result[]> //get All Student from backend
  {
    return this.http.get<Result[]>(`${this.backendURL}/result`); //localhost:8080/spring-boot/student
  }

  public addResult(result: Result,studentId: number, courseId: number): Observable<Result>
  {
    return this.http.post<Result>(`${this.backendURL}/result/add/studentId/${studentId}/courseId/${courseId}`,result);
  }

  public deleteResult(studentId: number, courseId: number): Observable<void>
  {
    return this.http.delete<void>(`${this.backendURL}/result/delete/studentId/${studentId}/courseId/${courseId}`);
  }

  public updateResult(studentId: number, courseId: number,grade: number): Observable<Result>
  {
    const params = new HttpParams().set('grade',grade);
    return this.http.put<Result>(`${this.backendURL}/result/update/studentId/${studentId}/courseId/${courseId}/grade`,params);
  }

}
