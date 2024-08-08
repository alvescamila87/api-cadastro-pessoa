import { Component, OnInit } from '@angular/core';
import { PessoaDTO } from '../../model/PessoaDTO';
import { PessoaService } from '../../services/pessoa.service';

@Component({
  selector: 'app-pessoa-list',
  standalone: true,
  imports: [],
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

}
