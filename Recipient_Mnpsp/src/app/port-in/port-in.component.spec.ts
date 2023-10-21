import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PortInComponent } from './port-in.component';

describe('PortInComponent', () => {
  let component: PortInComponent;
  let fixture: ComponentFixture<PortInComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PortInComponent]
    });
    fixture = TestBed.createComponent(PortInComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
