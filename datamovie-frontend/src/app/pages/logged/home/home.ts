import { Component } from '@angular/core';
import {Header} from '../../../components/logged/header/header';
import {Card} from '../../../components/logged/card/card';
import {RouterLink} from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [
    Header,
    Card,
    RouterLink
  ],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {

}
