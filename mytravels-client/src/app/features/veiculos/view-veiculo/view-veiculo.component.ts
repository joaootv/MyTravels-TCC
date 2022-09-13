import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { CidadesService } from 'src/app/services/cidades/cidades.service';
import { MotoristasService } from 'src/app/services/motoristas/motoristas.service';
import { VeiculosService } from 'src/app/services/veiculos/veiculos.service';

@Component({
  selector: 'app-view-veiculo',
  templateUrl: './view-veiculo.component.html',
  styleUrls: ['./view-veiculo.component.scss']
})
export class ViewVeiculoComponent implements OnInit {

  public veiculos:any;
  public id:any;

  constructor(
    private veiculosService: VeiculosService,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.findByVeiculos(this.id);
  }


  public findByVeiculos(id:number) {
    this.veiculosService.findByVeiculo(id).subscribe(
      response => {
        this.veiculos = response;
      }
    );
  }

}
