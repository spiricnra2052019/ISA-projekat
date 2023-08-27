import { Component, OnInit } from '@angular/core';
import { UserToken } from '../../blood-donation/model/user-token';
import { TokenStorageService } from '../../blood-donation/services/token-storage.service';
import { Router } from '@angular/router';
import { BloodCenter } from '../../blood-donation/model/blood-center';
import { FormGroup } from '@angular/forms';
import { BloodCenterService } from '../../blood-donation/services/blood-center.service';
import { FormBuilder } from '@angular/forms';
import { RegisteredUserService } from '../../blood-donation/services/registered-user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  centers: BloodCenter[];
  sortBy: string;
  searchProperty = '';
  filterProperty = '';
  searchAndFilter = {};
  showFilter = false;
  contactForm: FormGroup;
  selectedDevice: string;

  columns = [
    { id: 1, name: 'Average Rate', value: 'averageRate' },
    { id: 2, name: 'Description', value: 'description' },
    { id: 3, name: 'Name', value: 'name' },
    { id: 4, name: 'City', value: 'address.city' },
    { id: 5, name: 'Street', value: 'address.street' },
    { id: 6, name: 'Street Number', value: 'address.streetNumber' },
  ];

  user: UserToken;
  role: any;
  isLoggedIn: boolean = false;
  penaltyCount: number;


  constructor(private tokenStorageService: TokenStorageService, private router: Router, private registeredUserService: RegisteredUserService) {
    this.user = this.tokenStorageService.getUser()
    this.isLoggedIn = this.tokenStorageService.isLoggedIn()
    this.role = this.user.role
    console.log("User: ", this.user)
    if (this.role == "RegisteredUser") {
      registeredUserService.getPenaltyCount(this.user.id).subscribe(
        data => {
          this.penaltyCount = data
          console.log("Penalty count: ", this.penaltyCount)
        }
      )
    }
  }

  ngOnInit(): void {
    this.bloodCenterService.findAll().subscribe((data) => {
      this.centers = data;
    });
    this.contactForm = this.fb.group({
      columns: [null],
    });
  }
  filterFun(searchValue) {
    this.filterProperty = searchValue.value;

    this.searchAndFilter = {
      searchProperty: this.searchProperty.toUpperCase(),
      filterProperty: this.filterProperty.toUpperCase(),
    };
    this.bloodCenterService
      .findAllAndFilter(this.searchAndFilter)
      .subscribe((res) => {
        this.centers = res;
      });
  }

  searchFun(searchValue) {
    this.searchProperty = searchValue.value;
    this.showFilter = this.searchProperty != '';

    this.bloodCenterService
      .findAllAndSearch(this.searchProperty.toUpperCase())
      .subscribe((res) => {
        this.centers = res;
      });
  }

  onChange(deviceValue) {
    this.selectedDevice = deviceValue;
    this.bloodCenterService.sortBy(deviceValue).subscribe((res) => {
      this.centers = res;
    });
  }

  goToFreeAppointments(id) {
    this.router.navigate(['/user-schedule-appointment/' + id]);
  }
}
