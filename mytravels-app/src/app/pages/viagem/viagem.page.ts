import { EditPedagioComponent } from './../../features/pedagio/edit-pedagio/edit-pedagio.component';
import { EditDespesaComponent } from './../../features/despesa/edit-despesa/edit-despesa.component';
import { EditAbastecimentoComponent } from './../../features/abastecimento/edit-abastecimento/edit-abastecimento.component';
import { AlertController, ModalController, ToastController } from '@ionic/angular';
import { AddDespesaComponent } from './../../features/despesa/add-despesa/add-despesa.component';
import { AddAbastecimentoComponent } from './../../features/abastecimento/add-abastecimento/add-abastecimento.component';
import { AddPedagioComponent } from './../../features/pedagio/add-pedagio/add-pedagio.component';
import { PedagioService } from './../../services/pedagio.service';
import { DespesaService } from './../../services/despesa.service';
import { AbastecimentoService } from './../../services/abastecimento.service';
import { ViagensService } from './../../services/viagens.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CidadeService } from 'src/app/services/cidade.service';
import { format, parseISO } from 'date-fns';
import { EstadoViagem } from 'src/app/models/estadoViagem';
import { StorageService } from 'src/app/services/storage.service';
import { MotoristaService } from 'src/app/services/motorista.service';
import { VeiculosService } from 'src/app/services/veiculos.service';

@Component({
  selector: 'app-viagem',
  templateUrl: './viagem.page.html',
  styleUrls: ['./viagem.page.scss'],
})
export class ViagemPage implements OnInit {

  public dataModel: FormGroup;
  public cidades;
  public id;
  public abastecimentos;
  public despesas;
  public pedagios;
  public dialog = false;
  public error;
  public motoristaId;
  public veiculoId;

  statusPagamento: EstadoViagem[] = [
    {value: 1, name: 'PAGO'},
    {value: 2, name: 'ASSINADO'},
  ];

  constructor(
    private fb: FormBuilder,
    private cidadeService: CidadeService,
    private route: ActivatedRoute,
    private viagemService: ViagensService,
    private abastecimentoService: AbastecimentoService,
    private despesaService: DespesaService,
    private pedagioService: PedagioService,
    private modalCtrl: ModalController,
    private router: Router,
    private storageService: StorageService,
    private motoristaService: MotoristaService,
    private veiculoService: VeiculosService,
    private alertController: AlertController,
    private toastController: ToastController
  ) { }

  get f() {
    return this.dataModel.controls;
  }

  ionViewWillEnter() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.findByViagem();
    this.findByCidades();
    this.findByLists();
    this.getMotorista();
    this.getVeiculos();
  }

  ngOnInit() {
    this.dataModel = this.fb.group({
      origem: ['', [Validators.required]],
      destino: ['', [Validators.required]],
      kmIncial: ['', [Validators.required]],
      valorFrete: ['', [Validators.required]],
      pesoTonelada: ['', [Validators.required]],
      veiculo: ['', [Validators.required]],
      motorista: ['', [Validators.required]],
      kmFinal: ['', [Validators.required]],
      dataInical: ['', [Validators.required]],
    });
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

  public findByCidades() {
    this.cidadeService.getCidades().subscribe(
      response => {
        this.cidades = response;
      }
    );
  }

  public findByLists() {
    this.abastecimentoService.getAbastecimentos(this.id).subscribe(
      response => this.abastecimentos = response
    );
    this.despesaService.getDespesas(this.id).subscribe(
      response => this.despesas = response
    );
    this.pedagioService.pedagios(this.id).subscribe(
      response => this.pedagios = response
    );
  }

  public findByViagem() {
    this.viagemService.getViagem(this.id).subscribe(
      response => {
        console.log(response);
        response.dataInical = format(parseISO(response.dataInical), 'dd/MM/yyyy hh:mm');
        this.dataModel.patchValue({
          origem: response.origem.id,
          destino: response.destino.id,
          kmIncial: response.kmIncial,
          kmFinal: response.kmFinal,
          valorFrete: response.valorFrete,
          pesoTonelada: response.pesoTonelada,
          veiculo: response.veiculo.placa,
          motorista: response.motorista.pessoa.nome,
          dataInical: response.dataInical
        });
      }, error => {
        console.log(error);

      }
    );
  }

  public async despesa(){
    const modal = await this.modalCtrl.create({
      component: AddDespesaComponent,
      componentProps: {
        viagemId: this.id,
      },
      cssClass: 'large-modal'
    });

    return await modal.present();
  }

  public async despesaEdit(id){
    const modal = await this.modalCtrl.create({
      component: EditDespesaComponent,
      componentProps: {
        despesaId: id,
        viagemId: this.id
      },
      cssClass: 'large-modal'
    });

    return await modal.present();
  }

  public async abastecimento(){
    const modal = await this.modalCtrl.create({
      component: AddAbastecimentoComponent,
      componentProps: {
        viagemId: this.id,
      },
      cssClass: 'large-modal'
    });

    return await modal.present();
  }

  public async abastecimentoEdit(id){
    const modal = await this.modalCtrl.create({
      component: EditAbastecimentoComponent,
      componentProps: {
        abastecimentoId: id,
        viagemId: this.id
      },
      cssClass: 'large-modal'
    });

    return await modal.present();
  }

  public async pedagio(){
    const modal = await this.modalCtrl.create({
      component: AddPedagioComponent,
      componentProps: {
        viagemId: this.id,
      },
      cssClass: 'large-modal'
    });

    return await modal.present();
  }

  public async pedagioEdit(id){
    const modal = await this.modalCtrl.create({
      component: EditPedagioComponent,
      componentProps: {
        pedagioId: id,
        viagemId: this.id
      },
      cssClass: 'large-modal'
    });

    return await modal.present();
  }

  public edit(x){
    const data: any = {};
    data.id =  Number(this.id);
    data.dataInical = '2022-08-23T16:55:40.946Z';
    data.motoristaId = this.motoristaId;
    data.transportadoraId = this.storageService.getTransportadora();
    data.veiculoId = this.veiculoId;
    data.destino = this.f.destino.value;
    data.origem = this.f.origem.value;
    data.estadoViagem = x;
    data.kmIncial =  Number(this.f.kmIncial.value);
    data.kmFinal =  Number(this.f.kmFinal.value);
    data.pesoTonelada =  Number(this.f.pesoTonelada.value);
    data.valorFrete =  parseFloat(this.f.valorFrete.value);
    console.log(data);
    this.viagemService.update(data).subscribe(
      response => {
        this.router.navigate(['/home']);
      },
      error => {
        console.log(error);
        this.dialog = true;
        this.error = 'Não foi possivel concluir operação!';
      }
    );
  }

  public async deleteDespesa(gerente) {
    const alert = await this.alertController.create({
      message: `Você irá excluir a despesa <strong>${gerente.descricao}</strong>. Tem certeza?`,
      buttons: [
        {
          text: 'Cancelar',
          role: 'cancel',
          cssClass: 'btn-cancel'
        }, {
          text: 'Excluir',
          cssClass: 'btn-delete',
          handler: () => {
            this.despesaService.delete(gerente.id).subscribe(
              dados => {
                this.findByLists();
                this.displayToast('Despesa removida com sucesso!');
              },
              (error) => {
                let message = 'Não foi possível remover esta despesa.';
                message += '\n' + error.error.error.message;
                this.displayToast(message);
              }
            );
          }
        }
      ]
    });
    await alert.present();
  }

  public async deleteAbastecimento(gerente) {
    const alert = await this.alertController.create({
      message: `Você irá excluir a abastecida de <strong>${gerente.tipoAbastecimento}</strong>. Tem certeza?`,
      buttons: [
        {
          text: 'Cancelar',
          role: 'cancel',
          cssClass: 'btn-cancel'
        }, {
          text: 'Excluir',
          cssClass: 'btn-delete',
          handler: () => {
            this.abastecimentoService.delete(gerente.id).subscribe(
              dados => {
                this.findByLists();
                this.displayToast('Abastecimento removido com sucesso!');
              },
              (error) => {
                let message = 'Não foi possível remover este abastecimento.';
                message += '\n' + error.error.error.message;
                this.displayToast(message);
              }
            );
          }
        }
      ]
    });
    await alert.present();
  }

  public async deletePedagio(gerente) {
    const alert = await this.alertController.create({
      message: `Você irá excluir o pedágio <strong>${gerente.descricao}</strong>. Tem certeza?`,
      buttons: [
        {
          text: 'Cancelar',
          role: 'cancel',
          cssClass: 'btn-cancel'
        }, {
          text: 'Excluir',
          cssClass: 'btn-delete',
          handler: () => {
            this.pedagioService.delete(gerente.id).subscribe(
              dados => {
                this.findByLists();
                this.displayToast('Pedágio removido com sucesso!');
              },
              (error) => {
                let message = 'Não foi possível remover este pedágio.';
                message += '\n' + error.error.error.message;
                this.displayToast(message);
              }
            );
          }
        }
      ]
    });
    await alert.present();
  }

  private async displayToast(message) {
    const toast = await this.toastController.create({
      message,
      duration: 2000,
      buttons: [
        {
          text: 'Ok',
          role: 'cancel',
        }
      ],
      color: 'primary'
    });

    return await toast.present();
  }

}
