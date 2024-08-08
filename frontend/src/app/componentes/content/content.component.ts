import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterModule } from '@angular/router';
import { PessoaService } from '../../services/pessoa.service';

@Component({
  selector: 'app-content',
  standalone: true,
  imports: [],
  templateUrl: './content.component.html',
  styleUrl: './content.component.css'
})
export class ContentComponent implements OnInit{

  // preciso injetar o serviço que busca a pessoa
  constructor(private service: PessoaService){
    console.log("Estou no construtor do componente: conteúdo.")
  }

  ngOnInit(): void {
    console.log("Estou na inicialização do componente: conteúdo.")
    this.service.getAllPessoas()
    .subscribe(res => console.log(res),
               err => console.log(err));
  }

}
