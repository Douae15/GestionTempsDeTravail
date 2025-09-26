import { Employee } from "./employee";
import { Person } from "./person";

export class Admin extends Person {
    idAdmin:number | undefined;
    employee: Employee[] | undefined;
}
