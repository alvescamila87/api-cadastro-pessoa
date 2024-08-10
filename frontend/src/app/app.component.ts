import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from "./componentes/navbar/navbar.component";
import { FooterComponent } from "./componentes/footer/footer.component";
import { HeaderComponent } from "./componentes/header/header.component";
import { PessoaModalComponent } from "./componentes/pessoa-modal/pessoa-modal.component";
import { PessoaCreateComponent } from "./componentes/pessoa-create/pessoa-create.component";
import { PessoaListComponent } from "./componentes/pessoa-list/pessoa-list.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent, FooterComponent, HeaderComponent, PessoaModalComponent, PessoaCreateComponent, PessoaListComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
}
