import { StorageService } from './../../../services/storage/storage.service';
import { CidadesService } from 'src/app/services/cidades/cidades.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MotoristasService } from 'src/app/services/motoristas/motoristas.service';
import { VeiculosService } from 'src/app/services/veiculos/veiculos.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-veiculo',
  templateUrl: './edit-veiculo.component.html',
  styleUrls: ['./edit-veiculo.component.scss']
})
export class EditVeiculoComponent implements OnInit {

  public dataModel!: FormGroup;
  public veiculos:any;
  public cidades:any;
  public motoristas:any;
  public tipos:any;
  public idTransportadora:any;
  public id:any;

  constructor(
    private fb: FormBuilder,
    private cidadesService: CidadesService,
    private motoristasService: MotoristasService,
    private veiculosService: VeiculosService,
    private storageService: StorageService,
    private router: Router,
    private route: ActivatedRoute,
  ) { }

  get f() {
    return this.dataModel.controls;
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.idTransportadora = this.storageService.getTransportadora();
    this.dataModel = this.fb.group({
      anoFabricacao: ['', [Validators.required]],
      anoModelo: ['', [Validators.required]],
      chassi: ['', [Validators.required]],
      cidadeEmplacado: ['', [Validators.required]],
      cor: ['', [Validators.required]],
      exercicioDoc: ['', [Validators.required]],
      tipo: ['', [Validators.required]],
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
    this.findByVeiculos(this.id);
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

  public findByVeiculos(id:number) {
    this.veiculosService.findByVeiculo(id).subscribe(
      response => {
        this.dataModel.patchValue({
          anoFabricacao: response.anoFabricacao,
          anoModelo: response.anoModelo,
          chassi: response.chassi,
          cidadeEmplacado: response.cidadeEmplacado.id,
          cor: response.cor,
          exercicioDoc: response.exercicioDoc,
          tipo: response.tipo.id,
          kilometragem: response.kilometragem,
          marca: response.marca,
          modelo: response.modelo,
          motorista: response.motorista.id,
          placa: response.placa,
          renavam: response.renavam
        });
      }
    );
  }

  public edit() {
    const data: any = this.dataModel.value;
    data.id = this.id;
    data.transportadora = this.idTransportadora;
    console.log(data);
      this.veiculosService.update(data).subscribe(
        response => {
          this.router.navigate(['/veiculos']);
        },
        error => {
          console.log(error);
        }
      );
  }

}

