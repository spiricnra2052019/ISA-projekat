import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { RegisteredUser } from './registered-user';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegisteredUserService {
  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = 'http://localhost:8080/users';
  }

  public findAll(): Observable<RegisteredUser[]> {
    return this.http.get<RegisteredUser[]>(this.usersUrl);
  }

  public save(user: RegisteredUser) {
    return this.http.post<RegisteredUser>(this.usersUrl, user);
  }

}

