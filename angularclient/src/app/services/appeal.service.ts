import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Appeal } from '../model/appeal';

@Injectable({
  providedIn: 'root'
})
export class AppealService {

  private sendAppealUrl: string;

  constructor(private http: HttpClient) {
    this.sendAppealUrl = 'http://localhost:8080/send-appeal'
  }

  public getAllAppeals(): Observable<Appeal[]> {
    return this.http.get<Appeal[]>(this.sendAppealUrl);
  }

  public sendAppeal(appeal: Appeal): Observable<Appeal> {
    return this.http.post<Appeal>(this.sendAppealUrl, appeal);
  }

  public sendAppealResponse(appeal: Appeal): Observable<Appeal> {
    return this.http.post<Appeal>(this.sendAppealUrl, appeal);
  }
}
