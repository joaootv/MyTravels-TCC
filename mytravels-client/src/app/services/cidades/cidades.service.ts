import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from 'src/app/config/api.config';
import { StorageService } from '../storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class CidadesService {

  constructor(
    private http: HttpClient,
    private storage: StorageService
  ) { }

  public findAllCidades(): Observable<any> {
    return this.http.get(`${API_CONFIG.baseUrl}/api/cidade/v1`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }

    public findAllEstados(): Observable<any> {
    return this.http.get(`${API_CONFIG.baseUrl}/api/estado/v1`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }
}
