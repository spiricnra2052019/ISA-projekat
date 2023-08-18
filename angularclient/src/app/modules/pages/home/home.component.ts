import { Component, OnInit } from '@angular/core';
import { UserToken } from '../../blood-donation/model/user-token';
import { TokenStorageService } from '../../blood-donation/services/token-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user: UserToken;
  role: any;
  isLoggedIn: boolean = false;

  constructor(private tokenStorageService: TokenStorageService, private router: Router) {
    this.user = this.tokenStorageService.getUser()
    this.isLoggedIn = this.tokenStorageService.isLoggedIn()
    this.role = this.user.role
    console.log("User: ", this.user)
  }

  ngOnInit(): void {
  }

}
