import {Routes} from '@angular/router';
import {Login} from './pages/not-logged/login/login';
import {Home} from './pages/logged/home/home';
import {Registration} from './pages/not-logged/registration/registration';
import {CreateMovie} from './pages/logged/create-movie/create-movie';

export const routes: Routes = [
  {
    path: '',
    component: Home,
  },
  {
    path: 'login',
    component: Login,
  },
  {
    path: 'register',
    component: Registration,
  },
  {
    path: 'create-movie',
    component: CreateMovie
  }
];
