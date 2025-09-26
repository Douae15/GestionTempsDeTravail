import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, tap, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HumeurService {
  private apiUrl = 'http://localhost:8081';

   constructor(private http: HttpClient,private router: Router) {}

   isValidToken(token: string) {
    return this.http.get(`${this.apiUrl}/verifierToken/`+token, { responseType: 'text' as 'json' }).pipe(
      tap(response => {
        return response;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  ajouterHumeur(data: any) {
    return this.http.post<any>(`${this.apiUrl}/humeur/ajouter`, data);
  }

}
