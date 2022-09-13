import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ModalController } from '@ionic/angular';
import { EstadoViagem } from 'src/app/models/estadoViagem';
import { DespesaService } from 'src/app/services/despesa.service';

@Component({
  selector: 'app-edit-despesa',
  templateUrl: './edit-despesa.component.html',
  styleUrls: ['./edit-despesa.component.scss'],
})
export class EditDespesaComponent implements OnInit {

  @Input() despesaId;
  @Input() viagemId;
  public dataModel: FormGroup;

  tipoDespesas: EstadoViagem[] = [
    {value: 1, name: 'DIVERSA'},
    {value: 2, name: 'MANUTENCAO'},
  ];

  statusPagamento: EstadoViagem[] = [
    {value: 1, name: 'PAGO'},
    {value: 2, name: 'ASSINADO'},
  ];

  constructor(
    private modalCtrl: ModalController,
    private fb: FormBuilder,
    private despesaService: DespesaService,
  ) { }

  get f() {
    return this.dataModel.controls;
  }

  ionViewWillEnter() {
    this.findByDespesa();
  }

  ngOnInit() {
    this.dataModel = this.fb.group({
      estadoPagamento: ['', [Validators.required]],
      descricao: ['', [Validators.required]],
      local: ['', [Validators.required]],
      data: [''],
      preco: ['', [Validators.required]],
      tipoDespesa: ['', [Validators.required]],
    });
  }

  public findByDespesa() {
    this.despesaService.getDespesa(this.despesaId).subscribe(
      data => {
        console.log(data);
        if (data.estadoPagamento === 'PAGO') {
          data.estadoPagamento = 1;
        } else {
          data.estadoPagamento = 2;
        }
        if (data.tipoDespesa === 'DIVERSA') {
          data.tipoDespesa = 1;
        } else {
          data.tipoDespesa = 2;
        }
        this.dataModel.patchValue({
          estadoPagamento: data.estadoPagamento,
          descricao: data.descricao,
          local: data.local,
          data: data.data,
          preco: data.preco,
          tipoDespesa: data.tipoDespesa,
        });
      }
    );
  }

  public editar() {
    const data: any = this.dataModel.value;
    data.viagemId = this.viagemId;
    data.id = this.despesaId;
    this.despesaService.update(data).subscribe(
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
