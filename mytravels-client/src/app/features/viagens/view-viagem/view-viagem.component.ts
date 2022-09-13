import { StorageService } from './../../../services/storage/storage.service';
import { CidadesService } from 'src/app/services/cidades/cidades.service';
import { PedagioService } from './../../../services/pedagio/pedagio.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ViagensService } from 'src/app/services/viagens/viagens.service';
import { AbastecimentoService } from 'src/app/services/abastecimento/abastecimento.service';
import { DespesaService } from 'src/app/services/despesa/despesa.service';
import { format, parseISO } from 'date-fns';

@Component({
  selector: 'app-view-viagem',
  templateUrl: './view-viagem.component.html',
  styleUrls: ['./view-viagem.component.scss']
})
export class ViewViagemComponent implements OnInit {

  public dataModel!: FormGroup;
  public cidades:any;
  public id:any;
  public abastecimentos: any;
  public despesas: any;
  public pedagios: any;
  public viagem: any;

  constructor(
    private fb: FormBuilder,
    private viagensService: ViagensService,
    private router: Router,
    private route: ActivatedRoute,
    private abastecimentoService: AbastecimentoService,
    private despesaService: DespesaService,
    private pedagioService: PedagioService,
    private cidadesService: CidadesService,
    private storageService: StorageService
  ) { }

  get f() {
    return this.dataModel.controls;
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
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
    this.findByCidades();
    this.findByViagem();
    this.findByLists();
  }

  public findByViagem() {
    this.viagensService.findByViagem(this.id).subscribe(
      response => {
        console.log(response);
        this.viagem = response;
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

  public findByCidades() {
    this.cidadesService.findAllCidades().subscribe(
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

}
