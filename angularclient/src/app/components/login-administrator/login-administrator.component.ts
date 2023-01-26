import { Component, OnInit } from '@angular/core';
import { Router, NavigationExtras } from '@angular/router';
import { Administrator } from 'src/app/model/administrator';
import { AdministratorService } from 'src/app/services/administrator.service';

@Component({
  selector: 'app-login-administrator',
  templateUrl: './login-administrator.component.html',
  styleUrls: ['./login-administrator.component.css']
})
export class LoginAdministratorComponent implements OnInit {

  administrator: Administrator;

  constructor(private administratorService: AdministratorService, private router: Router) {
    this.administrator = new Administrator();
  }

  ngOnInit(): void {
  }

  loginAdministrator() {
    this.administratorService.loginAdministrator(this.administrator).subscribe(result => this.gotoAdminPage(result))
  }

  gotoAdminPage(result) {
    if (result.validated) {
      this.router.navigate(['/users']);
    } else {
      const navigationExtras: NavigationExtras = {
        state: {
          administrator: result
        }
      };
      this.router.navigate(['/set-password'], navigationExtras);
    }
  }
}
