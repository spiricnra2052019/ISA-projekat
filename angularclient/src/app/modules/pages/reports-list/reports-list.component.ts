import { Component, OnInit } from '@angular/core';
import { EmployeeReport } from 'src/app/modules/blood-donation/model/employee-report';
import { EmployeereportService } from '../../blood-donation/services/employeereport.service';

@Component({
  selector: 'app-reports-list',
  templateUrl: './reports-list.component.html',
  styleUrls: ['./reports-list.component.css']
})
export class ReportsListComponent implements OnInit {

  searchProperty = '';
  searchUrl: string;
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

  searchFun(searchValue) {
    this.searchProperty = searchValue.value;
    this.employeeReportService.searchByBTandDesc(this.searchProperty.toUpperCase()).subscribe(res => {
      this.reports = res;
    });
  }

}
