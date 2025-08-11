package br.com.datamovie.controller;

import br.com.datamovie.controller.request.MovieRequest;
import br.com.datamovie.controller.response.MovieResponse;
import br.com.datamovie.entity.Movie;
import br.com.datamovie.mapper.MovieMapper;
import br.com.datamovie.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/datamovie/movie")
@RequiredArgsConstructor
@Tag(name = "Movie", description = "Recurso responsável pelo gerenciamento dos filmes")
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    @Operation(summary = "Salvar filme", description = "Método responsável por salvar um novo filme no banco de dados",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Retorna filme salvo",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    public ResponseEntity<MovieResponse> saveMovie (@Valid @RequestBody MovieRequest movieRequest){
        Movie savedMovie = movieService.saveMovie(MovieMapper.toMovie(movieRequest));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(MovieMapper.toMovieResponse(savedMovie));
    }

    @GetMapping
    @Operation(summary = "Buscar filmes", description = "Método responsável por retornar todos os filmes do banco de dados",
        security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna todos os filmes cadastrados",
        content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
    public ResponseEntity<List<MovieResponse>> getAllMovies() {
        List<Movie> movies = movieService.findAll();
        return ResponseEntity.ok(movies.stream()
                .map(MovieMapper::toMovieResponse)
                .toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar filmes por id", description = "Método responsável por retornar filme pelo id selecionado",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna filme pelo id selecionado",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404", description = "Filme não localizado", content = @Content())
    public ResponseEntity<MovieResponse> getById(@PathVariable Long id){
        return movieService.findById(id)
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar filmes por id", description = "Método responsável por deletar filme pelo id selecionado",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "Não há retorno, o filme foi deletado", content = @Content())
    @ApiResponse(responseCode = "404", description = "Filme não localizado", content = @Content())
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id){
        Optional<Movie> movieId = movieService.findById(id);
        if(movieId.isPresent()){
            movieService.deleteMovie(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Alterar filmes por id", description = "Método responsável por alterar o filme pelo id selecionado",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna filme alterado",
            content = @Content(schema = @Schema(implementation = MovieResponse.class)))
    @ApiResponse(responseCode = "404", description = "Filme não localizado", content = @Content())
    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> updateMovie (@PathVariable Long id, @Valid @RequestBody MovieRequest movieRequest){
        return movieService.updateMovie(id, MovieMapper.toMovie(movieRequest))
                .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar filmes por categoria", description = "Método responsável por retornar filmes pela categoria selecionada",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Retorna filmes pela categoria selecionada",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieResponse.class))))
    @ApiResponse(responseCode = "404", description = "Filme não localizado", content = @Content())
    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long categoryId){
        return ResponseEntity.ok(movieService.findByCategory(categoryId)
                .stream()
                .map(MovieMapper::toMovieResponse)
                .toList());
    }
}

