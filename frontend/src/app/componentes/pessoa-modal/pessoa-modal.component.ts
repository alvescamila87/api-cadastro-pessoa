import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PessoaDTO } from '../../model/PessoaDTO';

@Component({
  selector: 'app-pessoa-modal',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pessoa-modal.component.html',
  styleUrl: './pessoa-modal.component.css'
})
export class PessoaModalComponent {

  @Input() pessoa: PessoaDTO | null = null;
  @Output() close = new EventEmitter<void>();

  closeModal(): void {
    this.close.emit();
  }

}
