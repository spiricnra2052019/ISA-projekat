import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BloodCenterAdminComponent } from './blood-center-admin.component';

describe('BloodCenterAdminComponent', () => {
  let component: BloodCenterAdminComponent;
  let fixture: ComponentFixture<BloodCenterAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BloodCenterAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BloodCenterAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
