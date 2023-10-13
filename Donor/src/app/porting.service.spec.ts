import { TestBed } from '@angular/core/testing';

import { PortingService } from './porting.service';

describe('PortingService', () => {
  let service: PortingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PortingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
