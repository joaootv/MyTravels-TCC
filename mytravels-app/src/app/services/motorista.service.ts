import { API_CONFIG } from './../config/api.config';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from './storage.service';

@Injectable({
  providedIn: 'root'
})
export class MotoristaService {

  constructor(
    private http: HttpClient,
    private storage: StorageService,
  ) { }

  public getMotorista(): Observable<any> {
    const pessoa = this.storage.getId();
    return this.http.get(`${API_CONFIG.baseUrl}/api/motorista/v1/pessoa/${pessoa}`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }

}
