import { Component, OnInit } from '@angular/core';
import { RegisteredUser } from '../registered-user';
import { RegisteredUserService } from '../registered-user.service';


@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users: RegisteredUser[] = [];

  constructor(private registeredUserService: RegisteredUserService) { }

  ngOnInit(): void {
    this.registeredUserService.findAll().subscribe(data => {
      this.users = data;
    })
  }

}
