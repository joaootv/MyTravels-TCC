import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RelatorioViagemPage } from './relatorio-viagem.page';

const routes: Routes = [
  {
    path: '',
    component: RelatorioViagemPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class RelatorioViagemPageRoutingModule {}
