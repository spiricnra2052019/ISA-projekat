import { Component, OnInit } from '@angular/core';
import { RegisteredUser } from '../registered-user';
import { RegisteredUserService } from '../registered-user.service';
import { Observable } from 'rxjs/internal/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Component({
  selector: 'app-nikola-test',
  templateUrl: './nikola-test.component.html',
  styleUrls: ['./nikola-test.component.css']
})
export class NikolaTestComponent implements OnInit {
  users: RegisteredUser[] = [];
  searchProperty = '';
  searchUrl: string;

  constructor(private registeredUserService: RegisteredUserService, private http: HttpClient) {
    this.searchUrl = 'http://localhost:8080/users/search?query=MARKO';
  }

  searchFun(searchValue) {
    this.searchProperty = searchValue.value;
    this.registeredUserService.findAllAndSearch(this.searchProperty.toUpperCase()).subscribe(res => {
      this.users = res;
    });
  }



  ngOnInit(): void {

    this.registeredUserService.findAll().subscribe(res => {
      this.users = res;
    });
  }
}

