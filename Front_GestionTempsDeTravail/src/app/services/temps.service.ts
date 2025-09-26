import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, catchError, map, of, tap, throwError } from 'rxjs';
import { Temps } from '../entity/temps';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class TempsService {
  private apiUrl = 'http://localhost:8081/temps';
  idTemps:any;

  constructor(private http: HttpClient,private router: Router,private loginService:LoginService) { }


  getTemps() {
    return this.http.get(`${this.apiUrl}/listTemps`);
  }

  tempsDay(date: Date,idEmployee: number){
    const formattedDate = date.toISOString().slice(0, 19) + 'Z';
    return this.http.get<any>(`${this.apiUrl}/`+formattedDate+'?idEmployee='+idEmployee ).pipe(
      tap(response => {
        return response;
      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }






getTempsById(idTemps:number) {
  return this.http.get(`${this.apiUrl}/afficherTemps/`+idTemps);
}


modifierTemps(temps: Temps): Observable<any> {
  const body = {
    date: temps.date,
    heureDebut: temps.heureDebut,
    heureFin: temps.heureFin,
    idEmployee: temps.idEmployee,
  };
  return this.http.put<any>(`${this.apiUrl}/modifierTemps`, body);
}

}
