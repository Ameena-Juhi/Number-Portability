import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PortingComponent } from './porting.component';

describe('PortingComponent', () => {
  let component: PortingComponent;
  let fixture: ComponentFixture<PortingComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PortingComponent]
    });
    fixture = TestBed.createComponent(PortingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
