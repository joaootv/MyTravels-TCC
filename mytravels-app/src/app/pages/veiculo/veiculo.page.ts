import { VeiculosService } from './../../services/veiculos.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-veiculo',
  templateUrl: './veiculo.page.html',
  styleUrls: ['./veiculo.page.scss'],
})
export class VeiculoPage implements OnInit {

  public veiculos;

  constructor(
    private veiculosService: VeiculosService
  ) { }

  ngOnInit() {
    this.getVeiculos();
  }

  public getVeiculos() {
    this.veiculosService.getVeiculos()
      .subscribe(
        data => {
          this.veiculos = data;
        }
      );
  }

}
