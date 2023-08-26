import { Component, OnInit } from '@angular/core';
import { UserToken } from '../../blood-donation/model/user-token';
import { TokenStorageService } from '../../blood-donation/services/token-storage.service';
import { Router } from '@angular/router';
import { RegisteredUserService } from '../../blood-donation/services/registered-user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user: UserToken;
  role: any;
  isLoggedIn: boolean = false;
  penaltyCount: number;

  constructor(private tokenStorageService: TokenStorageService, private router: Router, private registeredUserService: RegisteredUserService) {
    this.user = this.tokenStorageService.getUser()
    this.isLoggedIn = this.tokenStorageService.isLoggedIn()
    this.role = this.user.role
    console.log("User: ", this.user)
    if (this.role == "RegisteredUser") {
      registeredUserService.getPenaltyCount(this.user.id).subscribe(
        data => {
          this.penaltyCount = data
          console.log("Penalty count: ", this.penaltyCount)
        }
      )
    }
  }

  ngOnInit(): void {
  }

}
