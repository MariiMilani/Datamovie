package br.com.datamovie.controller.request;

import lombok.Builder;

@Builder
public record StreamingRequest(String name) {
}
