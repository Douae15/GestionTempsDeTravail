import { TestBed } from '@angular/core/testing';

import { HumeurService } from './humeur.service';

describe('HumeurService', () => {
  let service: HumeurService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HumeurService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
