import { UsuariosService } from './../../services/usuarios/usuarios.service';
import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.scss']
})
export class UsuariosComponent implements OnInit , AfterViewInit {

  public courses: any;
  public empresaDataSource = new MatTableDataSource();

  @ViewChild(MatTable) table!: MatTable<any>;
  @ViewChild(MatSort) sort!: MatSort;

  public displayedColumns = ['nome', 'email', 'cpf', 'dtNascimento', 'telefone', 'buttons'];

  public dados: any;

  public empresa: any;

  constructor(
    private usuariosService: UsuariosService,
    ) { }

  ionViewWillEnter() {
    this.findAllUsuarios();
  }

  ngOnInit() {
    this.findAllUsuarios();
   }

  ngAfterViewInit() {
    this.empresaDataSource.sort = this.sort;
  };

  public async findAllUsuarios() {
    await this.usuariosService.findAllUsuarios().subscribe(
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
