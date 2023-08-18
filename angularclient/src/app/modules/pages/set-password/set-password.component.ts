import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Administrator } from 'src/app/modules/blood-donation/model/administrator';
import { AdministratorService } from '../../blood-donation/services/administrator.service';

@Component({
  selector: 'app-set-password',
  templateUrl: './set-password.component.html',
  styleUrls: ['./set-password.component.css']
})
export class SetPasswordComponent implements OnInit {

  administrator: Administrator;

  constructor(private administratorService: AdministratorService, private router: Router) {
    const navigation = this.router.getCurrentNavigation();
    const state = navigation.extras.state as {
      administrator: Administrator
    };
    this.administrator = state.administrator;
  }

  ngOnInit(): void {
  }

  setPassword(): void {
    this.administrator.validated = true;
    this.administratorService.editAdministrator(this.administrator).subscribe(result => this.gotoUserList())
  }

  gotoUserList() {
    this.router.navigate(['/users']);
  }

}
