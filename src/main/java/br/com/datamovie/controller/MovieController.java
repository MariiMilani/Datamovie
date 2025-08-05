package br.com.datamovie.controller;

import br.com.datamovie.controller.request.MovieRequest;
import br.com.datamovie.controller.response.MovieResponse;
import br.com.datamovie.entity.Movie;
import br.com.datamovie.mapper.MovieMapper;
import br.com.datamovie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/datamovie/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponse> saveMovie (@RequestBody MovieRequest movieRequest){
        Movie savedMovie = movieService.saveMovie(MovieMapper.toMovie(movieRequest));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MovieMapper.toMovieResponse(savedMovie));
    }

    @GetMapping()
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        List<Movie> movies = movieService.findAll();
        return ResponseEntity.ok(movies.stream()
                .map(MovieMapper::toMovieResponse)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getById(@PathVariable Long id){
        return movieService.findById(id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

