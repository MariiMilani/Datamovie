import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError, map, Observable, of} from 'rxjs';
import {User, UserRegister, UserResponse} from '../interfaces/user.interface';
import {environment} from '../../environments/enviroment.development';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {
  }

  login(user: User): Observable<UserResponse> {
    return this.http.post<UserResponse>(`${this.apiUrl}/datamovie/auth/login`, user);
  }

  register(user: UserRegister): Observable<UserResponse> {
    return this.http.post<UserResponse>(`${this.apiUrl}/datamovie/auth/register`, user);
  }

  validateToken(): Observable<boolean> {
    const token = localStorage.getItem('token');

    if (!token) {
      return of(false);
    }

    const headers = new HttpHeaders().set(`Authorization`, `Bearer ${token}`);

    return this.http.head(`${this.apiUrl}/datamovie/auth/validate`, {headers, observe: 'response'})
      .pipe(
        map(response => response.status === 200),
        catchError(() => of(false))
      );
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isAuthenticated(): boolean {
    return this.getToken() !== null;
  }

  logout(): void {
    localStorage.removeItem('token');
  }
}
