import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ModalController } from '@ionic/angular';
import { format, parseISO } from 'date-fns';
import { EstadoViagem } from 'src/app/models/estadoViagem';
import { AbastecimentoService } from 'src/app/services/abastecimento.service';
import { CidadeService } from 'src/app/services/cidade.service';
import { DespesaService } from 'src/app/services/despesa.service';
import { PedagioService } from 'src/app/services/pedagio.service';
import { ViagensService } from 'src/app/services/viagens.service';

@Component({
  selector: 'app-relatorio-viagem',
  templateUrl: './relatorio-viagem.page.html',
  styleUrls: ['./relatorio-viagem.page.scss'],
})
export class RelatorioViagemPage implements OnInit {

  public dataModel: FormGroup;
  public cidades;
  public id;
  public abastecimentos;
  public despesas;
  public pedagios;

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
    private modalCtrl: ModalController
  ) { }

  get f() {
    return this.dataModel.controls;
  }

  ionViewWillEnter() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.findByViagem();
    this.findByCidades();
    this.findByLists();
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

}
