import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckForAppointmentComponent } from './check-for-appointment.component';

describe('CheckForAppointmentComponent', () => {
  let component: CheckForAppointmentComponent;
  let fixture: ComponentFixture<CheckForAppointmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CheckForAppointmentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CheckForAppointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
