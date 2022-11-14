import { TestBed } from '@angular/core/testing';

import { BloodCenterService } from './blood-center.service';

describe('BloodCenterService', () => {
  let service: BloodCenterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BloodCenterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
