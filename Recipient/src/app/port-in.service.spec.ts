import { TestBed } from '@angular/core/testing';

import { PortInService } from './port-in.service';

describe('PortInService', () => {
  let service: PortInService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PortInService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
