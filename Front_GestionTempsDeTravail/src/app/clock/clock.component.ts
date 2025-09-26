import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-clock',
  templateUrl: './clock.component.html',
  styleUrls: ['./clock.component.css']
})
export class ClockComponent implements OnInit{
  hours = 0;
  minutes = 0;
  seconds = 0;

  ngOnInit(): void {
    this.updateClock();
    setInterval(() => this.updateClock(), 1000);
  }

  updateClock() {
    const date = new Date();
    this.hours = ((date.getHours() + 11) % 12 + 1);
    this.minutes = date.getMinutes();
    this.seconds = date.getSeconds();
  }
}
