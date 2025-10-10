import {Component, ViewChild} from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {FormsModule, NgForm} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {UserRegister} from '../../../interfaces/user.interface';
import {AuthService} from '../../../services/auth.service';

@Component({
  selector: 'app-sign-up',
  imports: [
    RouterLink,
    FormsModule,
    CommonModule
  ],
  templateUrl: './sign-up.html',
  styleUrl: './sign-up.css'
})
export class SignUp {
  @ViewChild(`registerForm`) registerForm!: NgForm;

  user: UserRegister = {
    name: ``,
    email: ``,
    password: ``
  };

  errorMessage: string = ``;
  successMessage: string = ``;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  onSubmit() {
    if (!this.user.name || !this.user.email || !this.user.password) {
      return;
    }

    this.authService.register(this.user).subscribe({
      next: () => {
        this.successMessage = `Cadastro realizado com sucesso!`;
        this.errorMessage = ``;

        this.registerForm.resetForm();

        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 2000)
      },
      error: (error) => {
        this.errorMessage = error.error;
        this.successMessage = ``;
      }
    });
  }

  protected readonly length = length;
}
