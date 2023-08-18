import { Component, OnInit } from '@angular/core';
import { BloodCenter } from 'src/app/modules/blood-donation/model/blood-center';
import { Address } from 'src/app/modules/blood-donation/model/address';
import { BloodAmount } from 'src/app/modules/blood-donation/model/blood-amount';
import { Router } from '@angular/router';
import { BloodCenterService } from '../../blood-donation/services/blood-center.service';

@Component({
  selector: 'app-edit-center',
  templateUrl: './edit-center.component.html',
  styleUrls: ['./edit-center.component.css']
})
export class EditCenterComponent implements OnInit {

  bloodCenter: BloodCenter;

  bloodAmount: BloodAmount = new BloodAmount();
  showAddress: boolean;
  address: Address = new Address();

  constructor(private router: Router, private bloodCenterService: BloodCenterService) {
    this.bloodAmount = new BloodAmount();
    this.bloodCenter = new BloodCenter();
    this.address = new Address();
  }

  ngOnInit(): void {
    this.bloodCenterService.findById(1).subscribe(data => {
      this.bloodCenter = data;
      this.address = data.address;
      this.bloodAmount = data.bloodAmount;
      console.log(this.bloodCenter);
      console.log(this.bloodCenter.address.street);
    })
    //this.addressService.findbyId(this.bloodCenter.)
  }
  gotoCenterList() {
    this.router.navigate(['/blood-centers']);
  }

  addAddress() {
    this.showAddress = !this.showAddress;
  }

  editCenter(): void {
    this.bloodCenter.address = this.address;
    this.bloodCenterService.edit(this.bloodCenter).subscribe(result => this.gotoCenterList());
  }
}
