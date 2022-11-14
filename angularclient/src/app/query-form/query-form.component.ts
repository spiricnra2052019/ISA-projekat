import { Component, OnInit } from '@angular/core';
import { PatientQuery } from '../patient-query';
import { QueryService } from '../query.service';

@Component({
  selector: 'app-query-form',
  templateUrl: './query-form.component.html',
  styleUrls: ['./query-form.component.css']
})
export class QueryFormComponent implements OnInit {
  queries: PatientQuery[] = [];


  constructor(private queryService: QueryService) { }

  ngOnInit(): void {
    this.queryService.findAll().subscribe(data => {
      this.queries = data;
    })
  }

}
