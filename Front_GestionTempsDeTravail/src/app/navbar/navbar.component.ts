import { Component, OnInit } from '@angular/core';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';
import { ProfileEditComponent } from '../profile-edit/profile-edit.component';
import { MatDialog } from '@angular/material/dialog';
import { LogoutService } from '../services/logout.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  [x: string]: any;
  user: any;


  constructor(private loginService: LoginService, private logoutService: LogoutService,private router: Router, public dialog: MatDialog) {}

  ngOnInit(): void {
    this.user = this.loginService.getCurrentUser();
    console.log('User:', this.user);
  }

  handleLogout() {
    this.logoutService.logout();
  }

  isAdmin(): boolean {
    return this.loginService.hasAuthority('ROLE_ADMIN');
  }

  isEmployee(): boolean {
    return this.loginService.hasAuthority('ROLE_EMPLOYEE');
  }


  openUserProfilePopup(): void {
    const dialogRef = this.dialog.open(ProfileEditComponent, {
      data: this.user,
      width: '500px',
    });
  
    dialogRef.afterClosed().subscribe((result: any) => {
      console.log(`User profile popup closed: ${result}`);
    });
  }
  

}
