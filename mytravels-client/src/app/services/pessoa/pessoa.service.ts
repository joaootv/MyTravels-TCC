import { API_CONFIG } from 'src/app/config/api.config';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { StorageService } from '../storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class PessoaService {

  constructor(
    private http: HttpClient,
    private storage: StorageService
  ) { }

  public findAllPessoas(id: number): Observable<any> {
    return this.http.get(`${API_CONFIG.baseUrl}/api/pedagio/viagem/${id}`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }

  public findByPessoa(id: number): Observable<any> {
    return this.http.get(`${API_CONFIG.baseUrl}/api/pedagio/${id}`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }

  public create(data: any): Observable<any> {
    return this.http.post(`${API_CONFIG.baseUrl}/api/pessoa/v1`, data, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }

  public update(data: any): Observable<any> {
    return this.http.put(`${API_CONFIG.baseUrl}/api/pedagio`, data, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }

  public delete(id: number): Observable<any> {
    return this.http.delete(`${API_CONFIG.baseUrl}/api/pedagio/${id}`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }
}
