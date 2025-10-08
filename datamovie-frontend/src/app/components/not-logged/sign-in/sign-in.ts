import { Component } from '@angular/core';
import {RouterLink, Router} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {AuthService} from '../../../services/auth.service';
import {User} from '../../../interfaces/user.interface';

@Component({
  selector: 'app-sign-in',
  imports: [RouterLink, FormsModule],
  templateUrl: './sign-in.html',
  styleUrl: './sign-in.css'
})
export class SignIn {
  user: User = {
    email: '',
    password: '',
  };

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  onSubmit() {
    if(!this.user.email || !this.user.password || this.user.password.length < 1) {
      return;
    }


    this.authService.login(this.user).subscribe({
      next: (response) => {
        localStorage.setItem('token', response.token);
        this.router.navigate(['/']);
      },
      error: (error) => {
        console.log('Erro no login: ', error);
      }
    });
  }
}
