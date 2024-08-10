import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PessoaModalComponent } from './pessoa-modal.component';

describe('PessoaModalComponent', () => {
  let component: PessoaModalComponent;
  let fixture: ComponentFixture<PessoaModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PessoaModalComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PessoaModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
