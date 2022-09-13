import { STORAGE_KEYS } from './../config/storage_keys.config';
import { LocalUser } from './../models/local_user';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  public getLocalUser() {
    const user = localStorage.getItem(STORAGE_KEYS.localUser);
    if (user == null) {
      return null;
    } else {
      return JSON.parse(user);
    }
  }

  public setLocalUser(obj: LocalUser) {
    if (obj == null) {
      localStorage.removeItem(STORAGE_KEYS.localUser);
    } else {
      localStorage.setItem(STORAGE_KEYS.localUser, JSON.stringify(obj));
    }
  }

  public getId() {
    const user = this.getLocalUser();
    return user.idUser;
  }

  public getNome() {
    const user = this.getLocalUser();
    return user.name;
  }

  public getEmail() {
    const user = this.getLocalUser();
    return user.username;
  }

  public getTransportadora() {
    const user = this.getLocalUser();
    return user.idTransportadora;
  }

  public getToken() {
    const user = this.getLocalUser();
    return user.token;
  }

  public getIdMotorista() {
    const user = localStorage.getItem(STORAGE_KEYS.idMotorista);
    if (user == null) {
      return null;
    } else {
      return JSON.parse(user);
    }
  }

  public setIdMotorista(obj: any) {
    if (obj == null) {
      localStorage.removeItem(STORAGE_KEYS.idMotorista);
    } else {
      localStorage.setItem(STORAGE_KEYS.idMotorista, JSON.stringify(obj));
    }
  }
}
