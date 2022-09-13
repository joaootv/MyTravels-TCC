import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from 'src/app/config/api.config';
import { StorageService } from '../storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class MotoristasService {

  constructor(
    private http: HttpClient,
    private storage: StorageService
  ) { }

  public findAllMotoristas(): Observable<any> {
    const id = this.storage.getTransportadora();
    return this.http.get(`${API_CONFIG.baseUrl}/api/motorista/v1/trasnportadora/${id}`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }

}
