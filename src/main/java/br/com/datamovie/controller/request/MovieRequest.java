package br.com.datamovie.controller.request;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.List;

public record MovieRequest(@NotEmpty(message = "Movie's title cannot be empty") String title,
                           String description,
                           LocalDate releaseDate,
                           double rating,
                           List<Long> categories,
                           List<Long> streaming) {
}
