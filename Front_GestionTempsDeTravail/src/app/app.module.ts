import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './navbar/navbar.component';
import { HomeEmployeeComponent } from './home-employee/home-employee.component';
import { ClockComponent } from './clock/clock.component';
import { HumeurComponent } from './humeur/humeur.component';
import { ProfileEditComponent } from './profile-edit/profile-edit.component';
import { MatDialogModule } from '@angular/material/dialog';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeAdminComponent } from './home-admin/home-admin.component';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { WeekDialogComponent } from './week-dialog/week-dialog.component';
import { IndexComponent } from './index/index.component';




@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavbarComponent,
    HomeEmployeeComponent,
    ClockComponent,
    HumeurComponent,
    ProfileEditComponent,
    HomeAdminComponent,
    WeekDialogComponent,
    IndexComponent,

  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CommonModule,
    MatDialogModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
  ],
  providers: [
    
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
