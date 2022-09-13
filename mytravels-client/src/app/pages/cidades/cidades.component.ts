import { MatSort } from '@angular/material/sort';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { CidadesService } from 'src/app/services/cidades/cidades.service';

@Component({
  selector: 'app-cidades',
  templateUrl: './cidades.component.html',
  styleUrls: ['./cidades.component.scss']
})
export class CidadesComponent implements OnInit , AfterViewInit {

  public courses: any;
  public empresaDataSource = new MatTableDataSource();

  @ViewChild(MatTable) table!: MatTable<any>;
  @ViewChild(MatSort) sort!: MatSort;

  public displayedColumns = ['nome', 'estado'];

  public dados: any;

  public empresa: any;

  constructor(
    private cidadesService: CidadesService,
    ) { }

  ionViewWillEnter() {
    this.findAllCidades();
  }

  ngOnInit() {
    this.findAllCidades();
   }

  ngAfterViewInit() {
    this.empresaDataSource.sort = this.sort;
  };

  public async findAllCidades() {
    await this.cidadesService.findAllCidades().subscribe(
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

}
