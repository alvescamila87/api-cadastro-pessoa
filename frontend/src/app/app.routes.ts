import { Routes } from '@angular/router';
import { PessoaListComponent } from './componentes/pessoa-list/pessoa-list.component';
import { PessoaCreateComponent } from './componentes/pessoa-create/pessoa-create.component';

export const routes: Routes = [
  { path: 'pessoas', component: PessoaListComponent},
  { path: 'pessoas/cadastrar', component: PessoaCreateComponent},
  { path: '', redirectTo: '/pessoas', pathMatch: 'full'}
];
