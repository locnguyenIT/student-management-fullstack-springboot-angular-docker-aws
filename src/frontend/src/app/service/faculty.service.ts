import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Faculty } from '../interface/faculty';

@Injectable({
  providedIn: 'root'
})
export class FacultyService {

  private backendURL = environment.backendURL;
  constructor(private http: HttpClient) { }

  public getAllFaculty(): Observable<Faculty[]>
  {
    return this.http.get<Faculty[]>(`${this.backendURL}/faculty`)
  }

  public addFaculty(faculty: Faculty): Observable<Faculty>
  {
    return this.http.post<Faculty>(`${this.backendURL}/faculty/add`,faculty);
  }

  public deleteFaculty(facultyId: number): Observable<void>
  {
    return this.http.delete<void>(`${this.backendURL}/faculty/delete/${facultyId}`);
  }

  public updateFaculty(faculty: Faculty): Observable<Faculty>
  {
    return this.http.put<Faculty>(`${this.backendURL}/faculty/update`,faculty);
  }

}
