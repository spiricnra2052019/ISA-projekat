import { Component, OnInit } from '@angular/core';
import { BloodCenterListComponent } from '../blood-center-list/blood-center-list.component';
import { BloodCenterService } from '../blood-center.service';
import { Router } from '@angular/router';
import { BloodCenter } from '../blood-center';

@Component({
  selector: 'app-add-new-centers',
  templateUrl: './add-new-centers.component.html',
  styleUrls: ['./add-new-centers.component.css']
})
export class AddNewCentersComponent implements OnInit {

  bloodCenter: BloodCenter;

  constructor(
    private router: Router,
    private bloodCenterService: BloodCenterService) {
    this.bloodCenter = new BloodCenter();
  }

  addCenter() {
    this.bloodCenterService.save(this.bloodCenter).subscribe(result => this.gotoUserList());
  }

  gotoUserList() {
    this.router.navigate(['/blood-center']);
  }

  ngOnInit(): void {

  }

}
