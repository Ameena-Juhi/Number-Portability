import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MNPSPComponent } from './mnpsp.component';

describe('MNPSPComponent', () => {
  let component: MNPSPComponent;
  let fixture: ComponentFixture<MNPSPComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MNPSPComponent]
    });
    fixture = TestBed.createComponent(MNPSPComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
