import { Component, OnInit } from '@angular/core';
import { ScheduleCalendar } from 'src/app/model/schedule-calendar';
import { ScheduleCalendarService } from 'src/app/services/schedule-calendar.service';

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


  constructor(private scheduleCalendarService: ScheduleCalendarService) {
  }

  schedules: ScheduleCalendar;
  ngOnInit(): void {
  }

  dateSelected() {
    this.scheduleCalendarService.getAllTermins().subscribe(data => {
      this.temp = this.selected.toISOString().substring(0, 10);

      for (let i = 0; i < data.length; i++) {
        const year = this.selected.getFullYear().toString();
        const month = (this.selected.getMonth() + 1).toString();
        const day = this.selected.getDate().toString();

        const splitTemp = data[i].date.split("-");
        const first = splitTemp[0];
        const second = splitTemp[1];
        const third = splitTemp[2].substring(1, 2);

        if (first == year && month == second && day == third) {
          this.termins.push(data[i]);
        }
      }
    })
  }
}
