package br.com.datamovie.controller.request;

import jakarta.validation.constraints.NotEmpty;

public record CategoryRequest(@NotEmpty(message = "Category's name cannot be empty") String name) {
}
