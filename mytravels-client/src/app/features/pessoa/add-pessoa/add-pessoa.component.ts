import { CidadesService } from 'src/app/services/cidades/cidades.service';
import { PessoaService } from './../../../services/pessoa/pessoa.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { StorageService } from 'src/app/services/storage/storage.service';
import { VeiculosService } from 'src/app/services/veiculos/veiculos.service';

@Component({
  selector: 'app-add-pessoa',
  templateUrl: './add-pessoa.component.html',
  styleUrls: ['./add-pessoa.component.scss']
})
export class AddPessoaComponent implements OnInit {

  public dataModel!: FormGroup;
  public idTransportadora:any;
  public cidades:any;
  public estados:any;

  constructor(
    private fb: FormBuilder,
    private pessoaService: PessoaService,
    private storageService: StorageService,
    private router: Router,
    private cidadesService: CidadesService
  ) { }

  get f() {
    return this.dataModel.controls;
  }

  ngOnInit(): void {
    this.idTransportadora = this.storageService.getTransportadora();
    this.dataModel = this.fb.group({
      bairro: ['', [Validators.required]],
      cep: ['', [Validators.required]],
      cidadeId: ['', [Validators.required]],
      complemento: ['', [Validators.required]],
      cpf: ['', [Validators.required]],
      dtNascimento: ['', [Validators.required]],
      email: ['', [Validators.required]],
      logradouro: ['', [Validators.required]],
      mae: ['', [Validators.required]],
      naturalidade: ['', [Validators.required]],
      numero: ['', [Validators.required]],
      nome: ['', [Validators.required]],
      orgaoExpedidor: ['', [Validators.required]],
      pai: ['', [Validators.required]],
      rg: ['', [Validators.required]],
      telefone1: ['', [Validators.required]],
      uf: ['', [Validators.required]]
    });
    this.findByCidades();
    this.findByEstados();
  }

  public findByCidades() {
    this.cidadesService.findAllCidades().subscribe(
      response => {
        this.cidades = response;
      }
    );
  }

  public findByEstados() {
    this.cidadesService.findAllEstados().subscribe(
      response => {
        console.log(response);

        this.estados = response;
      }
    );
  }

  public create() {
    const data: any = this.dataModel.value;
    data.transportadora = this.idTransportadora;
    console.log(data);
      this.pessoaService.create(data).subscribe(
        response => {
          this.router.navigate(['/motoristas/adicionar/'+response.id]);
        },
        error => {
          console.log(error);
          this.dataModel.reset();
        }
      );
  }

}
