import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ModalController } from '@ionic/angular';
import { EstadoViagem } from 'src/app/models/estadoViagem';
import { AbastecimentoService } from 'src/app/services/abastecimento.service';

@Component({
  selector: 'app-edit-abastecimento',
  templateUrl: './edit-abastecimento.component.html',
  styleUrls: ['./edit-abastecimento.component.scss'],
})
export class EditAbastecimentoComponent implements OnInit {

  @Input() abastecimentoId;
  @Input() viagemId;
  public dataModel: FormGroup;

  statusPagamento: EstadoViagem[] = [
    {value: 1, name: 'PAGO'},
    {value: 2, name: 'ASSINADO'},
  ];

  tipoAbastecimento: EstadoViagem[] = [
    {value: 1, name: 'DIESEL'},
    {value: 2, name: 'ARLA'},
    {value: 3, name: 'OUTRO'},
  ];

  constructor(
    private modalCtrl: ModalController,
    private fb: FormBuilder,
    private abastecimentoService: AbastecimentoService,
  ) { }

  get f() {
    return this.dataModel.controls;
  }

  ionViewWillEnter() {
    this.findByAbastecimento();
  }

  ngOnInit() {
    this.dataModel = this.fb.group({
      estadoPagamento: ['', [Validators.required]],
      kmVeiculo: ['', [Validators.required]],
      litros: ['', [Validators.required]],
      local: ['', [Validators.required]],
      preco: ['', [Validators.required]],
      tipoAbastecimento: ['', [Validators.required]],
    });
  }

  public findByAbastecimento() {
    this.abastecimentoService.getAbastecimento(this.abastecimentoId).subscribe(
      data => {
        console.log(data);
        if (data.estadoPagamento === 'PAGO') {
          data.estadoPagamento = 1;
        } else {
          data.estadoPagamento = 2;
        }
        if (data.tipoAbastecimento === 'DIESEL') {
          data.tipoAbastecimento = 1;
        } else if (data.tipoAbastecimento === 'ARLA') {
          data.tipoAbastecimento = 2;
        } else {
          data.tipoAbastecimento = 3;
        }
        this.dataModel.patchValue({
          estadoPagamento: data.estadoPagamento,
          kmVeiculo: data.kmVeiculo,
          litros: data.litros,
          local: data.local,
          preco: data.preco,
          tipoAbastecimento: data.tipoAbastecimento
        });
      }
    );
  }

  public editar() {
    const data: any = this.dataModel.value;
    data.id = Number(this.abastecimentoId);
    data.viagemId = Number(this.viagemId);
    this.abastecimentoService.update(data).subscribe(
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
