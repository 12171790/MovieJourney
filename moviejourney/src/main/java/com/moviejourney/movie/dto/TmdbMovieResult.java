package com.moviejourney.movie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TmdbMovieResult (
  Long id,
  String title,
  String overview,
  @JsonProperty("release_date") String releaseDate,
  @JsonProperty("poster_path") String posterPath
) {}
