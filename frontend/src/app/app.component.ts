import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from "./componentes/navbar/navbar.component";
import { FooterComponent } from "./componentes/footer/footer.component";
import { ContentComponent } from "./componentes/content/content.component";
import { HeaderComponent } from "./componentes/header/header.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent, FooterComponent, ContentComponent, HeaderComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
}
