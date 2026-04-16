package com.moviejourney.movie.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TmdbMovieDetailResponse(
  Long id,
  String title,
  String overview,
  String status,
  @JsonProperty("release_date") String releaseDate,
  @JsonProperty("poster_path") String posterPath,
  @JsonProperty("vote_average") Double voteAverage,
  @JsonProperty("vote_count") Integer voteCount,
  Integer runtime,
  List<TmdbGenre> genreList
)
{
  public record TmdbGenre(
    Long id,
    String name
  )
  {
  }
}
