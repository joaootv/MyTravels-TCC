import { MotoristaService } from './motorista.service';
import { StorageService } from './storage.service';
import { LocalUser } from './../models/local_user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';
import { CredenciaisDTO } from '../models/credenciais.dto';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient,
    public storageService: StorageService,
    private motorisatService: MotoristaService
  ) { }

  public authenticate(creds: CredenciaisDTO): Observable<any>  {
    return this.http.post(`${API_CONFIG.baseUrl}/auth/signin`, creds, {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    });
  }

  public successfulLogin(token: string, username: string, idUser: number, name: string, idTransportadora: number) {
      const user: LocalUser = {
        token: token,
        username: username,
        name: name,
        idTransportadora: idTransportadora,
        idUser: idUser,
    };
    this.storageService.setLocalUser(user);
  }

  public logout() {
    this.storageService.setLocalUser(null);
    this.storageService.setIdMotorista(null);
  }

}
