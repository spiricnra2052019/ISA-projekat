import { Component, OnInit } from '@angular/core';
import { RegisteredUser } from 'src/app/model/registered-user';
import { RegisteredUserService } from 'src/app/services/registered-user.service';
import { Observable } from 'rxjs/internal/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';


@Component({
  selector: 'app-nikola-test',
  templateUrl: './users-search.component.html',
  styleUrls: ['./users-search.component.css']
})
export class UsersSearchComponent implements OnInit {
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

