import { Component, OnInit } from '@angular/core';
import { ScheduleCalendar } from '../../blood-donation/model/schedule-calendar';
import { UserToken } from '../../blood-donation/model/user-token';
import { ActivatedRoute, Router } from '@angular/router';
import { ScheduleCalendarService } from '../../blood-donation/services/schedule-calendar.service';
import { TokenStorageService } from '../../blood-donation/services/token-storage.service';
import { Time } from '@angular/common';
import { SearchAppointmentDTO } from '../../blood-donation/dto/search-appointment';
import { UserAppointmentDTO } from '../../blood-donation/dto/user-appointment';
import { RegisteredUserService } from '../../blood-donation/services/registered-user.service';
import { QueryService } from '../../blood-donation/services/query.service';

@Component({
  selector: 'app-search-schedule-appointment',
  templateUrl: './search-schedule-appointment.component.html',
  styleUrls: ['./search-schedule-appointment.component.css']
})
export class SearchScheduleAppointmentComponent implements OnInit {
  searchAppointment: SearchAppointmentDTO = new SearchAppointmentDTO();
  appointments: ScheduleCalendar[] = [];
  user: UserToken;
  selectedColumn: string;

  columns = [
    { id: 1, name: "Average Rate", value: "averageRate" }
  ]

  constructor(private route: ActivatedRoute, private scheduleCalendarService: ScheduleCalendarService, private tokenStorageService: TokenStorageService,
    private queryService: QueryService,
    private router: Router) {
    this.user = this.tokenStorageService.getUser();

  }

  ngOnInit(): void {
  }

  onChange(value) {
    this.selectedColumn = value;
    this.scheduleCalendarService.sortByAndSearch(value, this.searchAppointment).subscribe(data => {
      this.appointments = data;
    });
  }

  searchAppointments(searchAppointment) {
    this.searchAppointment = searchAppointment;
    if (this.selectedColumn != null) {
      this.scheduleCalendarService.sortByAndSearch(this.selectedColumn, this.searchAppointment).subscribe(data => {
        this.appointments = data;
        this.appointments = this.appointments.filter(appointment => new Date(appointment.scheduleDate) >= new Date() && appointment.user == null);
      });
    } else {
      this.scheduleCalendarService.searchAppointmentsByDateAndTime(searchAppointment).subscribe(data => {
        this.appointments = data;
        this.appointments = this.appointments.filter(appointment => new Date(appointment.scheduleDate) >= new Date() && appointment.user == null);
      });
    }
  }

  scheduleAppointment(id: string) {
    const userAppointment = new UserAppointmentDTO();
    userAppointment.appointmentId = parseInt(id);
    userAppointment.user = this.user;
    this.queryService.check(this.user.id).subscribe(data => {
      if (data == true) {
        this.scheduleCalendarService.scheduleAppointment(userAppointment).subscribe(
          data => {
            alert("Appointment scheduled!");
            this.router.navigate(['/user-appointments'])
          },
          error => {
            alert("Appointment not scheduled!");
          }
        );
      } else {
        alert("You have to have query to schedule appointment!");
        this.router.navigate(['/query']);
      }
    });
  }

}
