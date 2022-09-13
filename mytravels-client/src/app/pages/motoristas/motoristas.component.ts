import { MotoristasService } from './../../services/motoristas/motoristas.service';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-motoristas',
  templateUrl: './motoristas.component.html',
  styleUrls: ['./motoristas.component.scss']
})
export class MotoristasComponent implements OnInit, AfterViewInit {

  public courses: any;
  public empresaDataSource = new MatTableDataSource();

  @ViewChild(MatTable) table!: MatTable<any>;
  @ViewChild(MatSort) sort!: MatSort;

  public displayedColumns = ['nome', 'cpf', 'dtNascimento', 'telefone', 'validade', 'renavam', 'buttons'];

  public dados: any;

  public empresa: any;

  constructor(
    private motoristasService: MotoristasService,
    ) { }

  ionViewWillEnter() {
    this.findAllMotoristas();
  }

  ngOnInit() {
    this.findAllMotoristas();
   }

  ngAfterViewInit() {
    this.empresaDataSource.sort = this.sort;
  };

  public async findAllMotoristas() {
    this.motoristasService.findAllMotoristas().subscribe(
      data => {
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

}
