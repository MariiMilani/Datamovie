import {Component} from '@angular/core';
import {Header} from '../../../components/logged/header/header';
import {Card} from '../../../components/logged/card/card';
import {Router, RouterLink} from '@angular/router';
import {Movie, Streaming} from '../../../interfaces/movie.interface';
import {AuthService} from '../../../services/auth.service';
import {MovieService} from '../../../services/movie.service';

@Component({
  selector: 'app-login',
  imports: [
    Header,
    Card,
    RouterLink,

  ],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {

  constructor(
    private authService: AuthService,
    private router: Router,
    private movieService: MovieService,
  ) {
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  movies: Movie[] = [];

  ngOnInit() {
    this.loadMovies();
  }

  loadMovies() {
    this.movieService.getAllMovies().subscribe({
      next: (movies) => {
        this.movies = movies;
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  getApprovalRating(rating: number | null): number {
    if (rating != null) {
      return Math.round(rating * 10);
    }
    return 0;
  }

  getTop10(rating: number | null): boolean {
    if (rating != null) {
      if (rating >= 8.5) {
        return true;
      }
    }
    return false;
  }

  getLogo(streaming: Streaming[]): string {
    if (streaming.length > 0) {
      if (streaming[0].name === "Netflix") {
        return 'images/streaming/netflix.png';
      } else if (streaming[0].name == "apple") {
        return 'images/streaming/apple.png';
      }
    }
    return 'images/streaming/prime-video.png';
  }
}
