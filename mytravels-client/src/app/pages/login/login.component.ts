import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CredenciaisDTO } from 'src/app/models/credenciais.dto';
import { AuthService } from 'src/app/services/auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public loginModel: FormGroup = this.fb.group({
    username: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required]],
  });;
  public hide = true;
  public dados = true;
  public response: any;

  constructor(
    private router: Router,
    private fb: FormBuilder,
    public authService: AuthService,
  ) { }

  get f() {
    return this.loginModel.controls;
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
        this.router.navigate(['/viagens']);
      },
      error => {
        this.loginModel.reset();
        this.dados = true;
        // if (error.error.message) {
        //   this.onError(error.error.message);
        // } else {
        //   this.onError('Não foi possivel realizar o login, tente novamente!');
        // }
      }
    );
  }


  // getErrorMessage() {
  //   if (this.f.username.hasError('required')) {
  //     return 'Informe seu email!';
  //   }

  //   return this.f.username.hasError('email') ? 'E-mail inválido!' : '';
  // }

}
