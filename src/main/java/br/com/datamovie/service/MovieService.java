package br.com.datamovie.service;

import br.com.datamovie.entity.Category;
import br.com.datamovie.entity.Movie;
import br.com.datamovie.entity.Streaming;
import br.com.datamovie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

   private final MovieRepository movieRepository;
   private final CategoryService categoryService;
   private final StreamingService streamingService;

   public Movie saveMovie(Movie movie){
      movie.setCategories(this.findCategories(movie.getCategories()));
      movie.setStreaming(this.findStreamings(movie.getStreaming()));
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

   public Optional<Movie> updateMovie (Long id, Movie movie){
      Optional<Movie> movieId = movieRepository.findById(id);

      if(movieId.isPresent()){
         List<Category> categories = this.findCategories(movie.getCategories());
         List<Streaming> streamings = this.findStreamings(movie.getStreaming());

         Movie movieUpdated = movieId.get();
         movieUpdated.setTitle(movie.getTitle());
         movieUpdated.setDescription(movie.getDescription());
         movieUpdated.setReleaseDate(movie.getReleaseDate());
         movieUpdated.setRating(movie.getRating());

         movieUpdated.getCategories().clear();
         movieUpdated.getCategories().addAll(categories);

         movieUpdated.getStreaming().clear();
         movieUpdated.getStreaming().addAll(streamings);

         movieRepository.save(movieUpdated);

         return Optional.of(movieUpdated);
      }
      return Optional.empty();
   }

   public List<Movie> findByCategory (Long categoryId){
      return movieRepository.findMovieByCategories(List.of(Category.builder().id(categoryId).build()));
   }

   private List<Category> findCategories(List<Category> categories){
      List<Category> categoriesFound = new ArrayList<>();
      categories.forEach(category -> {
         categoryService.findById(category.getId()).ifPresent(categoriesFound::add);
      });
      return categoriesFound;
   }

   private List<Streaming> findStreamings(List<Streaming> streamings){
      List<Streaming> streamingsFound = new ArrayList<>();
      streamings.forEach(streaming -> {
         streamingService.findById(streaming.getId()).ifPresent(streamingsFound::add);
      });
      return streamingsFound;
   }
}
