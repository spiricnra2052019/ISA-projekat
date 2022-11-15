import { Component, OnInit } from '@angular/core';
import { BloodCenterService } from '../blood-center.service';
import { Router } from '@angular/router';
import { BloodCenter } from '../blood-center';
import { Address } from '../address';
import { BloodAmount } from '../blood-amount';

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

  constructor(
    private router: Router,
    private bloodCenterService: BloodCenterService) {
    this.bloodCenter = new BloodCenter();
    this.address = new Address();
    this.bloodAmount = new BloodAmount();
  }

  addCenter() {
    this.bloodCenter.address = this.address;
    this.bloodCenter.bloodAmount = this.bloodAmount;
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
}
