import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../blood-donation/services/auth.service';
import { TokenStorageService } from '../../blood-donation/services/token-storage.service';

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
    private tokenStorageService: TokenStorageService
  ) { }


  signIn() {
    // Perform sign-in logic here
    var loginRequest = {
      username: this.username,
      password: this.password
    };

    this.authService.authenticate(loginRequest).subscribe(result => {
      if (result) {
        this.tokenStorageService.saveToken(result.token);
        this.tokenStorageService.saveUser(result.token);
        this.router.navigate(['/blood-centers']).then(
          () => {
            window.location.reload();
          }
        );
        alert("Success!");
      } else {
        this.username = "";
        this.password = "";
      }
    });
  }

  ngOnInit(): void {
  }

}
