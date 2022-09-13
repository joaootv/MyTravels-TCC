import { VeiculosService } from './../../services/veiculos/veiculos.service';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-veiculos',
  templateUrl: './veiculos.component.html',
  styleUrls: ['./veiculos.component.scss']
})
export class VeiculosComponent implements OnInit, AfterViewInit {

  public courses: any;
  public empresaDataSource = new MatTableDataSource();

  @ViewChild(MatTable) table!: MatTable<any>;
  @ViewChild(MatSort) sort!: MatSort;

  public displayedColumns = ['placa', 'kilometragem', 'marca', 'modelo', 'anoModelo', 'renavam', 'motorista', 'buttons'];

  public dados: any;

  public empresa: any;

  constructor(
    private veiculosService: VeiculosService,
  ) { }

  ionViewWillEnter() {
    this.findAllVeiculos();
  }

  ngOnInit() {
    this.findAllVeiculos();
  }

  ngAfterViewInit() {
    this.empresaDataSource.sort = this.sort;
  };

  public async findAllVeiculos() {
    await this.veiculosService.findAllVeiculos().subscribe(
      data => {
        console.log(data);
        this.dados = true;
        this.courses = data;
        this.empresaDataSource.data = this.courses;
      }
    );
  }

  public filterTable(value: string) {
    this.empresaDataSource.filter = value.trim().toLocaleLowerCase();
  }

  public trackByGerentesList(index: any, empresa: any) {
    return empresa.id;
  }

  public dellete(veiculo: any) {

    let result = confirm("Deseja excluir o veículo " + veiculo.placa + "?");

    if (result) {
      this.veiculosService.delete(veiculo.id).subscribe(
        data => {
          console.log(data);
          this.findAllVeiculos();
        }
      );
    }
  }

}
