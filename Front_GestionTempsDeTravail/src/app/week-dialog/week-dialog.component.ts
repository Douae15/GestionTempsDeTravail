import { Component, Inject } from '@angular/core';
import { RapportService } from '../services/rapport.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';


@Component({
  selector: 'app-week-dialog',
  templateUrl: './week-dialog.component.html',
  styleUrls: ['./week-dialog.component.css']
})
export class WeekDialogComponent {

  rapports:any;
  idEmployee:any;
  nom:any;
  prenom:any;
  selectedWeekStartDate: any | undefined;
  selectedValueFromSecondSelect: any |undefined;
  constructor(private router: Router ,private rapportServices:RapportService
    ,private route: ActivatedRoute, @Inject(MAT_DIALOG_DATA) public data: any , private ref: MatDialogRef<WeekDialogComponent>){
      this.nom=data.nom;
      this.prenom=data.prenom;
      this.idEmployee = data.idEmployee;
  }

  formatCustomDate(dateString: string): string {
    const date = new Date(dateString);
    return date.toLocaleDateString('fr-FR', {
      weekday: 'long',
      year: 'numeric',
      month: 'long',
      day: 'numeric'
    });
  }

  ngOnInit(): void {
    console.log("hiiiiiiii  "+this.idEmployee)
    this.afficherRapports(this.idEmployee);
  }
  
  getStartOfWeek(dateString: string): string {
    const date = new Date(dateString);
    const day = date.getDay();
    const diff = date.getDate() - day + (day === 0 ? -6 : 1);
    const startOfWeek = new Date(date);
    startOfWeek.setDate(diff);
    return startOfWeek.toISOString().split('T')[0]; 
  }

  subtractDayFromDate(dateString: string): string {
    const date = new Date(dateString);
    date.setDate(date.getDate() - 1);
    return date.toISOString().split('T')[0];
  }
  


  afficherRapports(idEmployee:number){
    this.rapportServices.employeeRapports(idEmployee).subscribe(
      response => {
        this.rapports=response;
    }
    ,
      error => {
        console.error('Temps resource does not exist:', error);

      }
    );
  }


  closepopup() {

    this.ref.close('Closed using function');

  }

  }



