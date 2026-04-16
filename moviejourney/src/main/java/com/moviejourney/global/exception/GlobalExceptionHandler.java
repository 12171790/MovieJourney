package com.moviejourney.global.exception;

import com.moviejourney.global.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestControllerAdvice
public class GlobalExceptionHandler
{
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ApiResponse<Void>> handleIllegalArgument(IllegalArgumentException ex) {
    return ResponseEntity.badRequest().body(ApiResponse.fail(ex.getMessage()));
  }

  @ExceptionHandler(WebClientResponseException.class)
  public ResponseEntity<ApiResponse<Void>> handleWebClientResponseException(WebClientResponseException ex) {
    return ResponseEntity.status(ex.getStatusCode())
      .body(ApiResponse.fail("TMDb API Exception : " + ex.getMessage()));
  }
}
