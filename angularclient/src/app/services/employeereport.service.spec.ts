import { TestBed } from '@angular/core/testing';

import { EmployeereportService } from './employeereport.service';

describe('EmployeereportService', () => {
  let service: EmployeereportService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmployeereportService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
