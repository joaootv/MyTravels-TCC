import { AddPessoaComponent } from './features/pessoa/add-pessoa/add-pessoa.component';
import { EditVeiculoComponent } from './features/veiculos/edit-veiculo/edit-veiculo.component';
import { ViewVeiculoComponent } from './features/veiculos/view-veiculo/view-veiculo.component';
import { AddVeiculoComponent } from './features/veiculos/add-veiculo/add-veiculo.component';
import { ViewViagemComponent } from './features/viagens/view-viagem/view-viagem.component';
import { EditViagemComponent } from './features/viagens/edit-viagem/edit-viagem.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from './guards/auth.guard';
import { CidadesComponent } from './pages/cidades/cidades.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { MotoristasComponent } from './pages/motoristas/motoristas.component';
import { UsuariosComponent } from './pages/usuarios/usuarios.component';
import { VeiculosComponent } from './pages/veiculos/veiculos.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'viagens', component: HomeComponent, canActivate: [AuthGuard]},
  { path: 'viagem/:id', component: EditViagemComponent, canActivate: [AuthGuard]},
  { path: 'relatorio/viagem/:id', component: ViewViagemComponent, canActivate: [AuthGuard]},
  { path: 'relatorio/veiculo/:id', component: ViewVeiculoComponent, canActivate: [AuthGuard]},
  { path: 'veiculos', component: VeiculosComponent, canActivate: [AuthGuard]},
  { path: 'veiculos/adicionar', component: AddVeiculoComponent, canActivate: [AuthGuard]},
  { path: 'veiculos/editar/:id', component: EditVeiculoComponent, canActivate: [AuthGuard]},
  { path: 'motorista/pessoa/adicionar', component: AddPessoaComponent, canActivate: [AuthGuard]},
  { path: 'cidades', component: CidadesComponent, canActivate: [AuthGuard]},
  { path: 'usuarios', component: UsuariosComponent, canActivate: [AuthGuard]},
  { path: 'motoristas', component: MotoristasComponent, canActivate: [AuthGuard]},
  { path: '', redirectTo: 'viagens', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [AuthGuard]
})
export class AppRoutingModule { }
