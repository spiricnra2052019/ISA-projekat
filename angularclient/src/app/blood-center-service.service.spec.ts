import { TestBed } from '@angular/core/testing';

import { BloodCenterServiceService } from './blood-center-service.service';

describe('BloodCenterServiceService', () => {
  let service: BloodCenterServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BloodCenterServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
