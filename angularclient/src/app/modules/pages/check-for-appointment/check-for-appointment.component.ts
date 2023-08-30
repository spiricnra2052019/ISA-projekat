import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { TokenStorageService } from '../../blood-donation/services/token-storage.service';
import { UserToken } from '../../blood-donation/model/user-token';
import { RegisteredUserService } from '../../blood-donation/services/registered-user.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-check-for-appointment',
  templateUrl: './check-for-appointment.component.html',
  styleUrls: ['./check-for-appointment.component.css'],
})
export class CheckForAppointmentComponent implements OnInit {
  option1: boolean = false;
  option2: boolean = false;
  option3: boolean = false;
  option4: boolean = false;
  option5: boolean = false;
  option6: boolean = false;
  option7: boolean = false;
  option8: boolean = false;
  user: UserToken;
  patientId: number;
  scheduleId: number;
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private userService: RegisteredUserService,
    private tokenStorageService: TokenStorageService
  ) {}

  ngOnInit(): void {
    this.user = this.tokenStorageService.getUser();
    this.route.params.subscribe((params) => {
      this.patientId = params['patientId'];
      this.scheduleId = params['scheduleId'];
      console.log(this.patientId);
      console.log(this.scheduleId);
    });
    console.log(this.user);
  }

  didntCome() {
    this.userService.addPenalty(this.patientId).subscribe(
      (response: any) => {
        alert('Penalty successfully added to desired patient.');
      },
      (error: HttpErrorResponse) => {
        alert(error);
      }
    );
    this.router.navigate(['']);
  }

  startAppointment() {
    if (
      this.option1 == false &&
      this.option2 == false &&
      this.option3 == false &&
      this.option4 == false &&
      this.option5 == false &&
      this.option6 == false &&
      this.option7 == false &&
      this.option8 == false
    )
      this.router.navigate(['appointment', this.patientId, this.scheduleId]);
    else {
      alert('pacijent ne ispunjava uslove za pregled!');
      this.router.navigate(['']);
    }
  }
}
