import {ChangeDetectionStrategy, Component, inject} from '@angular/core';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {
  MatDialog,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogTitle,
} from '@angular/material/dialog';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatCardModule,
    MatButtonModule,
    FormsModule,
  ],
  standalone: true,
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginData = {
    email: '',
    password: ''
  };

  constructor(
    private authService: AuthService,
    private router: Router,
  ) {}
  readonly dialog = inject(MatDialog);

  openDialog() {
    this.dialog.open(Dialog);
  }

  onLogin(): void {
    this.authService.login(this.loginData.email, this.loginData.password).subscribe(
      response => {
        //seteamos el token en el local storage
        localStorage.setItem('token', JSON.stringify(response));
        //decodificamos el token y guardamos los permisos en memoria
        this.authService.getPermissions();
        this.router.navigate(['/inventory']);
      },error => {
        this.openDialog();
      });
    }
}
@Component({
  selector: 'dialog-elements-example-dialog',
  template: `
    <h2 mat-dialog-title>Usuario o contraseña incorrecto</h2>
    <mat-dialog-content>Revise sus credenciales y vuelva a intentarlo.</mat-dialog-content>
    <mat-dialog-actions>
      <button mat-button mat-dialog-close>Cerrar</button>
    </mat-dialog-actions>
  `,
  imports: [MatDialogTitle, MatDialogContent, MatDialogActions, MatDialogClose, MatButtonModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class Dialog {}
