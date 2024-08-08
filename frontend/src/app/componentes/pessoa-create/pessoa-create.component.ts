import { PessoaService } from './../../services/pessoa.service';
import { Component } from '@angular/core';
import { PessoaDTO } from '../../model/PessoaDTO';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-pessoa-create',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './pessoa-create.component.html',
  styleUrl: './pessoa-create.component.css'
})
export class PessoaCreateComponent {

  pessoa: PessoaDTO = {
    id: 0,
    nomeCompleto: '',
    cpf: '',
    telefone: '',
    cep: '',
    enderecoId: 0
  };

  constructor(private pessoaService: PessoaService, private router: Router) {}

  savePessoa(): void {
    this.pessoaService.createPessoa(this.pessoa).subscribe(() => {
      this.router.navigate(['/pessoas']);
    })
  }

}
