import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import {  Router } from '@angular/router';
import {  HttpParams } from '@angular/common/http';
import { Rapport } from '../entity/rapport';
import { MatDialog } from '@angular/material/dialog';

@Injectable({
  providedIn: 'root'
})
export class RapportService {
  private apiUrl = 'http://localhost:8081/rapport';
  
  constructor(private http: HttpClient,private router: Router,private dialog: MatDialog) { }
  

  employeeRapports(idEmployee: number) {
    return this.http.get<Rapport[]>(`${this.apiUrl}/EmployeeRapport?idEmployee=`+idEmployee).pipe(
      tap(response => {
        return response;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  
}
