import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NikolaTestComponent } from './nikola-test.component';

describe('NikolaTestComponent', () => {
  let component: NikolaTestComponent;
  let fixture: ComponentFixture<NikolaTestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NikolaTestComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NikolaTestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
