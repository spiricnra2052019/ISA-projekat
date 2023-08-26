import { Component, OnInit, ViewChild } from '@angular/core';
import { BloodCenter } from 'src/app/modules/blood-donation/model/blood-center';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort, Sort } from '@angular/material/sort';
import { LiveAnnouncer } from '@angular/cdk/a11y';
import { ObjectType } from 'typescript';
import { FormBuilder, FormGroup } from "@angular/forms";
import { BloodCenterService } from '../../blood-donation/services/blood-center.service';
import { BloodCenterAdminService } from '../../blood-donation/services/blood-center-admin-service.service';
import { Router } from '@angular/router';
import { TokenStorageService } from '../../blood-donation/services/token-storage.service';

@Component({
  selector: 'app-blood-center-list',
  templateUrl: './blood-center-list.component.html',
  styleUrls: ['./blood-center-list.component.css']
})


export class BloodCenterListComponent implements OnInit {
  centers: BloodCenter[];
  sortBy: string;
  searchProperty = '';
  filterProperty = '';
  searchAndFilter = {};
  showFilter = false;
  contactForm: FormGroup;
  selectedDevice: string;
  role: any;

  columns = [
    { id: 1, name: "Average Rate", value: "averageRate" },
    { id: 2, name: "Description", value: "description" },
    { id: 3, name: "Name", value: "name" },
    { id: 4, name: "City", value: "address.city" },
    { id: 5, name: "Street", value: "address.street" },
    { id: 6, name: "Street Number", value: "address.streetNumber" }
  ];

  constructor(private bloodCenterService: BloodCenterService, private _liveAnnouncer: LiveAnnouncer, private bloodCenterAdminService: BloodCenterAdminService,
    private fb: FormBuilder, private router: Router, private tokenService: TokenStorageService) {
    this.role = this.tokenService.getUser().role;
  }

  ngOnInit(): void {
    this.bloodCenterService.findAll().subscribe(data => {
      this.centers = data;
    })
    this.contactForm = this.fb.group({
      columns: [null]
    });
  }
  filterFun(searchValue) {
    this.filterProperty = searchValue.value;

    this.searchAndFilter = {
      "searchProperty": this.searchProperty.toUpperCase(),
      "filterProperty": this.filterProperty.toUpperCase()
    };
    this.bloodCenterService.findAllAndFilter(this.searchAndFilter).subscribe(res => {
      this.centers = res;
    });
  }

  searchFun(searchValue) {
    this.searchProperty = searchValue.value;
    this.showFilter = this.searchProperty != "";

    this.bloodCenterService.findAllAndSearch(this.searchProperty.toUpperCase()).subscribe(res => {
      this.centers = res;
    });
  }

  onChange(deviceValue) {
    this.selectedDevice = deviceValue;
    this.bloodCenterService.sortBy(deviceValue).subscribe(res => {
      this.centers = res;
    });
  }

  goToFreeAppointments(id) {
    this.router.navigate(['/user-schedule-appointment/' + id]);
  }

}
