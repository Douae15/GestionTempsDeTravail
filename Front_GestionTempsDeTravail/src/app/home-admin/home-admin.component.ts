import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Employee } from '../entity/employee';
import html2canvas from 'html2canvas';
import jspdf from 'jspdf';
import { MatDialog } from '@angular/material/dialog';
import { WeekDialogComponent } from '../week-dialog/week-dialog.component';

@Component({
  selector: 'app-home-admin',
  templateUrl: './home-admin.component.html',
  styleUrls: ['./home-admin.component.css']
})

export class HomeAdminComponent implements OnInit {
  totalEmployees: number | undefined;

  employees: Employee[] = [];




  constructor(private http: HttpClient, public dialog: MatDialog) {}

  ngOnInit() {
    this.http.get<number>('http://localhost:8081/employee/total-employees').subscribe((count) => {
      this.totalEmployees = count;
    });
    this.http.get<Employee[]>('http://localhost:8081/employee/listEmployee').subscribe((data) => {
      this.employees = data;
    });

  }

  openRapportPopup(idEmployee: any,nom:any,prenom:any): void {
    const dialogRef = this.dialog.open(WeekDialogComponent, {
      data: { idEmployee: idEmployee ,
              nom:nom,
              prenom:prenom
      },
      width: '500px'
    });
  }

  /*
  generateRapport(employeeId: number) {
    // Get the HTML element to capture (in this case, the table)
    const elementToCapture = document.getElementById('employeeTable');

    if (elementToCapture) {
      // Use html2canvas to capture the HTML content as an image
      html2canvas(elementToCapture).then((canvas) => {
        const imgData = canvas.toDataURL('image/png');

        // Create a PDF document
        const pdf = new jspdf('p', 'mm', 'a4');
        pdf.addImage(imgData, 'PNG', 0, 0, 210, 297); // Adjust the dimensions as needed

        // Save or download the PDF
        pdf.save('rapport.pdf');
      });
    } else {
      console.error('Element not found: employeeTable');
    }
  }
*/

}
