import { Component, OnInit } from '@angular/core';
import { BloodCenter } from '../blood-center';
import { Address } from '../address';
import { BloodAmount } from '../blood-amount';
import { Router } from '@angular/router';
import { BloodCenterService } from '../blood-center.service';

@Component({
  selector: 'app-edit-center',
  templateUrl: './edit-center.component.html',
  styleUrls: ['./edit-center.component.css']
})
export class EditCenterComponent implements OnInit {

  bloodCenter :BloodCenter;

  bloodAmount: BloodAmount;
  showAddress : boolean;

  constructor(private router: Router,private bloodCenterService: BloodCenterService) { 
    this.bloodAmount = new BloodAmount();
    this.bloodCenter = new BloodCenter();
  }

  ngOnInit(): void {
    this.bloodCenterService.findById(1).subscribe(data =>{
      this.bloodCenter = data;
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

  editCenter(): void{
    this.bloodCenterService.edit(this.bloodCenter).subscribe(result => this.gotoCenterList());
  }
}
