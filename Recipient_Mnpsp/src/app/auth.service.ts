
import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs';

import { Router } from '@angular/router';
import { user } from './user';
import { tokendto } from './tokendto';

@Injectable({

    providedIn: 'root',

})

export class AuthService {

    private apiUrl = 'http://localhost:8071/api/auth/token';

    user: any;

    constructor(private http: HttpClient, private router: Router) { }

    login(user: user): Observable<tokendto> {

        return this.http.post<tokendto>(`${this.apiUrl}`, user);

    }

    setToken(tokenDto: tokendto): void {
        localStorage.setItem('token', tokenDto.token);
    }

    getToken(): string | null {
        return localStorage.getItem('token'); 
    }
 

    // isUserAdmin(): boolean {

    //     const token = this.getToken();

    //     if (token) {

    //       const decodedToken: any = jwt_decode(token);

    //       const userRole = decodedToken.role;

    //       return userRole === 'ADMIN';

    //     }

    //     return false;

    //   }

 

    // getUserName(): string {

    //     const token = this.getToken();

    //     if (token) {

    //       const decodedToken: any = jwt_decode(token);

    //       const userName = decodedToken.sub;

    //       return userName;

    //     }

    //     return '';

    //   }  

 

    get isLoggedIn(): boolean {

        let authToken = localStorage.getItem('token');

        return authToken !== null ? true : false;

    }

 

    logout(): void {

        //this.authToken = null; // Clear the authToken property

        localStorage.removeItem('token');

        this.router.navigate(['login']);

    }

 

    isAuthenticated(): boolean {

        let authToken = localStorage.getItem('token');

        return authToken !== null ? true : false;

      }

}

 

// import { Injectable } from '@angular/core';
// import { HttpClient, HttpHeaders } from '@angular/common/http';
// import { Observable } from 'rxjs';
// import { user } from './user';

// @Injectable({
//   providedIn: 'root'
// })
// export class AuthService {

//    private baseUrl = 'http://localhost:8071/api/auth'; 

//     constructor(private http: HttpClient) { }
  
//     register(user: user): Observable<any> {
//       return this.http.post(`${this.baseUrl}/register`, user);
//     }
  
//     getToken(user:user): Observable<string> {
//       // const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
//       return this.http.post<string>(`${this.baseUrl}/token`, user);
//     }
  
//     validateToken(): Observable<string> {
//       const token = this.getTokenFromLocalStorage();
//       if(token) {
//         const params = { token: token };
//         return this.http.get<string>(`${this.baseUrl}/validate`, { params: params });
//       } else {
//         // Handle the case when token is not available in local storage
//         // You can throw an error or handle it based on your application's logic
//         throw new Error('Token not found in local storage');
//       }
//     }

//     setToken(token: string): void {
//       localStorage.setItem('token', token);
//     }
  
//     getTokenFromLocalStorage(): string | null {
//       return localStorage.getItem('token');
//     }
//   }
  

