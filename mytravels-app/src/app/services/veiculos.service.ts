import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';
import { StorageService } from './storage.service';

@Injectable({
  providedIn: 'root'
})
export class VeiculosService {

  constructor(
    private http: HttpClient,
    private storage: StorageService
  ) { }

  public getVeiculos(): Observable<any> {
    return this.http.get(`${API_CONFIG.baseUrl}/api/veiculo/v1/motorista/${this.storage.getIdMotorista()}`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }
}
