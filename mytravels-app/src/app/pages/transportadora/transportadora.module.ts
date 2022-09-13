import { SharedModule } from './../../shared/shared.module';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { TransportadoraPageRoutingModule } from './transportadora-routing.module';

import { TransportadoraPage } from './transportadora.page';
import { MatInputModule } from '@angular/material/input';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    TransportadoraPageRoutingModule,
    SharedModule,
    MatInputModule
  ],
  declarations: [TransportadoraPage]
})
export class TransportadoraPageModule {}
