import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchScheduleAppointmentComponent } from './search-schedule-appointment.component';

describe('SearchScheduleAppointmentComponent', () => {
  let component: SearchScheduleAppointmentComponent;
  let fixture: ComponentFixture<SearchScheduleAppointmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchScheduleAppointmentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchScheduleAppointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
