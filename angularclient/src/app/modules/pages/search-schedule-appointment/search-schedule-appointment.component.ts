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
import { BloodCenter } from '../../blood-donation/model/blood-center';
import { UserScheduleAppointmentDTO } from '../../blood-donation/dto/user-schedule-appointment';

@Component({
  selector: 'app-search-schedule-appointment',
  templateUrl: './search-schedule-appointment.component.html',
  styleUrls: ['./search-schedule-appointment.component.css']
})
export class SearchScheduleAppointmentComponent implements OnInit {
  searchAppointment: SearchAppointmentDTO = new SearchAppointmentDTO();
  bloodCenters: BloodCenter[] = [];
  user: UserToken;
  selectedColumn: string;
  userScheduleAppointmentDTO: UserScheduleAppointmentDTO = new UserScheduleAppointmentDTO();

  columns = [
    { id: 1, name: "Average Rate", value: "averageRate" }
  ]

  constructor(private route: ActivatedRoute, private scheduleCalendarService: ScheduleCalendarService, private tokenStorageService: TokenStorageService,
    private queryService: QueryService,
    private router: Router,
    private registeredUserService: RegisteredUserService) {
    this.user = this.tokenStorageService.getUser();

  }

  ngOnInit(): void {
  }

  onChange(value) {
    this.selectedColumn = value;
    this.scheduleCalendarService.sortByAndSearchFreeBloodCenters(this.searchAppointment).subscribe(data => {
      this.bloodCenters = data;
    });
  }

  searchAppointments(searchAppointment) {
    this.searchAppointment = searchAppointment;
    if (!this.isValidDate(this.searchAppointment.scheduleDate.toString())) {
      alert("Invalid date!");
      return;
    }
    if (this.selectedColumn != null) {
      this.scheduleCalendarService.sortByAndSearchFreeBloodCenters(this.searchAppointment).subscribe(data => {
        this.bloodCenters = data;
      });
    } else {
      this.scheduleCalendarService.getFreeBloodCentersByDateAndTime(searchAppointment).subscribe(data => {
        this.bloodCenters = data;
        console.log(this.bloodCenters);
      });
    }
  }

  isValidDate(scheduleDate: string): boolean {
    const scheduledDate = new Date(scheduleDate);
    const today = new Date();
    if (scheduledDate < today) {
      return false;
    }
    return true;
  }

  scheduleAppointment(id: number) {
    // check number of penalty points
    this.registeredUserService.getPenaltyCount(this.user.id).subscribe(
      data => {
        if (data > 2) {
          alert("You have more than 2 penalty points. You can't schedule appointment!");
        } else {
          this.userScheduleAppointmentDTO.bloodCenterId = id;
          this.userScheduleAppointmentDTO.userId = parseInt(this.user.id);
          this.userScheduleAppointmentDTO.scheduleDate = this.searchAppointment.scheduleDate;
          this.userScheduleAppointmentDTO.startTime = this.searchAppointment.startTime;
          this.userScheduleAppointmentDTO.duration = this.searchAppointment.duration;

          this.scheduleCalendarService.userSchedule(this.userScheduleAppointmentDTO).subscribe(
            data => {
              alert("Appointment scheduled!");
              this.router.navigate(['/user-appointments'])
            },
            error => {
              this.router.navigate(['query'])
            }
          );
        }
      },);
  }

}
