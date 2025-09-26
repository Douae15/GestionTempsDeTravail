import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Temps } from '../entity/temps';
import { LoginService } from '../services/login.service';
import { TempsService } from '../services/temps.service';
import { Router } from '@angular/router';
import { ProfileEditComponent } from '../profile-edit/profile-edit.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-home-employee',
  templateUrl: './home-employee.component.html',
  styleUrls: ['./home-employee.component.css']
})
export class HomeEmployeeComponent {
  temps: Temps = new Temps();

 

  errorMessage: any;
  successMessage:any;

  heureDebut: any;
  heureFin: any;

  isPaused: boolean = false;
  inseredHeureDebut:boolean=false;
  inseredHeureFin:boolean=false;


  constructor(private http: HttpClient, private loginService:LoginService,
    private tempsService: TempsService,
    private router :Router,) {}


  ngOnInit(): void {
    var date=new Date();
    const authUserData = localStorage.getItem('authUser');
    if (authUserData) {
      const userData = JSON.parse(authUserData);
    if (userData.user.idEmployee) {

      const idEmployee = userData.user.idEmployee;
      this.tempsService.tempsDay(date,idEmployee).subscribe(
      response => {
        console.log('Temps resource exists:', response);
        this.heureDebut = response.heureDebut;
        this.heureFin = response.heureFin;
        if(this.heureDebut!=null){
          this.inseredHeureDebut=true;
        }
        if(this.heureFin !=null){
          this.inseredHeureFin=true;
        }
        
    }
    ,
      error => {
        console.error('Temps resource does not exist:', error);

      }
    );
    }else{
      this.router.navigateByUrl('/');
    }

  }else{
    this.router.navigateByUrl('/');
  }

  }

  modifierTemps() {
    const user = this.loginService.getCurrentUser();
    const tempsData: Temps = {
      heureDebut: this.heureDebut,
      heureFin: this.heureFin,
      date: new Date(),
      idEmployee: user.user.idEmployee,
      idPause: undefined
    };


    this.tempsService.modifierTemps(tempsData).subscribe(
      (response) => {
        
        console.log('Temps added:', response);
        if(response != null){
        this.successMessage = 'Temps added successfully';
        window.location.reload();
        alert("Temps ajouté avec succès.")
        }else{
          this.successMessage = 'La date de fin doit être postérieure à la date de début.';
          alert("La date de fin doit être postérieure à la date de début.")
        }
        
        
      },
      (error) => {
        console.error('Error adding temps:', error);
        this.errorMessage = 'Error adding temps';
        alert("Erreur lors de l'ajout du temps.")
      }
    );
  }


managePause() {
  const user = this.loginService.getCurrentUser();
  const idEmployee = user.user.idEmployee;

  this.http.put(`http://localhost:8081/pauses/modifierPause/${idEmployee}`, {}).subscribe(
    response => {
      if(response!=null){
        this.isPaused = !this.isPaused;
        if(this.isPaused){
        this.successMessage = 'Début de pause';

      }else{

          this.successMessage = 'Fin de pause';

      }
      } else{
        alert("Veuillez ajouter l'heure de début de votre travail");
      }
      console.log('Pause managed:', response);
    },
    error => {
      console.error('Error managing pause:', error);
    }
  );
}

togglePause() {
  this.managePause();
}


  reloadCurrentRoute() {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigate([this.router.url]);
  }













}


