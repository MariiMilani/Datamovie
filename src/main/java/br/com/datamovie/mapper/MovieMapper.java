package br.com.datamovie.mapper;

import br.com.datamovie.controller.request.MovieRequest;
import br.com.datamovie.controller.response.CategoryResponse;
import br.com.datamovie.controller.response.MovieResponse;
import br.com.datamovie.controller.response.StreamingResponse;
import br.com.datamovie.entity.Category;
import br.com.datamovie.entity.Movie;
import br.com.datamovie.entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class MovieMapper {

    public static Movie toMovie(MovieRequest movieRequest){

        List<Category> categories = movieRequest.categories()
                .stream()
                .map(categoryID -> Category.builder().id(categoryID).build())
                .toList();

        List<Streaming> streamings = movieRequest.streaming()
                .stream()
                .map(streamingID -> Streaming.builder().id(streamingID).build())
                .toList();

        return Movie.builder()
                .title(movieRequest.title())
                .description(movieRequest.description())
                .releaseDate(movieRequest.releaseDate())
                .rating(movieRequest.rating())
                .categories(categories)
                .streaming(streamings)
                .build();
    }

    public static MovieResponse toMovieResponse (Movie movie){

        List<CategoryResponse> categories = movie.getCategories()
                .stream()
                .map(category -> CategoryMapper.toCategoryResponse(category))
                .toList();

        List<StreamingResponse> streamings = movie.getStreaming()
                .stream()
                .map(streaming -> StreamingMapper.toStreamingResponse((streaming)))
                .toList();

        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }


}
