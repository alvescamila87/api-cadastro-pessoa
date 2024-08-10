import { PessoaService } from './../../services/pessoa.service';
import { Component } from '@angular/core';
import { PessoaDTO } from '../../model/PessoaDTO';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { EnderecoDTO } from '../../model/EnderecoDTO';
import { EnderecoVIACEPService } from '../../services/endereco-viacep.service';
import { HttpErrorResponse } from '@angular/common/http';

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

  validateCPF(): boolean {
    const cpf = this.pessoa.cpf.replace(/\D/g, '');
    if(cpf.length != 11) {
      alert('O CPF ' + cpf + ' é inválido. Deve conter 11 digitos.');
      return false;
    }
    return true;
  }

  validateCEP(): boolean {
    const cep = this.pessoa.endereco.cep.replace(/\D/g, '');
    if(cep.length != 8) {
      alert('O CEP ' + cep + ' é inválido. Deve conter 8 digitos.');
      return false;
    }
    return true;
  }

  savePessoa(): void {
    console.log("SALVAR: Acionado");
    if(this.validateCEP() && this.validateCPF()) {
      this.pessoaService.createPessoa(this.pessoa).subscribe({
        next: () => {
          this.router.navigate(['/pessoas']);
          alert("Dados alterados com sucesso!");
        },
        error: (error: HttpErrorResponse) => {
          alert(`O CEP `+ this.pessoa.cpf + ` informado já possui cadastro. Tente um CPF diferente.`);
          console.error('Erro ao salvar a pessoa: ' + error);
        }
      });
    } else {
      alert("Campos obrigatórios não preenchidos. Tente novamente.")
    }
  }

  buscarCEP(): void {
    if(this.validateCEP()){
      this.enderecoService.getCEP(this.pessoa.endereco.cep).subscribe({
        next: (res: EnderecoDTO) => {
          //console.log(res);
          this.pessoa.endereco = res;
          this.pessoa.endereco.complemento = this.pessoa.endereco.complemento;
          },
        });
      }
    }
 }


