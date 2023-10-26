import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { user } from '../user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  currentuser:user = {username:'',password:''};
  response:string='';

  constructor(private authService:AuthService){}

  login() {
    this.authService.getToken(this.currentuser)
      .subscribe(
        (res: string) => {
          this.authService.setToken(res); 
          // this.authService.validateToken();// Store the token in local storage
          console.log(res);
          this.response = 'Login successful';
        },
        (error) => {
          this.response = 'Invalid username/password';
        }
      );
  }

}
