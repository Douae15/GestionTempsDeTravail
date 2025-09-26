import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import {  Router } from '@angular/router';
import {  HttpParams } from '@angular/common/http';
import { TempsService } from './temps.service';




@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private apiUrl = 'http://localhost:8081';
  user:any;
  

  constructor(private http: HttpClient,private router: Router) {}


  public login(username: string, password: string): Observable<any> {

    return this.http.post<any>(`${this.apiUrl}/authenticate`, { email: username, mdp : password }, { responseType: 'text' as 'json' }).pipe(
      tap(response => {
        localStorage.setItem('authUser', response);

      }),
      catchError(error => {
        return throwError(error);
      })
    );
  }

  
  

  public isAuthenticated(): boolean {
    return !!localStorage.getItem('authUser');
  }

  public getCurrentUser(): any | null {
    const userData = localStorage.getItem('authUser');
    console.log('User data:', userData); // Log the user data
    if (userData) {
      return JSON.parse(userData);
    } else {
      return null;
    }
  }

  public hasAuthority(authority: string): boolean {
    const user = this.getCurrentUser();
    if (user && user.authorities && user.authorities.includes(authority)) {
      return true;
    }
    return false;
  }

  reloadCurrentRoute() {

    this.router.routeReuseStrategy.shouldReuseRoute = () => false;

    this.router.onSameUrlNavigation = 'reload';

    this.router.navigate([this.router.url]);

  }


  updateUserProfile(updatedUser: any): Observable<any> {
    const userId = updatedUser.user.id;
    if(updatedUser.role=='employee'){
    return this.http.put<any>(`${this.apiUrl}/employee/modifierEmployee/${userId}`, updatedUser.user);
    }else{
      return this.http.put<any>(`${this.apiUrl}/admin/modifierAdmin/${userId}`, updatedUser.user);
    }
  }

}
