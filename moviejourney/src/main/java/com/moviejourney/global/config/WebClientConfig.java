package com.moviejourney.global.config;

import java.time.Duration;

import io.netty.channel.ChannelOption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfig
{
  private static final String TMDB_BASE_URL = "https://api.themoviedb.org/3";

  @Bean
  public WebClient tmdbWebClient() {
    HttpClient httpClient = HttpClient.create()
      .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
      .responseTimeout(Duration.ofSeconds(5));

    return WebClient.builder()
      .baseUrl(TMDB_BASE_URL)
      .clientConnector(new ReactorClientHttpConnector(httpClient))
      .build();
  }
}
