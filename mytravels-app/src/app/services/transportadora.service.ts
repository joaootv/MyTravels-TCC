import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';
import { StorageService } from './storage.service';

@Injectable({
  providedIn: 'root'
})
export class TransportadoraService {

  constructor(
    private http: HttpClient,
    private storage: StorageService
  ) { }

  public getTransportadora(): Observable<any> {
    return this.http.get(`${API_CONFIG.baseUrl}/api/transportadora/v1/${this.storage.getTransportadora()}`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }
}
