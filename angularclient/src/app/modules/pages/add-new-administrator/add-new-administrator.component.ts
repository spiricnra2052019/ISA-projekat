import { Component, OnInit } from '@angular/core';
import { Administrator } from 'src/app/modules/blood-donation/model/administrator';
import { Address } from 'src/app/modules/blood-donation/model/address';
import { Router } from '@angular/router';
import { AdministratorService } from '../../blood-donation/services/administrator.service';
@Component({
  selector: 'app-add-new-administrator',
  templateUrl: './add-new-administrator.component.html',
  styleUrls: ['./add-new-administrator.component.css']
})
export class AddNewAdministratorComponent implements OnInit {

  administrators: Administrator[] = [];
  administrator: Administrator;
  showAddress: boolean;
  confirmPassword: string;
  address: Address;
  constructor(private administratorService: AdministratorService, private router: Router) {
    this.administrator = new Administrator();
    this.address = new Address();
  }

  ngOnInit(): void {
    this.administratorService.getAll().subscribe(data => {
      this.administrators = data;
    })
  }

  addAdministrator() {
    if (this.confirmPassword === this.administrator.password) {
      this.administrator.address = this.address;
      this.administratorService.addAdministrator(this.administrator).subscribe(result => this.gotoUserList());
    } else {
      this.confirmPassword = "";
      this.administrator.password = "";
    }
  }

  gotoUserList() {
    this.router.navigate(['/users']);
  }

  addAddress() {
    this.showAddress = !this.showAddress;
  }

}
