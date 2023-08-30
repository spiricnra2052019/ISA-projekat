import { ObserversModule } from '@angular/cdk/observers';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppointmentDTO } from '../dto/appointmentDTO';

@Injectable({
  providedIn: 'root',
})
export class VisitHistoryService {
  private visitHistoryUrl;
  constructor(private http: HttpClient) {
    this.visitHistoryUrl = 'http://localhost:8080/employee-report';
  }

  public add(appointment): Observable<any> {
    return this.http.post<AppointmentDTO>(
      this.visitHistoryUrl + '/add-report',
      appointment
    );
  }
}
