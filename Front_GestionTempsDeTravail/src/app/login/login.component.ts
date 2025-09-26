import {Component} from '@angular/core';

import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent {

  email: string = '';
  mdp: string = '';

  errorMessage: any;

  constructor(
     private loginService: LoginService,
     private router :Router
     ){

  }

  ngOnInit(): void {


  }



  handleLogin(){
    if (this.email && this.mdp) {
      this.loginService.login(this.email, this.mdp).subscribe(
        response => {
          const responseObject = JSON.parse(response);
          const role = responseObject.role;
          if (role === 'admin') {
            this.router.navigateByUrl("/homeAdmin");
          }else if (role === 'employee') {
            this.router.navigateByUrl("/homeEmployee");
          }else{
            this.router.navigateByUrl("/home");
          }
          //alert("Login successfully")
        },
        error => alert("Please enter correct email and password")
      );
    }
  }




}

