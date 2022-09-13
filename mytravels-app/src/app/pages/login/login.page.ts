import { CredenciaisDTO } from './../../models/credenciais.dto';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MenuController, ToastController } from '@ionic/angular';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  public loginModel: FormGroup;
  public hide = true;
  public dados = true;
  public response;

  constructor(
    private router: Router,
    public menu: MenuController,
    private fb: FormBuilder,
    public authService: AuthService,
    private toastController: ToastController
  ) { }

  get f() {
    return this.loginModel.controls;
  }

  ionViewWillEnter() {
    this.menu.enable(false);
  }
  ionViewDidLeave() {
    this.menu.enable(true);
  }

  ngOnInit() {
    this.loginModel = this.fb.group({
      username: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
    });
  }

  async login() {
    this.dados = false;
    const cred: CredenciaisDTO = this.loginModel.value;
    await this.authService.authenticate(cred).subscribe(
      data => {
        this.response = data;
        this.authService.successfulLogin(
          this.response.token,
          this.response.username,
          this.response.idUser,
          this.response.name,
          this.response.idTransportadora
        );
        this.router.navigate(['/home']);
      },
      error => {
        this.loginModel.reset();
        this.dados = true;
        if (error.error.message) {
          this.onError(error.error.message);
        } else {
          this.onError('Não foi possivel realizar o login, tente novamente!');
        }
      }
    );
  }

  async onError(message: string) {
    const toast = await this.toastController.create({
      message,
      buttons: [
        {
          text: 'OK',
          role: 'cancel'
        }
      ]
    });

    return await toast.present();
  }

  getErrorMessage() {
    if (this.f.username.hasError('required')) {
      return 'Informe seu email!';
    }

    return this.f.username.hasError('email') ? 'E-mail inválido!' : '';
  }

}
