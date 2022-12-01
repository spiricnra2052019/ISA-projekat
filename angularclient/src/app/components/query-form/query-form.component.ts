import { Component, OnInit } from '@angular/core';
import { PatientAnswer } from 'src/app/model/patient-answer';
import { QueryService } from 'src/app/services/query.service';
import { ActivatedRoute, Router } from '@angular/router';
import { QueryQuestion } from 'src/app/model/query-question';
import { RegisteredUserService } from 'src/app/services/registered-user.service';
import { RegisteredUser } from 'src/app/model/registered-user';

@Component({
  selector: 'app-query-form',
  templateUrl: './query-form.component.html',
  styleUrls: ['./query-form.component.css']
})
export class QueryFormComponent implements OnInit {
  questions: QueryQuestion[] = [];
  queryAnswer: PatientAnswer;
  patient: RegisteredUser;


  constructor(private queryService: QueryService, private userService: RegisteredUserService,
    private route: ActivatedRoute,
    private router: Router,) { }

  ngOnInit(): void {
    this.queryService.findAll().subscribe(data => {
      this.questions = data;
      this.userService.findById(3).subscribe((response) => this.patient = response);
    })
    this.questions.forEach((x, i) => x.answer = false);
  }

  addQuery() {
    this.questions.forEach((x, i) => {
      this.queryAnswer = new PatientAnswer();
      this.queryAnswer.registeredUser = this.patient;
      this.queryAnswer.patientQuestion = x;
      this.queryAnswer.answer = x.answer;
      this.queryService.save(this.queryAnswer).subscribe(result => this.gotoUserList());
    })
  }

  gotoUserList() {
    this.router.navigate(['/users']);
  }



}
