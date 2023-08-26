import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Address } from 'src/app/modules/blood-donation/model/address';
import { RegisteredUser } from 'src/app/modules/blood-donation/model/registered-user';
import { RegisteredUserService } from '../../blood-donation/services/registered-user.service';
import { FormsModule } from '@angular/forms';
import { UserToken } from '../../blood-donation/model/user-token';
import { TokenStorageService } from '../../blood-donation/services/token-storage.service';
import { EditRegisteredUser } from '../../blood-donation/model/edit-registered-user';

@Component({
  selector: 'app-edit-user-form',
  templateUrl: './edit-user-form.component.html',
  styleUrls: ['./edit-user-form.component.css']
})
export class EditUserFormComponent implements OnInit {

  userToken: UserToken
  user: RegisteredUser;
  showAddress: boolean;
  confirmPassword: string;
  address: Address;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: RegisteredUserService,
    private tokenService: TokenStorageService) {
    this.user = new RegisteredUser();
    this.address = new Address();
  }

  ngOnInit(): void {
    this.userToken = this.tokenService.getUser();
    if (this.userToken.role.toString() == "RegisteredUser")
      this.userService.findPatientById(this.userToken.id).subscribe(data => {
        this.user = new EditRegisteredUser(data.id, data.name, data.lastname, data.username, data.password, data.birthday, data.address);
        this.address = this.user.address;
        console.log(this.user);
      })
    else if (this.userToken.role.toString() == "Administrator")
      this.userService.findAdminById(this.userToken.id).subscribe(data => {
        this.user = new EditRegisteredUser(data.id, data.name, data.lastname, data.username, data.password, data.birthday, data.address);
        this.address = this.user.address;
        console.log(this.user);
      })
    else
      this.userService.findBloodAdminById(this.userToken.id).subscribe(data => {
        this.user = new EditRegisteredUser(data.id, data.name, data.lastname, data.username, data.password, data.birthday, data.address);
        this.address = this.user.address;
        console.log(this.user);
      })
  }

  addAddress() {
    this.showAddress = !this.showAddress;
  }

  editUser(): void {
    if (this.confirmPassword == this.user.password && this.user.password != "") {
      this.user.address = this.address;
      console.log(this.user)
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
