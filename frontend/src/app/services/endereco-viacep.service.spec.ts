import { TestBed } from '@angular/core/testing';

import { EnderecoVIACEPService } from './endereco-viacep.service';

describe('EnderecoVIACEPService', () => {
  let service: EnderecoVIACEPService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EnderecoVIACEPService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
