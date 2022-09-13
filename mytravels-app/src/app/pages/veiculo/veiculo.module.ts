import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { IonicModule } from '@ionic/angular';

import { SharedModule } from './../../shared/shared.module';
import { VeiculoPageRoutingModule } from './veiculo-routing.module';
import { VeiculoPage } from './veiculo.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    VeiculoPageRoutingModule,
    SharedModule,
    MatInputModule
  ],
  declarations: [VeiculoPage]
})
export class VeiculoPageModule {}
