import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ValidateRequestsComponent } from './validate-requests.component';

describe('ValidateRequestsComponent', () => {
  let component: ValidateRequestsComponent;
  let fixture: ComponentFixture<ValidateRequestsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ValidateRequestsComponent]
    });
    fixture = TestBed.createComponent(ValidateRequestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
