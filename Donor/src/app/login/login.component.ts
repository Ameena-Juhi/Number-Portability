import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { user } from '../user';
import { tokendto } from '../tokendto';

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
    this.authService.login(this.currentuser)
      .subscribe(
        (res:tokendto) => {
          this.authService.setToken(res); 
          this.response = res.token;
          // this.authService.validateToken();// Store the token in local storage
          console.log("token - "+this.response);
          this.response = 'Login successful';
        },
        (error) => {
          this.response = 'Permission denied';
        }
      );
  }

}
