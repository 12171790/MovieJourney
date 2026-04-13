package com.moviejourney.auth.repository;

import java.util.Optional;

import com.moviejourney.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
  Optional<User> findByUserId(Long userId);

  Optional<User> findByEmail(String email);

  User save(User user);

  boolean existsByEmail(String email);

  boolean existsByNickname(String nickname);
}
