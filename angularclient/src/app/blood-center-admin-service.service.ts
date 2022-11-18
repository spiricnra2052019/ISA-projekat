import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BloodCenterAdmin } from './blood-center-admin/blood-center-admin.component';

@Injectable({
  providedIn: 'root'
})
export class BloodCenterAdminService {

  private bloodCenterAdminsUrl: string;

  constructor(private http: HttpClient) {
    this.bloodCenterAdminsUrl = 'http://localhost:8080/blood-center-administrators';
  }

  public findAll(): Observable<BloodCenterAdmin[]> {
    return this.http.get<BloodCenterAdmin[]>(this.bloodCenterAdminsUrl);
  }

  public save(center: BloodCenterAdmin) {
    return this.http.post<BloodCenterAdmin>(this.bloodCenterAdminsUrl, center);
  }
}
