import { TestBed } from '@angular/core/testing';

import { BloodCenterAdminService } from './blood-center-admin-service.service';

describe('BloodCenterAdminServiceService', () => {
  let service: BloodCenterAdminService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BloodCenterAdminService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
