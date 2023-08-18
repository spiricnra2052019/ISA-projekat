import { Component, OnInit } from '@angular/core';
import { Appeal } from 'src/app/modules/blood-donation/model/appeal';
import { ActivatedRoute, Router } from '@angular/router';
import { AppealService } from '../../blood-donation/services/appeal.service';

@Component({
  selector: 'app-appeal',
  templateUrl: './appeal.component.html',
  styleUrls: ['./appeal.component.css']
})
export class AppealComponent implements OnInit {

  appealData: Appeal;
  username: string;
  subject: string;
  text: string;

  constructor(private appealService: AppealService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void { }

  getUsername(username) {
    this.username = username.value;
  }

  getSubject(subject) {
    this.subject = subject.value;
  }

  getText(text) {
    this.text = text.value;
  }

  SendAppeal() {
    this.appealData = new Appeal();
    this.appealData.toUsername = this.username;
    this.appealData.subject = this.subject;
    this.appealData.text = this.text;
    this.appealService.sendAppeal(this.appealData).subscribe(result => this.gotoUserList())
  }

  gotoUserList() {
    this.router.navigate(['/response-appeal']);
  }
}
