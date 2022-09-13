import { EstadoViagem } from './../../../models/estadoViagem';
import { AbastecimentoService } from './../../../services/abastecimento.service';
import { ModalController } from '@ionic/angular';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-abastecimento',
  templateUrl: './add-abastecimento.component.html',
  styleUrls: ['./add-abastecimento.component.scss'],
})
export class AddAbastecimentoComponent implements OnInit {

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

  public cadastrar() {
    const data: any = this.dataModel.value;
    data.viagemId = this.viagemId;
    this.abastecimentoService.create(data).subscribe(
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
