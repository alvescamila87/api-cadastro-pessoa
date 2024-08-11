import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { PessoaService } from '../../services/pessoa.service';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  constructor(private pessoaService: PessoaService) {}

  generateCSVReport() {
    this.pessoaService.reportCSV().subscribe(
      (response: Blob) => {
        saveAs(response, 'pessoas.csv');
      },
      error => {
        console.error("Erro ao gerar arquivo CSV: ", error);
      }
    );
  }

}
