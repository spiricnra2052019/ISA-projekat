import { Component, OnInit } from '@angular/core';
import { ScheduleCalendar } from '../../blood-donation/model/schedule-calendar';
import { ActivatedRoute, Router } from '@angular/router';
import { ScheduleCalendarService } from '../../blood-donation/services/schedule-calendar.service';
import { UserToken } from '../../blood-donation/model/user-token';
import { TokenStorageService } from '../../blood-donation/services/token-storage.service';
import { BloodCenterService } from '../../blood-donation/services/blood-center.service';
import { BloodCenter } from '../../blood-donation/model/blood-center';

@Component({
  selector: 'app-schedule-appointment',
  templateUrl: './schedule-appointment.component.html',
  styleUrls: ['./schedule-appointment.component.css']
})
export class ScheduleAppointmentComponent {
  appointment: ScheduleCalendar;
  user: UserToken;
  bloodCenter: BloodCenter;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private scheduleCalendarService: ScheduleCalendarService,
    private tokenStorageService: TokenStorageService,
    private bloodCenterService: BloodCenterService) {
    this.user = this.tokenStorageService.getUser();
    this.appointment = new ScheduleCalendar();
    this.bloodCenterService.findBloodCenterIdByAdministratorId(this.user.id).subscribe(data => {
      this.bloodCenter = data;
      console.log("Blood center: ", this.bloodCenter);
    }
    );
  }


  scheduleAppointment() {
    this.appointment.bloodCenterId = this.bloodCenter.id;
    console.log("Appointment: ", this.appointment);
    if (this.isValidTime(this.appointment.startTime)) {
      this.scheduleCalendarService.save(this.appointment).subscribe(result => this.gotoAppointmentList());
    } else {
      alert("Invalid time!");
    }
  }

  isValidTime(time: string): boolean {
    const scheduledTime = this.convertToTime(time);
    const startTime = this.convertToTime(this.bloodCenter.workingTime.openingTime);
    const endTime = this.convertToTime(this.bloodCenter.workingTime.closingTime);
    return scheduledTime >= startTime && scheduledTime <= endTime;
  }

  gotoAppointmentList() {
    this.router.navigate(['/schedule-calendar']);
  }

  convertToTime(timeString: string): Date {
    const [hours, minutes] = timeString.split(':');
    const date = new Date();
    date.setHours(parseInt(hours, 10));
    date.setMinutes(parseInt(minutes, 10));
    return date;
  }
}
