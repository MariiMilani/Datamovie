package br.com.datamovie.service;

import br.com.datamovie.entity.Movie;
import br.com.datamovie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

   private final MovieRepository movieRepository;

   public Movie saveMovie(Movie movie){
      return movieRepository.save(movie);
   }

   public List<Movie> findAll(){
      return movieRepository.findAll();
   }

   public Optional<Movie> findById (Long id){
      return movieRepository.findById(id);
   }

   public void deleteMovie (Long id){
      movieRepository.deleteById(id);
   }
}
