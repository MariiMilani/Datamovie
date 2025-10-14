import {Component, OnInit} from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {Category, Movie, Streaming} from '../../../interfaces/movie.interface';
import {MovieService} from '../../../services/movie.service';
import {FormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-create-movie',
  imports: [
    RouterLink,
    FormsModule,
    CommonModule,
  ],
  templateUrl: './create-movie.html',
  styleUrl: './create-movie.css'
})
export class CreateMovie implements OnInit {
  movie: Omit<Movie, `id`> = {
    title: ``,
    description: ``,
    releaseDate: ``,
    rating: null,
    categories: [],
    streaming: []
  };

  availableCategories: Category[] = [];
  availableStreamings: Streaming[] = [];

  selectedCategoryId: number = 0;
  selectedStreamingId: number = 0;
  errorMessage: string = ``;
  successMessage: string = ``;

  constructor(
    private movieService: MovieService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.loadCategories();
    this.loadStreamings();
  }

  loadCategories() {
    this.movieService.getAllCategories().subscribe({
      next: (categories) => {
        this.availableCategories = categories;
      },
      error: (error) => {
        this.errorMessage = `Categories not loaded. Please, try again later`;
        console.log(error.error);
      }
    });
  }

  loadStreamings() {
    this.movieService.getAllStreamings().subscribe({
      next: (streamings) => {
        this.availableStreamings = streamings;
      },
      error: (error) => {
        this.errorMessage = `Streaming not loaded. Please, try again later.`;
        console.log(error.error);
      }
    });
  }

  onSubmit() {

    if (this.selectedCategoryId) {
      const selectedCategory = this.availableCategories.find(cat => cat.id === this.selectedCategoryId);
      if (selectedCategory) {
        this.movie.categories = [selectedCategory];
      }
    }

    if (this.selectedStreamingId) {
      const selectedStreaming = this.availableStreamings.find(str => str.id === this.selectedStreamingId);
      if (selectedStreaming) {
        this.movie.streaming = [selectedStreaming];
      }
    }

    this.movieService.createMovie(this.movie).subscribe({
      next: () => {
        this.successMessage = `Movie added successfully.`;
        setTimeout(() => {
          this.router.navigate(['/']);
        }, 2000)
      },
      error: (error) => {
        this.errorMessage = `Error! Movie not created, try again later.`;
        console.log(error.error);
      }
    })
  }
}
