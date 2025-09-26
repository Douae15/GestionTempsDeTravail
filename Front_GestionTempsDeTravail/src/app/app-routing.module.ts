import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HomeEmployeeComponent } from './home-employee/home-employee.component';
import { HumeurComponent } from './humeur/humeur.component';
import { ProfileEditComponent } from './profile-edit/profile-edit.component';
import { HomeAdminComponent } from './home-admin/home-admin.component';
import { WeekDialogComponent } from './week-dialog/week-dialog.component';
import { IndexComponent } from './index/index.component';



const routes: Routes = [
  {path:"", component:IndexComponent},
  {path:"login", component: LoginComponent},
  {path:"home",component: NavbarComponent},
  {path:"homeEmployee",component: HomeEmployeeComponent},
  {path:"homeAdmin",component: HomeAdminComponent},
  {path:"editProfile/:id",component: ProfileEditComponent},
  {path:"humeur/:token", component: HumeurComponent},
  { path: 'rapports', component: WeekDialogComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
