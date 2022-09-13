import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';
import { StorageService } from './storage.service';

@Injectable({
  providedIn: 'root'
})
export class DespesaService {

  constructor(
    private http: HttpClient,
    private storage: StorageService
  ) { }

  public getDespesas(id): Observable<any> {
    return this.http.get(`${API_CONFIG.baseUrl}/api/despesas/viagem/${id}`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }

  public getDespesa(id): Observable<any> {
    return this.http.get(`${API_CONFIG.baseUrl}/api/despesas/${id}`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }

  public create(data: any): Observable<any> {
    return this.http.post(`${API_CONFIG.baseUrl}/api/despesas`, data, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }

  public update(data: any): Observable<any> {
    return this.http.put(`${API_CONFIG.baseUrl}/api/despesas`, data, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }

  public delete(id): Observable<any> {
    return this.http.delete(`${API_CONFIG.baseUrl}/api/despesas/${id}`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }
}
