import { Injectable } from '@angular/core';
import { LoginService } from './login.service';
import { TempsService } from './temps.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LogoutService {

  constructor(private router: Router,private loginService: LoginService, private tempsService: TempsService) { }

  public logout(): void {
    const user = this.loginService.getCurrentUser();
  
    if (user.role == 'employee') {
      const date = new Date();
      const idEmployee = user.user.idEmployee;
  
      this.tempsService.tempsDay(date, idEmployee).subscribe(
        response => {
          if (response.heureDebut != null && response.heureFin == null) {
            alert("Vous n'avez pas enregistré l'heure de fin de votre travail !");
          } else {
            
            this.performLogout();
          }
        },
        error => {
          console.error('Erreur lors de la vérification des temps de travail', error);
        }
      );
    } else {
      
      this.performLogout();
    }
  }
  
  private performLogout(): void {
    localStorage.removeItem('authUser');
    localStorage.removeItem('user');
    this.router.navigateByUrl('/login');
  }
}
