import { Component, OnInit } from '@angular/core';
import { ScheduleCalendar } from 'src/app/modules/blood-donation/model/schedule-calendar';
import { ScheduleCalendarService } from '../../blood-donation/services/schedule-calendar.service';
import { UserToken } from '../../blood-donation/model/user-token';
import { TokenStorageService } from '../../blood-donation/services/token-storage.service';
import { BloodCenter } from '../../blood-donation/model/blood-center';
import { BloodCenterService } from '../../blood-donation/services/blood-center.service';
import { Router } from '@angular/router';
import { RegisteredUser } from '../../blood-donation/model/registered-user';

@Component({
  selector: 'app-schedule-calendar',
  templateUrl: './schedule-calendar.component.html',
  styleUrls: ['./schedule-calendar.component.css'],
})
export class ScheduleCalendarComponent implements OnInit {
  selected: Date | null;
  temp: string;
  date: string;
  termins: ScheduleCalendar[] = [];
  terminsYearly: ScheduleCalendar[] = [];
  weekly = false;
  monthly = false;
  yearly = false;
  user: UserToken;
  bloodCenter: BloodCenter;

  constructor(
    private scheduleCalendarService: ScheduleCalendarService,
    private tokenStorageService: TokenStorageService,
    private bloodCenterService: BloodCenterService,
    private router: Router
  ) {
    this.user = this.tokenStorageService.getUser();
    this.bloodCenterService
      .findBloodCenterIdByAdministratorId(this.user.id)
      .subscribe((data) => {
        this.bloodCenter = data;
      });
  }

  schedules: ScheduleCalendar;
  ngOnInit(): void {}

  selectedYear(yearSelected) {
    console.log(yearSelected.value);
    this.terminsYearly = [];
    this.scheduleCalendarService
      .getAllTerminsForBloodCenter(this.bloodCenter.id)
      .subscribe((data) => {
        console.log(data);
        for (let i = 0; i < data.length; i++) {
          const splitTemp = data[i].scheduleDate.split('-');
          const first = splitTemp[0];
          if (first == yearSelected.value) {
            if (data[i].user == null) {
              data[i].user = new RegisteredUser();
            }
            this.terminsYearly.push(data[i]);
          }
        }
      });
  }

  dateSelected() {
    this.termins = [];
    this.scheduleCalendarService
      .getAllTerminsForBloodCenter(this.bloodCenter.id)
      .subscribe((data) => {
        this.temp = this.selected.toISOString().substring(0, 10);
        for (let i = 0; i < data.length; i++) {
          const year = this.selected.getFullYear().toString();
          const month = (this.selected.getMonth() + 1).toString();
          const day = this.selected.getDate().toString();
          const splitTemp = data[i].scheduleDate.split('-');
          const first = splitTemp[0];
          let second = splitTemp[1];
          if (data[i].user == null) {
            data[i].user = new RegisteredUser();
          }

          if (Number(splitTemp[1]) < 10) {
            second = splitTemp[1].substring(1, 2);
          }

          if (Number(splitTemp[2]) < 10) {
            const third = splitTemp[2].substring(1, 2);
            if (first == year && month == second && day == third) {
              this.termins.push(data[i]);
            }
          } else {
            const third = splitTemp[2];
            if (first == year && month == second && day == third) {
              console.log('Nasao');
              this.termins.push(data[i]);
            }
          }
        }
      });
  }

  weeklyCalendar() {
    this.weekly = true;
    this.monthly = false;
    this.yearly = false;
  }

  monthlyCalendar() {
    this.weekly = false;
    this.monthly = true;
    this.yearly = false;
  }

  yearlyCalendar() {
    this.weekly = false;
    this.monthly = false;
    this.yearly = true;
  }
  startAppointment(patinetId, scheduleId) {
    if (patinetId != '')
      this.router.navigate(['check-for-appointment', patinetId, scheduleId]);
  }
}
