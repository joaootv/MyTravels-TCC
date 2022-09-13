import { TransportadoraService } from './../../services/transportadora.service';
import { Component, OnInit } from '@angular/core';
import { format, parseISO } from 'date-fns';

@Component({
  selector: 'app-transportadora',
  templateUrl: './transportadora.page.html',
  styleUrls: ['./transportadora.page.scss'],
})
export class TransportadoraPage implements OnInit {

  public transportadora;

  constructor(
    private transportadoraService: TransportadoraService
  ) { }

  ngOnInit() {
    this.getTransportadora();
  }

  public getTransportadora() {
    this.transportadoraService.getTransportadora()
      .subscribe(
        data => {
          console.log(data);
          data.vencimentoRNTRC = format(parseISO(data.vencimentoRNTRC), 'dd/MM/yyyy');
          this.transportadora = data;
        }
      );
  }

}
