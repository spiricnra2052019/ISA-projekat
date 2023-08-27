import { Component, OnInit } from '@angular/core';
import { PatientAnswer } from 'src/app/modules/blood-donation/model/patient-answer';
import { ActivatedRoute, Router } from '@angular/router';
import { QueryQuestion } from 'src/app/modules/blood-donation/model/query-question';
import { RegisteredUser } from 'src/app/modules/blood-donation/model/registered-user';
import { QueryService } from '../../blood-donation/services/query.service';
import { RegisteredUserService } from '../../blood-donation/services/registered-user.service';
import { TokenStorageService } from '../../blood-donation/services/token-storage.service';
import { UserToken } from '../../blood-donation/model/user-token';

@Component({
  selector: 'app-query-form',
  templateUrl: './query-form.component.html',
  styleUrls: ['./query-form.component.css']
})
export class QueryFormComponent implements OnInit {
  questions: QueryQuestion[] = [];
  queryAnswer: PatientAnswer;
  userToken: UserToken;


  constructor(private queryService: QueryService, private userService: RegisteredUserService,
    private route: ActivatedRoute,
    private router: Router,
    private tokenStorageService: TokenStorageService) {
    this.userToken = this.tokenStorageService.getUser();
  }

  ngOnInit(): void {
    this.queryService.findAll().subscribe(data => {
      this.questions = data;
    })
    this.questions.forEach((x, i) => x.answer = false);
  }

  addQuery() {
    //   this.questions.forEach((x, i) => {
    //     this.queryAnswer = new PatientAnswer();
    //     this.queryAnswer.userId = parseInt(this.userToken.id);
    //     this.queryAnswer.question = x;
    //     this.queryAnswer.answer = x.answer;
    //     this.queryService.save(this.queryAnswer).subscribe(result => this.gotoUserList());
    //   })
    // }
    const patientAnswers: PatientAnswer[] = [];

    this.questions.forEach(question => {
      const patientAnswer: PatientAnswer = {
        userId: parseInt(this.userToken.id),
        question: question,
        answer: question.answer
      };

      patientAnswers.push(patientAnswer);
    });
    console.log(patientAnswers);
    this.queryService.saveMultiple(patientAnswers).subscribe(results => {
      // Assuming that the saveMultiple method returns a result
      this.gotoUserList();
    });
  }

  gotoUserList() {
    this.router.navigate(['/']);
  }



}
