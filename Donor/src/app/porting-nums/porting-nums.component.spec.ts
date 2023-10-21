import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PortingNumsComponent } from './porting-nums.component';

describe('PortingNumsComponent', () => {
  let component: PortingNumsComponent;
  let fixture: ComponentFixture<PortingNumsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PortingNumsComponent]
    });
    fixture = TestBed.createComponent(PortingNumsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
