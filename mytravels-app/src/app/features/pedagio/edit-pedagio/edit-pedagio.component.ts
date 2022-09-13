import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ModalController } from '@ionic/angular';
import { PedagioService } from 'src/app/services/pedagio.service';

@Component({
  selector: 'app-edit-pedagio',
  templateUrl: './edit-pedagio.component.html',
  styleUrls: ['./edit-pedagio.component.scss'],
})
export class EditPedagioComponent implements OnInit {

  @Input() pedagioId;
  @Input() viagemId;
  public dataModel: FormGroup;

  constructor(
    private modalCtrl: ModalController,
    private fb: FormBuilder,
    private pedagioService: PedagioService,
  ) { }

  get f() {
    return this.dataModel.controls;
  }

  ionViewWillEnter() {
    this.findByPedagio();
  }

  ngOnInit() {
    this.dataModel = this.fb.group({
      data: ['', [Validators.required]],
      descricao: ['', [Validators.required]],
      numEixos: ['', [Validators.required]],
      valor: ['', [Validators.required]],
    });
  }

  public findByPedagio(){
    this.pedagioService.getPedagio(this.pedagioId).subscribe(
      data => {
        console.log(data);
        this.dataModel.patchValue({
          data: data.data,
          descricao: data.descricao,
          numEixos: data.numEixos,
          valor: data.valor,
        });
      }
    );
  }

  public dismiss() {
    this.modalCtrl.dismiss({
      dismissed: true
    }).catch(() => console.log('dismiss chamado sem modal'));
  }

  public editar() {
    const data: any = this.dataModel.value;
    data.viagemId = this.viagemId;
    data.id = this.pedagioId;
    this.pedagioService.update(data).subscribe(
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

}
