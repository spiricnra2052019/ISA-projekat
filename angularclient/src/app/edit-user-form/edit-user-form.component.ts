import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Address } from '../address';
import { RegisteredUser } from '../registered-user';
import { RegisteredUserService } from '../registered-user.service';

@Component({
  selector: 'app-edit-user-form',
  templateUrl: './edit-user-form.component.html',
  styleUrls: ['./edit-user-form.component.css']
})
export class EditUserFormComponent implements OnInit {

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

  ngOnInit(): void {
    this.userService.findById(1).subscribe(data => {
      this.user = data;
      this.address = this.user.address;
    })
  }

  addAddress() {
    this.showAddress = !this.showAddress;
  }

  editUser(): void {
    if (this.confirmPassword == this.user.password && this.user.password != "") {
      this.user.address = this.address;
      this.userService.update(this.user).subscribe(result => this.gotoUserList());
    } else {
      this.confirmPassword = "";
      this.user.password = "";
    }
  }

  gotoUserList() {
    this.router.navigate(['/users']);
  }

}
