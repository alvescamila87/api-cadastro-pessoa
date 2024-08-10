import { PessoaService } from './../../services/pessoa.service';
import { Component } from '@angular/core';
import { PessoaDTO } from '../../model/PessoaDTO';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { EnderecoDTO } from '../../model/EnderecoDTO';
import { EnderecoVIACEPService } from '../../services/endereco-viacep.service';

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
    endereco: {
      id: 0,
      cep: '',
      logradouro: '',
      numero: '',
      complemento: '',
      bairro: '',
      localidade: '',
      uf: '',
      ibge: '',
      gia: '',
      ddd: '',
      siafi: ''
    }
  };

  constructor(private pessoaService: PessoaService, private router: Router, private enderecoService: EnderecoVIACEPService) {}

  savePessoa(): void {
    console.log("SALVAR: Acionado");
    this.pessoaService.createPessoa(this.pessoa).subscribe(() => {
      this.router.navigate(['/pessoas']);
    })
    console.log("SALVAR: após this");
  }

  buscarCEP(): void {
    this.enderecoService.getCEP(this.pessoa.endereco.cep).subscribe({
      next: (res: EnderecoDTO) => {
        //console.log(res);
        this.pessoa.endereco = res;
        this.pessoa.endereco.complemento = this.pessoa.endereco.complemento;
        }
      });
    }
 }


