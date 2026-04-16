package com.moviejourney.movie.service;

import java.util.List;

import com.moviejourney.movie.dto.MovieDetailResponse;
import com.moviejourney.movie.dto.MovieResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService
{
  private final TmdbClient tmdbClient;

  public List<MovieResponse> searchMovies(String query, int page) {
    return tmdbClient.searchMovies(query, page)
      .movieResultList()
      .stream()
      .map(MovieResponse::from)
      .toList();
  }

  public List<MovieResponse> getNowPlayingMovies(int page) {
    return tmdbClient.getNowPlayingMovies(page)
      .movieResultList()
      .stream()
      .map(MovieResponse::from)
      .toList();
  }

  public MovieDetailResponse getMovieDetail(Long movieId) {
    return MovieDetailResponse.from(tmdbClient.getMovieDetail(movieId));
  }
}
