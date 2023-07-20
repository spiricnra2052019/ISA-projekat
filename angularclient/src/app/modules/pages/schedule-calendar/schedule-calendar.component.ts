import { Component, OnInit } from '@angular/core';
import { ScheduleCalendar } from 'src/app/modules/blood-donation/model/schedule-calendar';
import { ScheduleCalendarService } from '../../blood-donation/services/schedule-calendar.service';

@Component({
  selector: 'app-schedule-calendar',
  templateUrl: './schedule-calendar.component.html',
  styleUrls: ['./schedule-calendar.component.css']
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


  constructor(private scheduleCalendarService: ScheduleCalendarService) {
  }

  schedules: ScheduleCalendar;
  ngOnInit(): void {
  }

  selectedYear(yearSelected) {
    console.log(yearSelected.value);
    this.terminsYearly = [];
    this.scheduleCalendarService.getAllTermins().subscribe(data => {
      for (let i = 0; i < data.length; i++) {
        const splitTemp = data[i].date.split("-");
        const first = splitTemp[0];
        if (first == yearSelected.value) {
          this.terminsYearly.push(data[i]);
        }
      }
    });
  }

  dateSelected() {
    this.termins = [];
    this.scheduleCalendarService.getAllTermins().subscribe(data => {
      this.temp = this.selected.toISOString().substring(0, 10);

      for (let i = 0; i < data.length; i++) {

        const year = this.selected.getFullYear().toString();
        const month = (this.selected.getMonth() + 1).toString();
        const day = this.selected.getDate().toString();

        const splitTemp = data[i].date.split("-");
        const first = splitTemp[0];
        const second = splitTemp[1];
        if (Number(splitTemp[2]) < 10) {
          const third = splitTemp[2].substring(1, 2);
          if (first == year && month == second && day == third) {
            this.termins.push(data[i]);
          }
        } else {
          const third = splitTemp[2];
          if (first == year && month == second && day == third) {
            this.termins.push(data[i]);
          }
        }
      }
    })
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
}
