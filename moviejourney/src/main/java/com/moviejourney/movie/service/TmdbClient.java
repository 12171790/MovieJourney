package com.moviejourney.movie.service;

import com.moviejourney.global.config.TmdbProperties;
import com.moviejourney.movie.dto.TmdbMovieDetailResponse;
import com.moviejourney.movie.dto.TmdbMovieListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class TmdbClient
{
  private final WebClient webClient;
  private final TmdbProperties tmdbProperties;

  private static final String LANGUAGE_KO = "ko-KR";
  private final String API_KEY = "api_key";
  private final String LANGUAGE = "language";
  private final String QUERY = "query";
  private final String PAGE = "page";

  public TmdbMovieListResponse searchMovies(String query, int page) {
    return webClient.get()
      .uri(uriBuilder -> uriBuilder
        .path("/search/movie")
        .queryParam(API_KEY, tmdbProperties.apiKey())
        .queryParam(LANGUAGE, LANGUAGE_KO)
        .queryParam(QUERY, query)
        .queryParam(PAGE, page)
        .build())
      .retrieve()
      .bodyToMono(TmdbMovieListResponse.class)
      .block();
  }

  public TmdbMovieListResponse getNowPlayingMovies(int page) {
    return webClient.get()
      .uri(uriBuilder -> uriBuilder
        .path("/movie/now_playing")
        .queryParam(API_KEY, tmdbProperties.apiKey())
        .queryParam(LANGUAGE, LANGUAGE_KO)
        .queryParam(PAGE, page)
        .build())
      .retrieve()
      .bodyToMono(TmdbMovieListResponse.class)
      .block();
  }

  public TmdbMovieDetailResponse getMovieDetail(Long tmdbMovieId) {
    return webClient.get()
      .uri(uriBuilder -> uriBuilder
        .path("/movie/{movieId}")
        .queryParam(API_KEY, tmdbProperties.apiKey())
        .queryParam(LANGUAGE, LANGUAGE_KO)
        .build(tmdbMovieId))
      .retrieve()
      .bodyToMono(TmdbMovieDetailResponse.class)
      .block();
  }
}
