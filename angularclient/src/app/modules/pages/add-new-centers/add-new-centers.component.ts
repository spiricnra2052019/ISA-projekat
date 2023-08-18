import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BloodCenter } from 'src/app/modules/blood-donation/model/blood-center';
import { Address } from 'src/app/modules/blood-donation/model/address';
import { BloodAmount } from 'src/app/modules/blood-donation/model/blood-amount';
import { BloodCenterService } from '../../blood-donation/services/blood-center.service';
import { BloodCenterAdminService } from '../../blood-donation/services/blood-center-admin-service.service';
import { BloodCenterAdministrator } from '../../blood-donation/model/blod-center-administrator';

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
