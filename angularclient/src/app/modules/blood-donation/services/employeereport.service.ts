import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EmployeeReport } from '../model/employee-report';

@Injectable({
  providedIn: 'root'
})
export class EmployeereportService {

  private employeeReportUrl: string;
  private searchUrl: string;

  constructor(private http: HttpClient) {
    this.employeeReportUrl = "http://localhost:8080/employee-report";
    this.searchUrl = 'http://localhost:8080/employee-report/search?query=';
  }

  public getAllReports(): Observable<EmployeeReport[]> {
    return this.http.get<EmployeeReport[]>(this.employeeReportUrl);
  }

  public sendAppealResponse(dto: EmployeeReport): Observable<EmployeeReport> {
    return this.http.post<EmployeeReport>(this.employeeReportUrl, dto);
  }

  public searchByBTandDesc(searchProperty): Observable<EmployeeReport[]> {
    return this.http.get<EmployeeReport[]>(this.searchUrl.concat(searchProperty));
  }

  public getAllReportsByUserId(userId): Observable<EmployeeReport[]> {
    return this.http.get<EmployeeReport[]>(this.employeeReportUrl + '/user/' + userId);
  }

  public sortUserHistoryBy(userId, sortBy): Observable<EmployeeReport[]> {
    return this.http.get<EmployeeReport[]>(this.employeeReportUrl + '/sort/' + userId + "?sortBy=" + sortBy);
  }
}
