import {Component} from '@angular/core';
import {Header} from '../../../components/not-logged/header/header';
import {SignUp} from '../../../components/not-logged/sign-up/sign-up';

@Component({
  selector: 'app-registration',
    imports: [
        Header,
        SignUp,
    ],
  templateUrl: './registration.html',
  styleUrl: './registration.css'
})
export class Registration {

}
