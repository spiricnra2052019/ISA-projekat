import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-employee',
  templateUrl: './edit-employee.component.html',
  styleUrls: ['./edit-employee.component.css']
})
export class EditEmployeeComponent implements OnInit {

  employee : Employee;
  confirmPassword : string;

  constructor(private router: Router, private employeeService: EmployeeService) {
    this.employee = new Employee();
   }

  ngOnInit(): void {
    this.employeeService.findById(1).subscribe(data => {
      this.employee = data;
  })
  }
  editEmployee(): void {
    if (this.confirmPassword == this.employee.password && this.employee.password !=""){
      this.employeeService.edit(this.employee).subscribe(res => this.gotoUserList());
    }
    else {
      alert("Incorrect password!!!")
    }
  }
  gotoUserList() {
    this.router.navigate(['/users']);
  }
}

