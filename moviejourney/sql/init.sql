 CREATE TABLE users
 (
     user_id    BIGSERIAL PRIMARY KEY,
     email      VARCHAR(100) NOT NULL UNIQUE,
     password   VARCHAR(300) NOT NULL,
     nickname   VARCHAR(50)  NOT NULL UNIQUE,
     created_at TIMESTAMP    NOT NULL DEFAULT NOW(),
     updated_at TIMESTAMP    NOT NULL DEFAULT NOW()
 );

 CREATE TABLE movies
 (
     movie_id      BIGSERIAL PRIMARY KEY,
     tmdb_movie_id BIGINT       NOT NULL UNIQUE,
     title         VARCHAR(300) NOT NULL,
     overview      VARCHAR(4000),
     release_date  DATE,
     poster_url    VARCHAR(1000)
 );

CREATE TABLE user_movie
(
    user_movie_id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users(user_id),
    movie_id      BIGINT    NOT NULL REFERENCES movies (movie_id),
    rating         NUMERIC(2, 1) CHECK (rating >= 0.0 AND rating <= 5.0),
    review        VARCHAR(4000),
    watched_date  DATE,
    created_date  TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_date  TIMESTAMP NOT NULL DEFAULT NOW(),
    UNIQUE (user_id, movie_id)
);

CREATE INDEX idx_user_movie_user_id ON user_movie(user_id);
CREATE INDEX idx_user_movie_movie_id ON user_movie(movie_id);