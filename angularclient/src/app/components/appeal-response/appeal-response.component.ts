import { Component, OnInit } from '@angular/core';
import { Appeal } from 'src/app/model/appeal';
import { AppealService } from 'src/app/services/appeal.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-appeal-response',
  templateUrl: './appeal-response.component.html',
  styleUrls: ['./appeal-response.component.css']
})
export class AppealResponseComponent implements OnInit {

  showAppeal = false;
  appeal: Appeal;
  getUsername: string;
  getSubject: string;
  getText: string;
  responseText: string;
  appealData: Appeal[] = [];

  constructor(private appealService: AppealService, private router: Router) { }

  ngOnInit(): void {
    this.appealService.getAllAppeals().subscribe(data => {
      this.appealData = data;
    })
  }

  appealSelected(toUsername, subject, text) {
    this.showAppeal = true;
    this.getUsername = toUsername;
    this.getSubject = subject;
    this.getText = text;
  }

  responseTextFunction(response) {
    this.responseText = response.value;
  }

  sendResponse() {
    this.appeal = new Appeal();
    this.appeal.toUsername = this.getUsername;
    this.appeal.subject = this.getSubject;
    this.appeal.text = this.responseText;
    console.log(this.appeal);
    this.appealService.sendAppealResponse(this.appeal).subscribe(result => this.gotoUserList());
  }

  gotoUserList() {
    this.router.navigate(['/send-appeal']);
  }
}
