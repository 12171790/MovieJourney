package com.moviejourney.movie.controller;

import java.util.List;

import com.moviejourney.global.response.ApiResponse;
import com.moviejourney.movie.dto.MovieDetailResponse;
import com.moviejourney.movie.dto.MovieResponse;
import com.moviejourney.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController
{
  private final MovieService movieService;

  @GetMapping
  public ResponseEntity<ApiResponse<List<MovieResponse>>> searchMovie(
    @RequestParam(required = false) String query,
    @RequestParam(defaultValue = "1") int page)
  {
    List<MovieResponse> movies = query != null && !query.isBlank()
      ? movieService.searchMovies(query, page)
      : movieService.getNowPlayingMovies(page);

    return ResponseEntity.ok(ApiResponse.success(movies));
  }

  @GetMapping("/{tmdbMovieId}")
  public ResponseEntity<ApiResponse<MovieDetailResponse>> getMovieDetail(@PathVariable Long tmdbMovieId) {
    MovieDetailResponse movie = movieService.getMovieDetail(tmdbMovieId);

    return ResponseEntity.ok(ApiResponse.success(movie));
  }
}
