import { DespesaService } from './../../../services/despesa.service';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ModalController } from '@ionic/angular';
import { EstadoViagem } from 'src/app/models/estadoViagem';

@Component({
  selector: 'app-add-despesa',
  templateUrl: './add-despesa.component.html',
  styleUrls: ['./add-despesa.component.scss'],
})
export class AddDespesaComponent implements OnInit {

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

  public cadastrar() {
    const data: any = this.dataModel.value;
    data.viagemId = this.viagemId;
    data.data = new Date();
    this.despesaService.create(data).subscribe(
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
