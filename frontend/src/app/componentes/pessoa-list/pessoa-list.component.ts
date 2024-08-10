import { PessoaDTO } from './../../model/PessoaDTO';
import { EnderecoDTO } from './../../model/EnderecoDTO';
import { Component, OnInit } from '@angular/core';
import { PessoaService } from '../../services/pessoa.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { EnderecoVIACEPService } from '../../services/endereco-viacep.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-pessoa-list',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './pessoa-list.component.html',
  styleUrl: './pessoa-list.component.css'
})
export class PessoaListComponent implements OnInit{

  pessoas: PessoaDTO[] = [];
  pessoaSelected: PessoaDTO;

  constructor(private pessoaService: PessoaService, private enderecoService: EnderecoVIACEPService){
    this.pessoaSelected = new PessoaDTO();
  }

  ngOnInit(): void {
    this.loadPessoas();
  }

  loadPessoas(): void {
    this.pessoaService.getAllPessoas().subscribe(data => {
      this.pessoas = data;
    })
  }

  showDetailsPessoa(pessoa: PessoaDTO): void {
    //console.log("FUI ACIONADO: BOTÃO DETALHES");
    this.pessoaSelected = pessoa;
  }

  editPessoa(pessoa: PessoaDTO): void {
    //console.log("Entrou no editar" +  pessoa)
    this.pessoaSelected = pessoa;
  }

  deletePessoa(id: number): void {
    //console.log("FUI ACIONADO: BOTÃO DELETAR");
    if (confirm('Você tem certeza que deseja excluir esta pessoa?')) {
      this.pessoaService.deletePessoa(id).subscribe(() => {
        this.pessoas = this.pessoas.filter(p => p.id !== id);
        this.loadPessoas();
      })
    }
  }

  buscarCEP(): void {
    if(this.validateCEP()){
      this.enderecoService.getCEP(this.pessoaSelected.endereco.cep).subscribe({
        next: (res: EnderecoDTO) => {
          //console.log(res);
          this.pessoaSelected.endereco = res;
          this.pessoaSelected.endereco.complemento = this.pessoaSelected.endereco.complemento;
          }
        });
      }
    }

    savePessoa(): void {
      //console.log("SALVAR DA EDIÇÃO: Acionado");
      if(this.validateCEP() && this.validateCPF()) {
        this.pessoaService.createPessoa(this.pessoaSelected).subscribe({
          next: () => {
            alert("Dados alterados com sucesso!");
          },
          error: (error: HttpErrorResponse) => {
            alert(`O CEP `+ this.pessoaSelected.cpf + ` informado já possui cadastro. Tente um CPF diferente.`);
            console.error('Erro ao salvar a pessoa: ' + error);
          }
        })
      } else {
        alert("Campos obrigatórios não preenchidos. Tente novamente.")
      }
    }

    refreshEdit(): void{
      //this.ngOnInit();
      window.location.reload();
    }


  validateCPF(): boolean {
    const cpf = this.pessoaSelected.cpf.replace(/\D/g, '');
    if(cpf.length != 11) {
      alert('O CPF ' + cpf + ' é inválido. Deve conter 11 digitos.');
      return false;
    }
    return true;
  }

  validateCEP(): boolean {
    const cep = this.pessoaSelected.endereco.cep.replace(/\D/g, '');
    if(cep.length != 8) {
      alert('O CEP ' + cep + ' é inválido. Deve conter 8 digitos.');
      return false;
    }
    return true;
  }

  filterSearch(id: number): void {
    if (id > 0) {
      this.pessoaService.getPessoaById(id).subscribe(
        (pessoa) => {
          this.pessoas = [pessoa];
        },
        (error) => {
          console.error('Erro ao buscar pessoa:', error);
          this.pessoas = [];
        }
      );
    } else {
      alert('ID inválido. Tente novamente.')
      this.loadPessoas();
    }
  }

}
