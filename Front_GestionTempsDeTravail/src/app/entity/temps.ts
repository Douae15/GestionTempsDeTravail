import { Time } from "@angular/common";

export class Temps {
  idTemps?: number; // Make idTemps optional by using '?'
  idEmployee: number | undefined;
  date: Date | undefined;
  heureDebut: String | undefined;
  heureFin: String | undefined;
  idPause: number | undefined;
}
