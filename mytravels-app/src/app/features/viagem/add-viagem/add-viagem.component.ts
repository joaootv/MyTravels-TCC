import { VeiculosService } from './../../../services/veiculos.service';
import { StorageService } from './../../../services/storage.service';
import { ViagemNewDTO } from './../../../models/viagem';
import { ViagensService } from './../../../services/viagens.service';
import { EstadoViagem } from './../../../models/estadoViagem';
import { CidadeService } from './../../../services/cidade.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ModalController } from '@ionic/angular';
import { MotoristaService } from 'src/app/services/motorista.service';

@Component({
  selector: 'app-add-viagem',
  templateUrl: './add-viagem.component.html',
  styleUrls: ['./add-viagem.component.scss'],
})
export class AddViagemComponent implements OnInit {

  public dataModel: FormGroup;
  public cidades;
  public dataStart;
  public motoristaId;
  public veiculoId;

  constructor(
    private modalCtrl: ModalController,
    private fb: FormBuilder,
    private cidadeService: CidadeService,
    private viagemService: ViagensService,
    private motoristaService: MotoristaService,
    private storageService: StorageService,
    private veiculoService: VeiculosService
  ) { }

  get f() {
    return this.dataModel.controls;
  }

  ionViewWillEnter() {
    this.findByCidades();
    this.getMotorista();
    this.getVeiculos();
  }

  ngOnInit() {
    this.dataModel = this.fb.group({
      origem: ['', [Validators.required]],
      destino: ['', [Validators.required]],
      kmIncial: ['', [Validators.required]],
      saldoInicial: ['', [Validators.required]],
      valorFrete: ['', [Validators.required]],
      pesoTonelada: ['', [Validators.required]],
    });
  }

  public findByCidades() {
    this.cidadeService.getCidades().subscribe(
      response => {
        this.cidades = response;
      }
    );
  }

  public getMotorista() {
    this.motoristaService.getMotorista()
      .subscribe(
        data => {
          this.motoristaId = data.id;
        }
      );
  }

  public getVeiculos() {
    this.veiculoService.getVeiculos()
      .subscribe(
        data => {
          this.veiculoId = data.id;
        }
      );
  }

  public cadastrar() {
    const data: ViagemNewDTO = this.dataModel.value;
    data.motoristaId = this.motoristaId;
    data.transportadoraId = this.storageService.getTransportadora();
    data.veiculoId = this.veiculoId;
    this.viagemService.create(data).subscribe(
      response => {
        console.log(response);
        this.dismiss();
        location.reload();
      },
      error => {
        console.log(error);
      }
    );
  }

  public dismiss() {
    this.modalCtrl.dismiss({
      dismissed: true
    }).catch(() => console.log('dismiss chamado sem modal'));
  }

}
