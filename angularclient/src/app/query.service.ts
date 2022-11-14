import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PatientQuery } from './patient-query';

@Injectable({
  providedIn: 'root'
})
export class QueryService {
  private patientQueryUrl: string;

  constructor(private http: HttpClient) {
    this.patientQueryUrl = 'http://localhost:8080/query'
  }

  public findAll(): Observable<PatientQuery[]> {
    return this.http.get<PatientQuery[]>(this.patientQueryUrl);
  }

}
