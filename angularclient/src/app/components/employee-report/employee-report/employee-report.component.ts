import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { EmployeeReport } from 'src/app/model/employee-report';
import { EmployeereportService } from 'src/app/services/employeereport.service';


@Component({
  selector: 'app-employee-report',
  templateUrl: './employee-report.component.html',
  styleUrls: ['./employee-report.component.css']
})
export class EmployeeReportComponent implements OnInit {

  employeeReport: EmployeeReport;
  constructor(private employeeReportService: EmployeereportService, private router: Router) {
    this.employeeReport = new EmployeeReport();
  }

  ngOnInit(): void {
  }

  addReport(event) {
    event.preventDefault();
    this.employeeReportService.sendAppealResponse(this.employeeReport).subscribe(data => {
      this.router.navigate(['/report-list']);
    });
  }

  addBloodType(bloodType) {
    this.employeeReport.bloodType = bloodType.value;
  }

  addQuantity(quan) {
    this.employeeReport.quantity = quan.value;
  }

  addEquipmentNumber(equNum) {
    this.employeeReport.numberOfEquipmentUsed = equNum.value;
  }

  addDescription(desc) {
    this.employeeReport.description = desc.value;
  }
}
