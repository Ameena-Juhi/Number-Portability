import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MnpspValidationComponent } from './mnpsp-validation.component';

describe('MnpspValidationComponent', () => {
  let component: MnpspValidationComponent;
  let fixture: ComponentFixture<MnpspValidationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MnpspValidationComponent]
    });
    fixture = TestBed.createComponent(MnpspValidationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
