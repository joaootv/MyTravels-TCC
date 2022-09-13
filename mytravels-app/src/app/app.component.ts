import { StorageService } from './services/storage.service';
import { Component } from '@angular/core';
@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent {
  public appPages = [
    { title: 'Minhas Viagens', url: '/home', icon: 'subway' },
    { title: 'Meu Veículo', url: '/veiculo', icon: 'bus' },
    { title: 'Meus Dados', url: '/motorista', icon: 'file-tray-full' },
    { title: 'Transportadora', url: '/transportadora', icon: 'clipboard' },
  ];
  constructor(
    private storage: StorageService
  ) { }

}
