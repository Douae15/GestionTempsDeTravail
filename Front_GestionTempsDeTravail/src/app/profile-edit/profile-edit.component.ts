import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-profile-edit',
  templateUrl: './profile-edit.component.html',
  styleUrls: ['./profile-edit.component.css']
})
export class ProfileEditComponent implements OnInit {
  user: any;
  updatedUser: any = {};
  message:any;

  constructor(private loginService: LoginService, private ref: MatDialogRef<ProfileEditComponent>) {}

  ngOnInit(): void {
    this.user = this.loginService.getCurrentUser();
    this.updatedUser.user = {}; // Initialize the 'user' property
    this.updatedUser.user.nom = this.user.user.nom;
    this.updatedUser.user.prenom = this.user.user.prenom;
    this.updatedUser.user.mdp = this.user.user.mdp;
    this.updatedUser.user.image = this.user.user.image;
    this.updatedUser.user.email = this.user.user.email;
    
    if (this.user.role == 'employee') {
      this.updatedUser.user.id = this.user.user.idEmployee;
      
    } else {
      this.updatedUser.user.id = this.user.user.idAdmin;
    }
    
    this.updatedUser.role = this.user.role;
  }
  

  closepopup() {
    this.ref.close('Closed using function');
  }

  editProfil() {
    this.loginService.updateUserProfile(this.updatedUser).subscribe(
      (updatedUser) => {
        localStorage.removeItem('authUser');
        localStorage.setItem('authUser', JSON.stringify(updatedUser)); 
        console.log('Profil mis à jour avec succès', updatedUser);
        this.ref.close('Closed using function');
        alert('Profil mis à jour avec succès');
      },
      (error) => {
        console.error('Erreur lors de la mise à jour du profil', error);
      }
    );
  }
  
}
