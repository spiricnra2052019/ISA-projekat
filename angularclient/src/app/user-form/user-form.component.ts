import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RegisteredUser } from '../registered-user';
import { RegisteredUserService } from '../registered-user.service';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent {

  user: RegisteredUser;
  showAddress: boolean;
  confirmPassword: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: RegisteredUserService) {
    this.user = new RegisteredUser();
  }
  addUser() {
    if (this.confirmPassword === this.user.password) {
      this.userService.save(this.user).subscribe(result => this.gotoUserList());
    } else {
      this.confirmPassword = "";
      this.user.password = "";
    }
  }

  gotoUserList() {
    this.router.navigate(['/users']);
  }

  addAddress() {
    this.showAddress = !this.showAddress;
  }
}