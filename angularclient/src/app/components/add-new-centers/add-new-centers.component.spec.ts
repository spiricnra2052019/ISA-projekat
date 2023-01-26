import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewCentersComponent } from './add-new-centers.component';

describe('AddNewCentersComponent', () => {
  let component: AddNewCentersComponent;
  let fixture: ComponentFixture<AddNewCentersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddNewCentersComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddNewCentersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
