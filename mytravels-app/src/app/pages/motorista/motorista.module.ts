import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { IonicModule } from '@ionic/angular';

import { SharedModule } from './../../shared/shared.module';
import { MotoristaPageRoutingModule } from './motorista-routing.module';
import { MotoristaPage } from './motorista.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    MotoristaPageRoutingModule,
    SharedModule,
    MatInputModule
  ],
  declarations: [MotoristaPage]
})
export class MotoristaPageModule {}
