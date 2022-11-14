import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BloodCenterListComponent } from './blood-center-list.component';

describe('BloodCenterListComponent', () => {
  let component: BloodCenterListComponent;
  let fixture: ComponentFixture<BloodCenterListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BloodCenterListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BloodCenterListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
