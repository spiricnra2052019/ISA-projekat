import { Component, OnInit } from '@angular/core';
import { AppointmentDTO } from '../../blood-donation/dto/appointmentDTO';
import { ActivatedRoute, Router } from '@angular/router';
import { VisitHistoryService } from '../../blood-donation/services/visit-history.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.css'],
})
export class AppointmentComponent implements OnInit {
  appointment: AppointmentDTO;
  patientId: number;
  scheduleId: number;
  constructor(
    private visitHistoryService: VisitHistoryService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.appointment = new AppointmentDTO();
    this.route.params.subscribe((params) => {
      this.patientId = params['patientId'];
      this.scheduleId = params['scheduleId'];
    });
    this.appointment.scheduleCalendarId = this.scheduleId;
    this.appointment.patientId = this.patientId;
  }
  finish() {
    console.log(this.appointment);
    this.visitHistoryService.add(this.appointment).subscribe(
      (response: any) => {},
      (error: HttpErrorResponse) => {
        alert(error);
      }
    );
    this.router.navigate(['']);
  }
}
