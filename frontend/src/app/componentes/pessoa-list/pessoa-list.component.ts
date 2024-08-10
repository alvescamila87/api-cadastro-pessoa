import { PessoaDTO } from './../../model/PessoaDTO';
import { Component, OnInit } from '@angular/core';
import { PessoaService } from '../../services/pessoa.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PessoaModalComponent } from '../pessoa-modal/pessoa-modal.component';

@Component({
  selector: 'app-pessoa-list',
  standalone: true,
  imports: [FormsModule, CommonModule, PessoaModalComponent],
  templateUrl: './pessoa-list.component.html',
  styleUrl: './pessoa-list.component.css'
})
export class PessoaListComponent implements OnInit{

  pessoas: PessoaDTO[] = [];
  pessoaSelected?: PessoaDTO;

  constructor(private pessoaService: PessoaService){}

  ngOnInit(): void {
    this.loadPessoas();
  }

  loadPessoas(): void {
    this.pessoaService.getAllPessoas().subscribe(data => {
      this.pessoas = data;
    })
  }

  showDetailsPessoa(pessoa: PessoaDTO): void {
    console.log("FUI ACIONADO: BOTÃO DETALHES");
    this.pessoaSelected = pessoa;
  }

  editPessoa(pessoa: PessoaDTO): void {
    // colocar logica
}

  closeModal(): void {
    //this.pessoaSelected = null;
  }

  deletePessoa(id: number): void {
    console.log("FUI ACIONADO: BOTÃO DELETAR");
    if (confirm('Você tem certeza que deseja deletar esta pessoa?')) {
      this.pessoaService.deletePessoa(id).subscribe(() => {
        this.pessoas = this.pessoas.filter(p => p.id !== id);
        this.loadPessoas();
      })
    }
  }
}
