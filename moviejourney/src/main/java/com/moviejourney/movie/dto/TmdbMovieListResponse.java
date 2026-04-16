package com.moviejourney.movie.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TmdbMovieListResponse(
  int page,
  @JsonProperty("results") List<TmdbMovieResult> movieResultList,
  @JsonProperty("total_pages") int totalPages,
  @JsonProperty("total_results") int totalResults
)
{
}
