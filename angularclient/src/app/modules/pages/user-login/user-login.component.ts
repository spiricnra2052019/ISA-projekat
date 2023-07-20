import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RegisteredUserService } from '../../blood-donation/services/registered-user.service';
import { AuthService } from '../../blood-donation/services/auth.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {
  username: string;
  password: string;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
  ) { }


  signIn() {
    // Perform sign-in logic here
    var loginRequest = {
      username: this.username,
      password: this.password
    };

    this.authService.authenticate(loginRequest).subscribe(result => {
      if (result) {
        this.router.navigate(['/users']);
      } else {
        this.username = "";
        this.password = "";
      }
    }); // <-- added missing closing parenthesis here
  }

  ngOnInit(): void {
  }

}
