import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { LibraryCard } from '../interface/library-card';

@Injectable({
  providedIn: 'root'
})
export class LibraryCardService {

  private backendURL = 'http://localhost:8080/api/spring-boot'; //URL to connect to backend;

  constructor(private http: HttpClient) { }

  public getAllLibraryCard(): Observable<LibraryCard[]>
  {
    return this.http.get<LibraryCard[]>(`${this.backendURL}/library-card`)
  }

  public addLibraryCard(libraryCard: LibraryCard,studentId: number): Observable<LibraryCard>
  {
    return this.http.post<LibraryCard>(`${this.backendURL}/library-card/add/studentId/${studentId}`,libraryCard);
  }

  public deleteLibraryCard(libraryCardId: number): Observable<void>
  {
    return this.http.delete<void>(`${this.backendURL}/library-card/delete/${libraryCardId}`);
  }

  public updateLibraryCard(libraryCardId: number, card_number: number) :Observable<LibraryCard>
  {
    const params = new HttpParams().set('card_number',card_number);
    return this.http.put<LibraryCard>(`${this.backendURL}/library-card/update/${libraryCardId}`,params);
  }
}
