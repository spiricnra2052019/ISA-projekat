import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ScheduleCalendar } from '../../blood-donation/model/schedule-calendar';
import { ScheduleCalendarService } from '../../blood-donation/services/schedule-calendar.service';
import { UserToken } from '../../blood-donation/model/user-token';
import { TokenStorageService } from '../../blood-donation/services/token-storage.service';
import { RegisteredUser } from '../../blood-donation/model/registered-user';
import { UserAppointmentDTO } from '../../blood-donation/dto/user-appointment';

@Component({
  selector: 'app-user-schedule-appointment',
  templateUrl: './user-schedule-appointment.component.html',
  styleUrls: ['./user-schedule-appointment.component.css']
})
export class UserScheduleAppointmentComponent implements OnInit {
  id: number;
  appointments: ScheduleCalendar[] = [];
  user: UserToken;

  constructor(private route: ActivatedRoute, private scheduleCalendarService: ScheduleCalendarService, private tokenStorageService: TokenStorageService) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.id = +params.get('id');
      this.scheduleCalendarService.getAllTerminsForBloodCenter(this.id).subscribe(data => {
        this.appointments = data;
        // filter appointments that are in the future (not in the past)
        this.appointments = this.appointments.filter(appointment => new Date(appointment.scheduleDate) >= new Date() && appointment.user == null);
      });
    });
    this.user = this.tokenStorageService.getUser();
  }

  scheduleAppointment(id: string) {
    const userAppointment = new UserAppointmentDTO();
    userAppointment.appointmentId = parseInt(id);
    userAppointment.user = this.user;
    console.log("User appointment: ", userAppointment);
    this.scheduleCalendarService.scheduleAppointment(userAppointment).subscribe(
      data => {
        alert("Appointment scheduled!");
        this.ngOnInit();
      },
      error => {
        alert("Appointment not scheduled!");
      }
    );

  }
}
