import { Component, OnInit } from '@angular/core';
import { PessoaDTO } from '../../model/PessoaDTO';
import { PessoaService } from '../../services/pessoa.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-pessoa-list',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './pessoa-list.component.html',
  styleUrl: './pessoa-list.component.css'
})
export class PessoaListComponent implements OnInit{

  pessoas: PessoaDTO[] = [];

  constructor(private pessoaService: PessoaService){}

  ngOnInit(): void {
    this.loadPessoas();
  }

  loadPessoas(): void {
    this.pessoaService.getAllPessoas().subscribe(data => {
      this.pessoas = data;
    })
  }

  /*deletePessoa(id: number): void {
    this.pessoaService.deletePessoa(id).subscribe(() => {
      this.loadPessoas();
    })
  }*/

}
