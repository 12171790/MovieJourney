package com.moviejourney.movie.dto;

public record MovieResponse(
  Long tmdbMovieId,
  String title,
  String overview,
  String releaseDate,
  String posterUrl
) {
  private static final String TMDB_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";

  public static MovieResponse from(TmdbMovieResult tmdbMovieResult) {
    return new MovieResponse(
      tmdbMovieResult.id(),
      tmdbMovieResult.title(),
      tmdbMovieResult.overview(),
      tmdbMovieResult.releaseDate(),
      tmdbMovieResult.posterPath() != null ? TMDB_IMAGE_BASE_URL + tmdbMovieResult.posterPath() : ""
    );
  }
}
