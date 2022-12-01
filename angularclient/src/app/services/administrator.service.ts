import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Administrator } from '../model/administrator';

@Injectable({
  providedIn: 'root'
})
export class AdministratorService {

  private AdminsUrl: string;

  constructor(private http: HttpClient) {
    this.AdminsUrl = 'http://localhost:8080/administrators';
  }

  public getAll(): Observable<Administrator[]> {
    return this.http.get<Administrator[]>(this.AdminsUrl);
  }

  public addAdministrator(admin: Administrator) {
    return this.http.post<Administrator>(this.AdminsUrl, admin);
  }
}
