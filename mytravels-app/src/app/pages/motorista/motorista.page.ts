import { MotoristaService } from './../../services/motorista.service';
import { Component, OnInit } from '@angular/core';
import { parseISO, format } from 'date-fns';

@Component({
  selector: 'app-motorista',
  templateUrl: './motorista.page.html',
  styleUrls: ['./motorista.page.scss'],
})
export class MotoristaPage implements OnInit {

  public motorista;

  constructor(
    private motoristaService: MotoristaService
  ) { }

  ngOnInit() {
    this.getMotorista();
  }

  public getMotorista() {
    this.motoristaService.getMotorista()
      .subscribe(
        data => {
          console.log(data);
          data.pessoa.dtNascimento = format(parseISO(data.pessoa.dtNascimento), 'dd/MM/yyyy');
          data.validade = format(parseISO(data.validade), 'dd/MM/yyyy');
          this.motorista = data;
        }
      );
  }

}

