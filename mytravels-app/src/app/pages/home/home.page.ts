import { AddViagemComponent } from './../../features/viagem/add-viagem/add-viagem.component';
import { ModalController } from '@ionic/angular';
import { StorageService } from './../../services/storage.service';
import { MotoristaService } from './../../services/motorista.service';
import { ViagensService } from './../../services/viagens.service';
import { Component, OnInit } from '@angular/core';
import { LocalUser } from 'src/app/models/local_user';

@Component({
  selector: 'app-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
})
export class HomePage implements OnInit {

  public viagens;

  constructor(
    private motoristaService: MotoristaService,
    private viagensService: ViagensService,
    private storageService: StorageService,
    private modalCtrl: ModalController
  ) { }

  ionViewWillEnter() {
    this.getIdMotorista();
    this.getViagens();
  }

  ngOnInit() {
    if(this.storageService.getIdMotorista()) {
      this.getViagens();
    }
   }

  public getIdMotorista() {
    this.motoristaService.getMotorista().subscribe(
      data => {
        this.storageService.setIdMotorista(data.id);
        return this.storageService.getIdMotorista();
      }
    );
  }

  getViagens() {
    const id = this.storageService.getIdMotorista();
    this.viagensService.getViagens(id)
      .subscribe(
        data => {
          this.viagens = data;
        }
    );
  }

  public async addViagem(){
    const modal = await this.modalCtrl.create({
      component: AddViagemComponent,
      cssClass: 'large-modal'
    });

    return await modal.present();
  }

}
