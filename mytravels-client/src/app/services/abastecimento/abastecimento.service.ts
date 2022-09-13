import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from 'src/app/config/api.config';
import { StorageService } from '../storage/storage.service';


@Injectable({
  providedIn: 'root'
})
export class AbastecimentoService {

  constructor(
    private http: HttpClient,
    private storage: StorageService
  ) { }

  public getAbastecimentos(id:number): Observable<any> {
    return this.http.get(`${API_CONFIG.baseUrl}/api/abastecimentos/viagem/${id}`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }

  public getAbastecimento(id:number): Observable<any> {
    return this.http.get(`${API_CONFIG.baseUrl}/api/abastecimentos/${id}`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }

  public create(data: any): Observable<any> {
    return this.http.post(`${API_CONFIG.baseUrl}/api/abastecimentos`, data, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }

  public update(data: any): Observable<any> {
    return this.http.put(`${API_CONFIG.baseUrl}/api/abastecimentos`, data, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }

  public delete(id:number): Observable<any> {
    return this.http.delete(`${API_CONFIG.baseUrl}/api/abastecimentos/${id}`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }
}
