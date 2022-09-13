import { ViagemNewDTO } from './../models/viagem';
import { MotoristaService } from './motorista.service';
import { StorageService } from './storage.service';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';

@Injectable({
  providedIn: 'root'
})
export class ViagensService {

  constructor(
    private http: HttpClient,
    private storage: StorageService
  ) { }

  public getViagens(id): Observable<any> {
    return this.http.get(`${API_CONFIG.baseUrl}/api/viagens/motorista/${id}`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }

  public getViagem(id): Observable<any> {
    return this.http.get(`${API_CONFIG.baseUrl}/api/viagens/${id}`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }

  public create(data: ViagemNewDTO): Observable<any> {
    return this.http.post(`${API_CONFIG.baseUrl}/api/viagens`, data, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }

  public update(data: any): Observable<any> {
    return this.http.put(`${API_CONFIG.baseUrl}/api/viagens`, data, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }

}
