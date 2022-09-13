import { PedagioService } from './../../../services/pedagio.service';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ModalController } from '@ionic/angular';

@Component({
  selector: 'app-add-pedagio',
  templateUrl: './add-pedagio.component.html',
  styleUrls: ['./add-pedagio.component.scss'],
})
export class AddPedagioComponent implements OnInit {

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

  ngOnInit() {
    this.dataModel = this.fb.group({
      data: ['', [Validators.required]],
      descricao: ['', [Validators.required]],
      numEixos: ['', [Validators.required]],
      valor: ['', [Validators.required]],
    });
  }

  public dismiss() {
    this.modalCtrl.dismiss({
      dismissed: true
    }).catch(() => console.log('dismiss chamado sem modal'));
  }

  public cadastrar() {
    const data: any = this.dataModel.value;
    data.viagemId = this.viagemId;
    data.data = new Date();
    this.pedagioService.create(data).subscribe(
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
