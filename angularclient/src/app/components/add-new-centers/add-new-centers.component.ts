import { Component, OnInit } from '@angular/core';
import { BloodCenterService } from 'src/app/services/blood-center.service';
import { Router } from '@angular/router';
import { BloodCenter } from 'src/app/model/blood-center';
import { Address } from 'src/app/model/address';
import { BloodAmount } from 'src/app/model/blood-amount';
import { BloodCenterAdministrator } from '../blood-center-admin/blood-center-admin.component';
import { BloodCenterAdminService } from 'src/app/services/blood-center-admin-service.service';

@Component({
  selector: 'app-add-new-centers',
  templateUrl: './add-new-centers.component.html',
  styleUrls: ['./add-new-centers.component.css']
})
export class AddNewCentersComponent {

  bloodCenter: BloodCenter;
  address: Address;
  showAddress: boolean;
  bloodAmount: BloodAmount;
  showBloodAmount: boolean;
  bloodCenterAdministrator: BloodCenterAdministrator;
  showBloodCenterAdmin: boolean;

  constructor(
    private router: Router,
    private bloodCenterService: BloodCenterService,
    private bloodCenterAdminService: BloodCenterAdminService
  ) {
    this.bloodCenter = new BloodCenter();
    this.address = new Address();
    this.bloodAmount = new BloodAmount();
    this.bloodCenterAdministrator = new BloodCenterAdministrator();
  }

  addCenter() {
    this.bloodCenter.address = this.address;
    this.bloodCenter.bloodAmount = this.bloodAmount;
    this.bloodCenter.bloodCenterAdministrator = this.bloodCenterAdministrator;

    this.bloodCenterAdminService.save(this.bloodCenter.bloodCenterAdministrator).subscribe(result => this.gotoUserList())
    this.bloodCenterService.save(this.bloodCenter).subscribe(result => this.gotoUserList());
  }

  gotoUserList() {
    this.router.navigate(['/blood-centers']);
  }

  addAddress() {
    this.showAddress = !this.showAddress;
  }

  addBloodAmount() {
    this.showBloodAmount = !this.showBloodAmount;
  }

  addBloodCenterAdmin() {
    this.showBloodCenterAdmin = !this.showBloodCenterAdmin;
  }
}
