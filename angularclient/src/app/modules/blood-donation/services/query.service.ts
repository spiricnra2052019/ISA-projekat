import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PatientAnswer } from '../model/patient-answer';
import { QueryQuestion } from '../model/query-question';

@Injectable({
  providedIn: 'root'
})
export class QueryService {
  private queryQuestionsUrl: string;
  private queryAnswersUrl: string;

  constructor(private http: HttpClient) {
    this.queryQuestionsUrl = 'http://localhost:8080/query'
    this.queryAnswersUrl = 'http://localhost:8080/query-answer'
  }

  public findAll(): Observable<QueryQuestion[]> {
    return this.http.get<QueryQuestion[]>(this.queryQuestionsUrl);
  }

  public save(queryAnswer: PatientAnswer) {
    return this.http.post<PatientAnswer>(this.queryAnswersUrl, queryAnswer);
  }

  public check(userId): Observable<any> {
    return this.http.get<any>(this.queryAnswersUrl + "/check/" + userId);
  }

  public saveMultiple(patientAnswers: PatientAnswer[]) {
    return this.http.post<PatientAnswer[]>(this.queryAnswersUrl + "/multiple", patientAnswers);
  }
}
