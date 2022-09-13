import { EditViagemComponent } from './../../features/viagens/edit-viagem/edit-viagem.component';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { format, parseISO } from 'date-fns';
import { ViagensService } from 'src/app/services/viagens/viagens.service';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, AfterViewInit {

  public courses: any;
  public empresaDataSource = new MatTableDataSource();

  @ViewChild(MatTable) table!: MatTable<any>;
  @ViewChild(MatSort) sort!: MatSort;

  public displayedColumns = ['dataInical', 'origem', 'destino', 'motorista', 'valorFrete', 'buttons'];

  public dados: any;

  public empresa: any;

  constructor(
    private viagensService: ViagensService,
    public dialog: MatDialog,
  ) { }

  ionViewWillEnter() {
    this.findAllViagens();
  }

  ngOnInit() {
    this.findAllViagens();
  }

  ngAfterViewInit() {
    this.empresaDataSource.sort = this.sort;
  };

  public async findAllViagens() {
    await this.viagensService.findAllViagens().subscribe(
      data => {
        // data.dataInical = format(parseISO(data.dataInical), 'dd/MM/yyyy hh:mm');
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

  public openDialogEdit(id: number): void {
    const dialog = this.dialog.open(EditViagemComponent, {
      disableClose: true,
      width: '600px',
      data: { idViagem: id }
    });
    dialog.afterClosed().subscribe(result => {
      if (!result.error) {
        this.findAllViagens();
      }
    })
  }


}
