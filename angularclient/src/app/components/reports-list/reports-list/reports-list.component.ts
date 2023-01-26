import { Component, OnInit } from '@angular/core';
import { EmployeeReport } from 'src/app/model/employee-report';
import { EmployeereportService } from 'src/app/services/employeereport.service';

@Component({
  selector: 'app-reports-list',
  templateUrl: './reports-list.component.html',
  styleUrls: ['./reports-list.component.css']
})
export class ReportsListComponent implements OnInit {

  employeeReport: EmployeeReport;
  reports: EmployeeReport[] = [];
  constructor(private employeeReportService: EmployeereportService) {
    this.employeeReport = new EmployeeReport();
  }

  ngOnInit(): void {
    this.employeeReportService.getAllReports().subscribe(data => {
      this.reports = data;
    });
  }

}
