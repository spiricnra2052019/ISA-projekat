import { Component, OnInit } from '@angular/core';
import { EmployeeReport } from '../../blood-donation/model/employee-report';
import { EmployeereportService } from '../../blood-donation/services/employeereport.service';
import { UserToken } from '../../blood-donation/model/user-token';
import { TokenStorageService } from '../../blood-donation/services/token-storage.service';

@Component({
  selector: 'app-user-history',
  templateUrl: './user-history.component.html',
  styleUrls: ['./user-history.component.css']
})

export class UserHistoryComponent implements OnInit {
  user: UserToken;
  sortBy: string;
  searchProperty = '';
  searchUrl: string;
  reports: EmployeeReport[] = [];

  columns = [
    { id: 1, name: "Blood Type", value: "bloodType" },
    { id: 2, name: "Blood Quantity", value: "bloodQuantity" },
    { id: 3, name: "Number of Equipment Used", value: "numberOfEquipmentUsed" },
    { id: 4, name: "Description", value: "description" }
  ];


  constructor(private employeeReportService: EmployeereportService, private tokenStorageService: TokenStorageService) {
    this.user = this.tokenStorageService.getUser()
  }

  ngOnInit(): void {
    this.employeeReportService.getAllReportsByUserId(this.user.id).subscribe(data => {
      this.reports = data;
    });
  }

  searchFun(searchValue) {
    this.searchProperty = searchValue.value;
    this.employeeReportService.searchByBTandDesc(this.searchProperty.toUpperCase()).subscribe(res => {
      this.reports = res;
    });
  }

  onChange(deviceValue) {
    this.sortBy = deviceValue;
    this.employeeReportService.sortUserHistoryBy(this.user.id, this.sortBy).subscribe(res => {
      this.reports = res;
    });
  }

}
