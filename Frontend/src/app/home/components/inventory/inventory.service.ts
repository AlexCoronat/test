import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class InventoryService {
  api: string = environment.api + '/products';
  tokenStored = localStorage.getItem('token');
  jwt = this.tokenStored ? JSON.parse(this.tokenStored) : '';
  headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${this.jwt.token}`
  });
  constructor(
    private http: HttpClient
  ) { }
  getInventory() {
    return this.http.get(`${this.api}`, { headers: this.headers });
  }
  getInventoryById(id: string) {
    return this.http.get(`${this.api}/${id}`, { headers: this.headers });
  }
  createProduct(data: any) {
    return this.http.post(`${this.api}/new`, data, { headers: this.headers });
  }
  updateProduct(id: string, data: any) {
    return this.http.patch(`${this.api}/${id}`, data, { headers: this.headers });
  }
}
