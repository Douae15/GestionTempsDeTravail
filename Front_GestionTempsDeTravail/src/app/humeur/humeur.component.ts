import { Component } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { HumeurService } from '../services/humeur.service';

@Component({
  selector: 'app-humeur',
  templateUrl: './humeur.component.html',
  styleUrls: ['./humeur.component.css']
})
export class HumeurComponent {

  token:any;
  tokenStatut:any;
  selectedHumeur: string | undefined;
  error:any;
  message:any;

  constructor(private router: Router ,private humeurService: HumeurService
    ,private route: ActivatedRoute){

  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      const token = params['token'];
      this.humeurService.isValidToken(token).subscribe(
        response => {
          this.tokenStatut=response;
           console.log(this.tokenStatut);
        },
        error => {
          var message = localStorage.getItem('message');
          if (message) {
          this.message=message;
          localStorage.removeItem('message');
          }else{
            this.message="Ce lien est expiré ou incorrect.";
          }
        }
      );
    });
  }
  

  ajouterHumeur(){
    if(this.selectedHumeur!=null){
    this.route.params.subscribe(params => {
      const token = params['token'];
    const data = {
      "token": token,
      "humeur": this.selectedHumeur,
      "date": new Date()
  };
console.log(token);
    this.humeurService.ajouterHumeur(data).subscribe(
      response => {
        localStorage.setItem('message', 'Votre humeur a été enregistrée avec succès.');
        window.location.reload();
        
      },
      error => {
        console.error('Erreur lors de la requête :', error);
      }
    );

    });
  }else{
    this.error="Veuillez choisir une humeur avant d'envoyer.";
}
} 
  
}
