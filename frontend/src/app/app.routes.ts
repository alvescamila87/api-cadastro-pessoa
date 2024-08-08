import { Routes } from '@angular/router';
import { ContentComponent } from './componentes/content/content.component';
import { PessoaListComponent } from './componentes/pessoa-list/pessoa-list.component';
import { PessoaCreateComponent } from './componentes/pessoa-create/pessoa-create.component';

export const routes: Routes = [
  { path: '', component: ContentComponent},
  { path: 'link1', component: PessoaListComponent},
  { path: 'link2', component: PessoaCreateComponent}
];
