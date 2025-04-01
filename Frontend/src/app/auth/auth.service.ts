import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginRequest } from './login/login.request';
import { environment } from '../../environments/environment.development';
import { jwtDecode } from 'jwt-decode';
interface tokenInformation{
  sub: string;
  email: string;
  permissions: string[];
  iat: number;
  exp: number;
}
@Injectable({
  providedIn: 'root'
})
export class AuthService {
   objectDecoded = {
      sub: '',
      email: '',
      permissions: [],
      iat: 0,
      exp: 0
    };
  constructor(private http: HttpClient) { }

  getPermissions(): string[] {
    const token = localStorage.getItem('token') || '';
    const decoded: tokenInformation = token != '' ? jwtDecode(token) : this.objectDecoded;
    return  decoded.permissions || [];
  }
  logOut() {
    localStorage.removeItem('token');
    this.getPermissions();
  }

  login(email: string, password: string): Observable<LoginRequest> {
    return this.http.post<LoginRequest>(`${environment.api}/auth/login`, { email, password });
  }
}
