import { StorageService } from './../../../services/storage/storage.service';
import { VeiculosService } from './../../../services/veiculos/veiculos.service';
import { MotoristasService } from './../../../services/motoristas/motoristas.service';
import { CidadesService } from './../../../services/cidades/cidades.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-veiculo',
  templateUrl: './add-veiculo.component.html',
  styleUrls: ['./add-veiculo.component.scss']
})
export class AddVeiculoComponent implements OnInit {

  public dataModel!: FormGroup;
  public veiculos:any;
  public cidades:any;
  public motoristas:any;
  public tipos:any;
  public idTransportadora:any;

  constructor(
    private fb: FormBuilder,
    private cidadesService: CidadesService,
    private motoristasService: MotoristasService,
    private veiculosService: VeiculosService,
    private storageService: StorageService,
    private router: Router,
  ) { }

  get f() {
    return this.dataModel.controls;
  }

  ngOnInit(): void {
    this.idTransportadora = this.storageService.getTransportadora();
    this.dataModel = this.fb.group({
      anoFabricacao: ['', [Validators.required]],
      anoModelo: ['', [Validators.required]],
      chassi: ['', [Validators.required]],
      cidadeEmplacado: ['', [Validators.required]],
      cor: ['', [Validators.required]],
      exercicioDoc: ['', [Validators.required]],
      idTipo: ['', [Validators.required]],
      kilometragem: ['', [Validators.required]],
      marca: ['', [Validators.required]],
      modelo: ['', [Validators.required]],
      motorista: ['', [Validators.required]],
      placa: ['', [Validators.required]],
      renavam: ['', [Validators.required]]
    });
    this.findByCidades();
    this.findByMotoristas();
    this.findByTipoVeiculos();
  }

  public findByCidades() {
    this.cidadesService.findAllCidades().subscribe(
      response => {
        this.cidades = response;
      }
    );
  }

  public findByMotoristas() {
    this.motoristasService.findAllMotoristas().subscribe(
      response => {
        this.motoristas = response;
      }
    );
  }

  public findByTipoVeiculos() {
    this.veiculosService.findAllTipoVeiculos().subscribe(
      response => {
        this.tipos = response;
      }
    );
  }

  public create() {
    const data: any = this.dataModel.value;
    data.transportadora = this.idTransportadora;
    console.log(data);
      this.veiculosService.create(data).subscribe(
        response => {
          this.router.navigate(['/veiculos']);
        },
        error => {
          console.log(error);
          this.dataModel.reset();
        }
      );
  }

}
