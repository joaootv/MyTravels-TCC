import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from 'src/app/config/api.config';
import { StorageService } from '../storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class ViagensService {
  getViagem(id: any) {
    throw new Error('Method not implemented.');
  }

  constructor(
    private http: HttpClient,
    private storage: StorageService
  ) { }

  public findAllViagens(): Observable<any> {
    const id = this.storage.getTransportadora();
    return this.http.get(`${API_CONFIG.baseUrl}/api/viagens/trasnportadora/${id}`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }

  public findByViagem(id: number): Observable<any> {
    return this.http.get(`${API_CONFIG.baseUrl}/api/viagens/${id}`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }

  public update(data: any): Observable<any> {
    return this.http.put(`${API_CONFIG.baseUrl}/api/viagens`, data, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }

  public delete(id:number): Observable<any> {
    return this.http.delete(`${API_CONFIG.baseUrl}/api/viagens/${id}`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }
}
