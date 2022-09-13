import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'login',
    loadChildren: () => import('./pages/login/login.module').then( m => m.LoginPageModule)
  },
  {
    path: 'home',
    loadChildren: () => import('./pages/home/home.module').then( m => m.HomePageModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'veiculo',
    loadChildren: () => import('./pages/veiculo/veiculo.module').then( m => m.VeiculoPageModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'motorista',
    loadChildren: () => import('./pages/motorista/motorista.module').then( m => m.MotoristaPageModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'transportadora',
    loadChildren: () => import('./pages/transportadora/transportadora.module').then( m => m.TransportadoraPageModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'viagem/:id',
    loadChildren: () => import('./pages/viagem/viagem.module').then( m => m.ViagemPageModule)
  },
  {
    path: 'relatorio/:id',
    loadChildren: () => import('./pages/relatorio-viagem/relatorio-viagem.module').then( m => m.RelatorioViagemPageModule)
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
