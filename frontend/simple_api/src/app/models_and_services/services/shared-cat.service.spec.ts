import { TestBed } from '@angular/core/testing';

import { SharedCatService } from './shared-cat.service';

describe('SharedCatService', () => {
  let service: SharedCatService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SharedCatService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
