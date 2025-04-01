import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment.development';
@Injectable({
  providedIn: 'root'
})
export class HistoryService {

  constructor(private http: HttpClient) { }
  tokenStored = localStorage.getItem('token');
  jwt = this.tokenStored ? JSON.parse(this.tokenStored) : '';
  headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${this.jwt.token}`
  });
  getHistory() {
    return this.http.get(`${environment.api}/history`, { headers: this.headers });
  }
}
