import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ScheduleCalendar } from '../model/schedule-calendar';
import { BloodCenter } from '../model/blood-center';

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
  freeBloodCenterUrlSort: string;
  freeBloodCenterUrl: string;
  scheduleCalendarUserUrl: string;

  constructor(private http: HttpClient) {
    this.scheduleCalendarUrl = 'http://localhost:8080/schedule-calendar';
    this.scheduleCalendarBloodCenterUrl = 'http://localhost:8080/schedule-calendar/blood-center';
    this.scheduleCalendarAppointmentUrl = 'http://localhost:8080/schedule-calendar/appointment';
    this.scheduleCalendarUserUrl = 'http://localhost:8080/schedule-calendar/appointment/user';
    this.scheduleCalendarUrlUser = 'http://localhost:8080/schedule-calendar/user';
    this.searchUrl = 'http://localhost:8080/schedule-calendar/search?scheduleDate=';
    this.searchSortUrl = 'http://localhost:8080/schedule-calendar/search/sort?scheduleDate=';
    this.freeBloodCenterUrl = 'http://localhost:8080/schedule-calendar/free?scheduleDate=';
    this.freeBloodCenterUrlSort = 'http://localhost:8080/schedule-calendar/free/sort?scheduleDate=';
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

  public getFreeBloodCentersByDateAndTime(searchAppointment): Observable<BloodCenter[]> {
    return this.http.get<BloodCenter[]>(this.freeBloodCenterUrl.concat(searchAppointment.scheduleDate).concat('&startTime=').concat(searchAppointment.startTime).concat('&duration=').concat(searchAppointment.duration));
  }

  public sortByAndSearchFreeBloodCenters(searchAppointment): Observable<BloodCenter[]> {
    return this.http.get<BloodCenter[]>(this.freeBloodCenterUrlSort.concat(searchAppointment.scheduleDate).concat('&startTime=').concat(searchAppointment.startTime).concat('&duration=').concat(searchAppointment.duration));
  }

  public userSchedule(userScheduleAppointmentDTO) {
    return this.http.post(this.scheduleCalendarUserUrl, userScheduleAppointmentDTO);
  }
}
