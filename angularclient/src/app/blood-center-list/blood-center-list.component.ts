import { Component, OnInit, ViewChild } from '@angular/core';
import { BloodCenter } from "../blood-center";
import { BloodCenterService } from "../blood-center.service";
import { MatTableDataSource } from '@angular/material/table';
import { MatSort, Sort } from '@angular/material/sort';
import { LiveAnnouncer } from '@angular/cdk/a11y';
import { ObjectType } from 'typescript';
import { BloodCenterAdminService } from '../blood-center-admin-service.service';

@Component({
  selector: 'app-blood-center-list',
  templateUrl: './blood-center-list.component.html',
  styleUrls: ['./blood-center-list.component.css']
})


export class BloodCenterListComponent implements OnInit {
  displayedColumns: string[] = ['averageRate', 'description', 'name', 'address.city', 'address.street', 'address.streetNumber'];
  centers: MatTableDataSource<BloodCenter> = new MatTableDataSource<BloodCenter>();
  searchProperty = '';
  filterProperty = '';
  searchAndFilter = {};
  showFilter = false;

  constructor(private bloodCenterService: BloodCenterService, private _liveAnnouncer: LiveAnnouncer, private bloodCenterAdminService: BloodCenterAdminService) { }

  @ViewChild(MatSort) sort: MatSort;

  ngOnInit(): void {
    this.bloodCenterService.findAll().subscribe(data => {
      this.centers = new MatTableDataSource(data);
      this.centers.sortingDataAccessor = (item, property) => {
        switch (property) {
          case 'address.city': return item.address.city;
          case 'address.street': return item.address.street;
          case 'address.streetNumber': return item.address.streetNumber;
          default: return item[property];
        }
      };
      this.centers.sort = this.sort;
    })
  }
  filterFun(searchValue) {
    this.filterProperty = searchValue.value;

    this.searchAndFilter = {
      "searchProperty": this.searchProperty.toUpperCase(),
      "filterProperty": this.filterProperty.toUpperCase()
    };
    this.bloodCenterService.findAllAndFilter(this.searchAndFilter).subscribe(res => {
      this.centers = new MatTableDataSource(res);
    });
  }

  searchFun(searchValue) {
    this.searchProperty = searchValue.value;
    this.showFilter = this.searchProperty != "";

    this.bloodCenterService.findAllAndSearch(this.searchProperty.toUpperCase()).subscribe(res => {
      this.centers = new MatTableDataSource(res);
    });
  }

  /** Announce the change in sort state for assistive technology. */
  announceSortChange(sortState: Sort) {
    // This example uses English messages. If your application supports
    // multiple language, you would internationalize these strings.
    // Furthermore, you can customize the message to add additional
    // details about the values being sorted.
    if (sortState.direction) {
      this._liveAnnouncer.announce(`Sorted ${sortState.direction}ending`);
    } else {
      this._liveAnnouncer.announce('Sorting cleared');
    }
  }
}
