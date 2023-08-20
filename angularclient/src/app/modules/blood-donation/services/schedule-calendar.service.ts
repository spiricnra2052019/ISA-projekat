import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ScheduleCalendar } from '../model/schedule-calendar';

@Injectable({
  providedIn: 'root'
})
export class ScheduleCalendarService {

  private scheduleCalendarUrl: string;
  private scheduleCalendarBloodCenterUrl: string;
  private scheduleCalendarAppointmentUrl: string;
  private scheduleCalendarUrlUser: string;
  private searchUrl: string;
  private searchSortUrl: string;

  constructor(private http: HttpClient) {
    this.scheduleCalendarUrl = 'http://localhost:8080/schedule-calendar';
    this.scheduleCalendarBloodCenterUrl = 'http://localhost:8080/schedule-calendar/blood-center';
    this.scheduleCalendarAppointmentUrl = 'http://localhost:8080/schedule-calendar/appointment';
    this.scheduleCalendarUrlUser = 'http://localhost:8080/schedule-calendar/user';
    this.searchUrl = 'http://localhost:8080/schedule-calendar/search?scheduleDate=';
    this.searchSortUrl = 'http://localhost:8080/schedule-calendar/search/sort?scheduleDate=';
  }

  public getAllTerminsForBloodCenter(id: number): Observable<ScheduleCalendar[]> {
    return this.http.get<ScheduleCalendar[]>(this.scheduleCalendarBloodCenterUrl + "/" + id);
  }

  public save(scheduleCalendar: ScheduleCalendar) {
    console.log("Schedule Calendar: ", scheduleCalendar);
    return this.http.post<ScheduleCalendar>(this.scheduleCalendarUrl, scheduleCalendar);
  }

  public scheduleAppointment(userAppointment) {
    return this.http.post<ScheduleCalendar>(this.scheduleCalendarAppointmentUrl, userAppointment);
  }

  public getAllTerminsForUser(id): Observable<ScheduleCalendar[]> {
    return this.http.get<ScheduleCalendar[]>(this.scheduleCalendarUrlUser + "/" + id);
  }

  public declineAppointment(id: number) {
    return this.http.put(this.scheduleCalendarUrl + "/decline/" + id, null);
  }

  public searchAppointmentsByDateAndTime(searchAppointment): Observable<ScheduleCalendar[]> {
    return this.http.get<ScheduleCalendar[]>(this.searchUrl.concat(searchAppointment.scheduleDate).concat('&startTime=').concat(searchAppointment.startTime));
  }

  public sortByAndSearch(sortBy, searchAppointment): Observable<ScheduleCalendar[]> {
    return this.http.get<ScheduleCalendar[]>(this.searchSortUrl.concat(searchAppointment.scheduleDate).concat('&startTime=').concat(searchAppointment.startTime).concat('&sortBy=').concat(sortBy));
  }
}
