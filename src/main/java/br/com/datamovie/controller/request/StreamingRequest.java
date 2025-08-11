package br.com.datamovie.controller.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record StreamingRequest(@NotEmpty(message = "Streaming's name cannot be empty") String name) {
}
