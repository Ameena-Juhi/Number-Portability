import { TestBed } from '@angular/core/testing';

import { MNPSPService } from './mnpsp.service';

describe('MNPSPService', () => {
  let service: MNPSPService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MNPSPService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
