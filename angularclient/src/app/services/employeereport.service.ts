import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EmployeeReport } from '../model/employee-report';

@Injectable({
  providedIn: 'root'
})
export class EmployeereportService {

  private employeeReportUrl: string;

  constructor(private http: HttpClient) {
    this.employeeReportUrl = "http://localhost:8080/employee-report"
  }

  public getAllReports(): Observable<EmployeeReport[]> {
    return this.http.get<EmployeeReport[]>(this.employeeReportUrl);
  }

  public sendAppealResponse(dto: EmployeeReport): Observable<EmployeeReport> {
    return this.http.post<EmployeeReport>(this.employeeReportUrl, dto);
  }
}
