import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserScheduleAppointmentComponent } from './user-schedule-appointment.component';

describe('UserScheduleAppointmentComponent', () => {
  let component: UserScheduleAppointmentComponent;
  let fixture: ComponentFixture<UserScheduleAppointmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserScheduleAppointmentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserScheduleAppointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
