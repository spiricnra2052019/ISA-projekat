import { Component, OnInit } from '@angular/core';
import { RegisteredUser } from 'src/app/model/registered-user';
import { RegisteredUserService } from 'src/app/services/registered-user.service';


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
