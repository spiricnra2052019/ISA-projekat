import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { BloodCenterAdministrator } from '../components/blood-center-admin/blood-center-admin.component';

@Injectable({
  providedIn: 'root'
})
export class BloodCenterAdminService {

  private bloodCenterAdminsUrl: string;

  constructor(private http: HttpClient) {
    this.bloodCenterAdminsUrl = 'http://localhost:8080/blood-center-administrators';
  }

  public findAll(): Observable<BloodCenterAdministrator[]> {
    return this.http.get<BloodCenterAdministrator[]>(this.bloodCenterAdminsUrl);
  }

  public save(center: BloodCenterAdministrator) {
    return this.http.post<BloodCenterAdministrator>(this.bloodCenterAdminsUrl, center);
  }
}
