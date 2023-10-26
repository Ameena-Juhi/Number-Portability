import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { user } from './user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

   private baseUrl = 'http://localhost:8071/api/auth'; 

    constructor(private http: HttpClient) { }
  
    register(user: user): Observable<string> {
      return this.http.post<string>(`${this.baseUrl}/register`, user);
    }
  
    getToken(user:user): Observable<string> {
      // const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
      return this.http.post<string>(`${this.baseUrl}/token`, user);
    }
  
    validateToken(): Observable<string> {
      const token = this.getTokenFromLocalStorage();
      if(token) {
        const params = { token: token };
        return this.http.get<string>(`${this.baseUrl}/validate`, { params: params });
      } else {
        // Handle the case when token is not available in local storage
        // You can throw an error or handle it based on your application's logic
        throw new Error('Token not found in local storage');
      }
    }
    
    setToken(token: string): void {
      localStorage.setItem('token', token);
    }
  
    getTokenFromLocalStorage(): string | null {
      return localStorage.getItem('token');
    }
  }
  

