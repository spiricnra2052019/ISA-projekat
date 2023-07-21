import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { UserToken } from '../blood-donation/model/user-token';
import { TokenStorageService } from '../blood-donation/services/token-storage.service';

@Component({
    selector: 'app-navbar',
    templateUrl: './navbar.component.html',
    styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

    user: UserToken;
    role: any;
    isLoggedIn: boolean = false;

    constructor(private tokenStorageService: TokenStorageService, private router: Router) {
        this.user = this.tokenStorageService.getUser()
        this.isLoggedIn = this.tokenStorageService.isLoggedIn()
        this.role = this.user.role
        console.log("Role: ", this.role)
    }

    ngOnInit(): void {
    }

    signOut() {
        this.tokenStorageService.signOut()
        this.router.navigate(['']).then(() => {
            window.location.reload();
        })
    }

    routeToSignUp(): void {
        this.router.navigate(['/sign-up']);
    }

    routeToSignIn(): void {
        this.router.navigate(['/sign-in']);
    }

    routeToCart(): void {
        this.router.navigate(['/cart']);
    }

}