import { CommonModule, registerLocaleData } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import localePT from '@angular/common/locales/pt';
import { LOCALE_ID, NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgToastModule } from 'ng-angular-popup';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { MotoristasComponent } from './pages/motoristas/motoristas.component';
import { VeiculosComponent } from './pages/veiculos/veiculos.component';
import { AngularMaterialModule } from './shared/angular-material/angular-materia.module';
import { HeaderComponent } from './shared/header/header.component';
import { CidadesComponent } from './pages/cidades/cidades.component';
import { UsuariosComponent } from './pages/usuarios/usuarios.component';
import { EditViagemComponent } from './features/viagens/edit-viagem/edit-viagem.component';
import { ViewViagemComponent } from './features/viagens/view-viagem/view-viagem.component';
import { AddVeiculoComponent } from './features/veiculos/add-veiculo/add-veiculo.component';
import { ViewVeiculoComponent } from './features/veiculos/view-veiculo/view-veiculo.component';
import { EditVeiculoComponent } from './features/veiculos/edit-veiculo/edit-veiculo.component';
import { AddPessoaComponent } from './features/pessoa/add-pessoa/add-pessoa.component';
import { AddMotoristaComponent } from './features/motorista/add-motorista/add-motorista.component';

registerLocaleData(localePT);

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    HomeComponent,
    VeiculosComponent,
    MotoristasComponent,
    CidadesComponent,
    UsuariosComponent,
    EditViagemComponent,
    ViewViagemComponent,
    AddVeiculoComponent,
    ViewVeiculoComponent,
    EditVeiculoComponent,
    AddPessoaComponent,
    AddMotoristaComponent,
  ],
  imports: [
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    AngularMaterialModule,
    NgToastModule,
  ],
  providers: [
    { provide: LOCALE_ID, useValue: 'pt-br'}
  ],
  bootstrap: [AppComponent]
})

export class AppModule { }

