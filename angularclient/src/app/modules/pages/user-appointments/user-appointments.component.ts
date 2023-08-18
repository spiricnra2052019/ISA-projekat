import { Component, OnInit } from '@angular/core';
import { ScheduleCalendar } from '../../blood-donation/model/schedule-calendar';
import { UserToken } from '../../blood-donation/model/user-token';
import { ActivatedRoute } from '@angular/router';
import { ScheduleCalendarService } from '../../blood-donation/services/schedule-calendar.service';
import { TokenStorageService } from '../../blood-donation/services/token-storage.service';

@Component({
  selector: 'app-user-appointments',
  templateUrl: './user-appointments.component.html',
  styleUrls: ['./user-appointments.component.css']
})
export class UserAppointmentsComponent implements OnInit {
  appointments: ScheduleCalendar[] = [];
  user: UserToken;

  constructor(private route: ActivatedRoute, private scheduleCalendarService: ScheduleCalendarService, private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.user = this.tokenStorageService.getUser();
    this.scheduleCalendarService.getAllTerminsForUser(this.user.id).subscribe(data => {
      this.appointments = data;
      this.appointments = this.appointments.filter(appointment => new Date(appointment.scheduleDate) >= new Date());
    });
  }

  isEligibleForDecline(appointment: ScheduleCalendar): boolean {
    const now = new Date();
    const twentyFourHoursLater = new Date(now.getTime() + 24 * 60 * 60 * 1000);

    // Combine scheduleDate and startTime to create a single Date object for comparison
    const combinedDateTime = new Date(appointment.scheduleDate + ' ' + appointment.startTime);

    return combinedDateTime > twentyFourHoursLater;
  }

  declineAppointment(appointment: any) {
    this.scheduleCalendarService.declineAppointment(appointment.id).subscribe(
      data => {
        alert("Appointment declined!");
        this.ngOnInit();
      },
      error => {
        alert("Appointment not declined!");
      }
    );
  }
}
