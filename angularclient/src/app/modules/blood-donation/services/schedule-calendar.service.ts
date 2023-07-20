import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ScheduleCalendar } from '../model/schedule-calendar';

@Injectable({
  providedIn: 'root'
})
export class ScheduleCalendarService {

  private scheduleCalendarUrl: string;

  constructor(private http: HttpClient) {
    this.scheduleCalendarUrl = 'http://localhost:8080/schedule-calendar';
  }

  public getAllTermins(): Observable<ScheduleCalendar[]> {
    return this.http.get<ScheduleCalendar[]>(this.scheduleCalendarUrl);
  }
}
