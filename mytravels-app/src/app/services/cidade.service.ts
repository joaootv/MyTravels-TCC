import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StorageService } from './storage.service';
import { API_CONFIG } from '../config/api.config';

@Injectable({
  providedIn: 'root'
})
export class CidadeService {

  constructor(
    private http: HttpClient,
    private storage: StorageService,
  ) { }

  public getCidades(): Observable<any> {
    return this.http.get(`${API_CONFIG.baseUrl}/api/cidade/v1/`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }
}
