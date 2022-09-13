import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from 'src/app/config/api.config';
import { StorageService } from '../storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  constructor(
    private http: HttpClient,
    private storage: StorageService
  ) { }

  public findAllUsuarios(): Observable<any> {
    const id = this.storage.getTransportadora();
    return this.http.get(`${API_CONFIG.baseUrl}/api/users/trasnportadora/${id}`, {
      headers: new HttpHeaders().set('Authorization', `Bearer ${this.storage.getToken()}`)
    });
  }
}
