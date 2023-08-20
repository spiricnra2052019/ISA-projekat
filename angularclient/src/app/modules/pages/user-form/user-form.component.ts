import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Address } from 'src/app/modules/blood-donation/model/address';
import { RegisteredUser } from 'src/app/modules/blood-donation/model/registered-user';
import { RegisteredUserService } from '../../blood-donation/services/registered-user.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent {

  user: RegisteredUser;
  showAddress: boolean;
  confirmPassword: string;
  address: Address;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: RegisteredUserService) {
    this.user = new RegisteredUser();
    this.address = new Address();
  }
  addUser() {
    if (this.confirmPassword === this.user.password) {
      this.user.address = this.address;
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