import { Component, OnInit } from '@angular/core';
import { BloodCenterService } from '../blood-center.service';
import { Router } from '@angular/router';
import { BloodCenter } from '../blood-center';
import { Address } from '../address';
import { BloodAmount } from '../blood-amount';
import { BloodCenterAdmin } from '../../app/blood-center-admin/blood-center-admin.component'
import { BloodCenterAdminService } from '../blood-center-admin-service.service';

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
  bloodCenterAdmin: BloodCenterAdmin;
  showBloodCenterAdmin: boolean;

  constructor(
    private router: Router,
    private bloodCenterService: BloodCenterService,
    private bloodCenterAdminService: BloodCenterAdminService
  ) {
    this.bloodCenter = new BloodCenter();
    this.address = new Address();
    this.bloodAmount = new BloodAmount();
    this.bloodCenterAdmin = new BloodCenterAdmin();
  }

  addCenter() {
    this.bloodCenter.address = this.address;
    this.bloodCenter.bloodAmount = this.bloodAmount;
    this.bloodCenter.bloodCenterAdmin = this.bloodCenterAdmin;
    console.log(this.bloodCenter.bloodCenterAdmin);
    this.bloodCenterAdminService.save(this.bloodCenter.bloodCenterAdmin).subscribe(result => this.gotoUserList())
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
