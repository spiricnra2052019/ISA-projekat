import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Employee } from '../model/employee';
import { RegisteredUser } from '../model/registered-user';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private employeeUrl: string;

  constructor(private http: HttpClient) {
    this.employeeUrl = 'http://localhost:8080/employers';

  }

  public edit(employee) {
    return this.http.put<Employee>(this.employeeUrl, employee);
  }

  public findById(id) {
    return this.http.get<Employee>(this.employeeUrl + "/" + id);
  }
}
