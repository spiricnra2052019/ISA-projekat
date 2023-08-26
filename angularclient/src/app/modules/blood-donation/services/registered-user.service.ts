import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { RegisteredUser } from '../model/registered-user';
import { HttpClient, HttpHeaders } from '@angular/common/http';



@Injectable({
  providedIn: 'root'
})
export class RegisteredUserService {
  private usersUrl: string;
  private searchUrl: string;
  private apiHost = 'http://localhost:8080/';

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/users';
    this.searchUrl = 'http://localhost:8080/users/search?query=';
  }

  public findAll(): Observable<RegisteredUser[]> {
    return this.http.get<RegisteredUser[]>(this.usersUrl);
  }

  public save(user: RegisteredUser) {
    return this.http.post<RegisteredUser>(this.usersUrl, user);
  }

  public findAllAndSearch(searchProperty): Observable<RegisteredUser[]> {
    return this.http.get<RegisteredUser[]>(this.searchUrl.concat(searchProperty));
  }

  public findById(id) {
    return this.http.get<RegisteredUser>(this.usersUrl + "/" + id);
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

