package com.moviejourney.movie.dto;

import java.util.List;

public record MovieDetailResponse(
  Long id,
  String title,
  String overview,
  String status,
  String releaseDate,
  String posterUrl,
  Double voteAverage,
  Integer voteCount,
  Integer runtime,
  List<String> genres
)
{
  private static final String TMDB_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";

  public static MovieDetailResponse from(TmdbMovieDetailResponse response) {
    return new MovieDetailResponse(
      response.id(),
      response.title(),
      response.overview(),
      response.status(),
      response.releaseDate(),
      response.posterPath() != null ? TMDB_IMAGE_BASE_URL + response.posterPath() : "",
      response.voteAverage(),
      response.voteCount(),
      response.runtime(),
      response.genreList() != null
        ? response.genreList().stream()
            .map(TmdbMovieDetailResponse.TmdbGenre::name)
            .toList()
        : List.of()
    );
  }
}
