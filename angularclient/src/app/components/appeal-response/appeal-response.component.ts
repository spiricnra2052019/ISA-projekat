import { Component, OnInit } from '@angular/core';
import { Appeal } from 'src/app/model/appeal';
import { AppealService } from 'src/app/services/appeal.service';

@Component({
  selector: 'app-appeal-response',
  templateUrl: './appeal-response.component.html',
  styleUrls: ['./appeal-response.component.css']
})
export class AppealResponseComponent implements OnInit {

  showAppeal = false;
  getUsername: string;
  getSubject: string;
  getText: string;
  appealData: Appeal[] = [];
  constructor(private appealService: AppealService) { }

  ngOnInit(): void {
    this.appealService.getAllAppeals().subscribe(data => {
      this.appealData = data;
      console.log(this.appealData);
    })
  }

  appealSelected(toUsername, subject, text) {
    this.showAppeal = true;
    this.getUsername = toUsername;
    this.getSubject = subject;
    this.getText = text;
  }

}
