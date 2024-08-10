import { PessoaDTO } from './../../model/PessoaDTO';
import { EnderecoDTO } from './../../model/EnderecoDTO';
import { Component, OnInit } from '@angular/core';
import { PessoaService } from '../../services/pessoa.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { EnderecoVIACEPService } from '../../services/endereco-viacep.service';

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
    console.log("FUI ACIONADO: BOTÃO DETALHES");
    this.pessoaSelected = pessoa;
  }

  editPessoa(pessoa: PessoaDTO): void {
    console.log("Entrou no editar" +  pessoa)
    this.pessoaSelected = pessoa;
  }

  deletePessoa(id: number): void {
    console.log("FUI ACIONADO: BOTÃO DELETAR");
    if (confirm('Você tem certeza que deseja excluir esta pessoa?')) {
      this.pessoaService.deletePessoa(id).subscribe(() => {
        this.pessoas = this.pessoas.filter(p => p.id !== id);
        this.loadPessoas();
      })
    }
  }

  buscarCEP(): void {
    this.enderecoService.getCEP(this.pessoaSelected.endereco.cep).subscribe({
      next: (res: EnderecoDTO) => {
        //console.log(res);
        this.pessoaSelected.endereco = res;
        this.pessoaSelected.endereco.complemento = this.pessoaSelected.endereco.complemento;
        }
      });
    }

    savePessoa(): void {
      console.log("SALVAR: Acionado");
      this.pessoaService.createPessoa(this.pessoaSelected).subscribe(() => {
        alert("Dados alterados com sucesso!");
      })
      console.log("SALVAR: após this");
    }

    refreshEdit(): void{
      //this.ngOnInit();
      window.location.reload();
    }
  }
