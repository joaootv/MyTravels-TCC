import { StorageService } from './../../../services/storage/storage.service';
import { CidadesService } from 'src/app/services/cidades/cidades.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { format, parseISO } from 'date-fns';
import { AbastecimentoService } from 'src/app/services/abastecimento/abastecimento.service';
import { DespesaService } from 'src/app/services/despesa/despesa.service';
import { PedagioService } from 'src/app/services/pedagio/pedagio.service';
import { ViagensService } from 'src/app/services/viagens/viagens.service';

interface EstadoViagem {
  value: number;
  name: string;
}

@Component({
  selector: 'app-edit-viagem',
  templateUrl: './edit-viagem.component.html',
  styleUrls: ['./edit-viagem.component.scss']
})
export class EditViagemComponent implements OnInit {

  public dataModel!: FormGroup;
  public cidades:any;
  public id:any;
  public abastecimentos: any;
  public despesas: any;
  public pedagios: any;
  public viagem: any;
  public statusPagamento: EstadoViagem[] = [
    {value: 1, name: 'PAGO'},
    {value: 2, name: 'ASSINADO'},
  ];

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

  public edit(x:number){
    const data: any = {};
    data.id =  Number(this.id);
    data.dataInical = '2022-08-26T04:24:45.651Z';
    data.motoristaId = this.viagem.motorista.id;
    data.transportadoraId = this.storageService.getTransportadora();
    data.veiculoId = this.viagem.veiculo.id;
    data.destino = this.f['destino'].value;
    data.origem = this.f['origem'].value;
    if (x != 0 ) {
      data.estadoViagem = x;
    } else {
      data.estadoViagem = this.viagem.estadoViagem;
      if (data.estadoViagem = 'ABERTA') {
        data.estadoViagem = 1;
      } else if (data.estadoViagem = 'FINALIZADA') {
        data.estadoViagem = 2;
      } else {
        data.estadoViagem = 3;
      }
    }
    data.kmIncial =  Number(this.f['kmIncial'].value);
    data.kmFinal =  Number(this.f['kmFinal'].value);
    data.pesoTonelada =  Number(this.f['pesoTonelada'].value);
    data.valorFrete =  parseFloat(this.f['valorFrete'].value);
    console.log(data);
    this.viagensService.update(data).subscribe(
      response => {
        this.router.navigate(['/viagens']);
      },
      error => {
        console.log(error);
      }
    );
  }

}
