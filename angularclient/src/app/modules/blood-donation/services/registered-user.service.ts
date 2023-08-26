import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { RegisteredUser } from '../model/registered-user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UserToken } from '../model/user-token';
import { Administrator } from '../model/administrator';
import { BloodCenterAdministrator } from '../model/blod-center-administrator';



@Injectable({
  providedIn: 'root'
})
export class RegisteredUserService {
  private usersUrl: string;
  private searchUrl: string;
  private apiHost = 'http://localhost:8080/';
  private authRegisterUrl = this.apiHost + 'api/v1/auth/register';
  private adminUrl = this.apiHost + 'administrators';
  private bloodAdminUrl = this.apiHost + 'blood-center-administrators';

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/users';
    this.searchUrl = 'http://localhost:8080/users/search?query=';
  }

  public findAll(): Observable<RegisteredUser[]> {
    return this.http.get<RegisteredUser[]>(this.usersUrl);
  }

  public save(user: RegisteredUser) {
    return this.http.post<RegisteredUser>(this.authRegisterUrl, user);
  }

  public findAllAndSearch(searchProperty): Observable<RegisteredUser[]> {
    return this.http.get<RegisteredUser[]>(this.searchUrl.concat(searchProperty));
  }

  public findPatientById(id) {
    return this.http.get<RegisteredUser>(this.usersUrl + "/" + id);
  }

  public findAdminById(id) {
    return this.http.get<Administrator>(this.adminUrl + "/" + id);
  }

  public findBloodAdminById(id) {
    return this.http.get<BloodCenterAdministrator>(this.bloodAdminUrl + "/" + id);
  }

  public update(user) {
    console.log(user);
    return this.http.put<RegisteredUser>(this.usersUrl, user);
  }

  public authenticate(loginRequest: any): Observable<any> {
    return this.http.post<any>(this.apiHost + 'api/v1/auth/authenticate', loginRequest);
  }

  public getPenaltyCount(id) {
    return this.http.get<number>(this.usersUrl + '/penalty/' + id);
  }
}

