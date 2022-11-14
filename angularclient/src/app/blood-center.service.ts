import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BloodCenter } from './blood-center';

@Injectable({
  providedIn: 'root'
})
export class BloodCenterService {
  private bloodCentersUrl: string;

  constructor(private http: HttpClient) {
    this.bloodCentersUrl = 'http://localhost:8080/blood-centers';
  }

  public findAll(): Observable<BloodCenter[]> {
    return this.http.get<BloodCenter[]>(this.bloodCentersUrl);
  }

}
