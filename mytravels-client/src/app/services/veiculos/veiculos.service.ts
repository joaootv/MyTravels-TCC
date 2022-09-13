import { StorageService } from './../storage/storage.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from 'src/app/config/api.config';

@Injectable({
  providedIn: 'root'
})
export class VeiculosService {

  constructor(
    private http: HttpClient,
    private storage: StorageService
  ) { }

  public findAllVeiculos(): Observable<any> {
    const id = this.storage.getTransportadora();
    return this.http.get(`${API_CONFIG.baseUrl}/api/veiculo/v1/trasnportadora/${id}`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }

  public findAllTipoVeiculos(): Observable<any> {
    const id = this.storage.getTransportadora();
    return this.http.get(`${API_CONFIG.baseUrl}/api/tipoVeiculo/v1`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }

  public findByVeiculo(id:number): Observable<any> {
    return this.http.get(`${API_CONFIG.baseUrl}/api/veiculo/v1/${id}`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }

  public create(data: any): Observable<any> {
    return this.http.post(`${API_CONFIG.baseUrl}/api/veiculo/v1`, data, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }

  public update(data: any): Observable<any> {
    return this.http.put(`${API_CONFIG.baseUrl}/api/veiculo/v1`, data, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }

  public delete(id:number): Observable<any> {
    return this.http.delete(`${API_CONFIG.baseUrl}/api/veiculo/v1/${id}`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }
}
