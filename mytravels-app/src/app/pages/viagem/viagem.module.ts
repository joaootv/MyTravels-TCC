import { EditPedagioComponent } from './../../features/pedagio/edit-pedagio/edit-pedagio.component';
import { EditAbastecimentoComponent } from './../../features/abastecimento/edit-abastecimento/edit-abastecimento.component';
import { EditDespesaComponent } from './../../features/despesa/edit-despesa/edit-despesa.component';
import { AddPedagioComponent } from './../../features/pedagio/add-pedagio/add-pedagio.component';
import { AddAbastecimentoComponent } from './../../features/abastecimento/add-abastecimento/add-abastecimento.component';
import { AddDespesaComponent } from './../../features/despesa/add-despesa/add-despesa.component';
import { AddViagemComponent } from './../../features/viagem/add-viagem/add-viagem.component';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { IonicModule } from '@ionic/angular';

import { SharedModule } from './../../shared/shared.module';
import { ViagemPageRoutingModule } from './viagem-routing.module';
import { ViagemPage } from './viagem.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ViagemPageRoutingModule,
    SharedModule,
    MatInputModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    ReactiveFormsModule
  ],
  declarations: [
    ViagemPage,
    AddDespesaComponent,
    AddAbastecimentoComponent,
    AddPedagioComponent,
    EditDespesaComponent,
    EditAbastecimentoComponent,
    EditPedagioComponent
  ]
})
export class ViagemPageModule {}
