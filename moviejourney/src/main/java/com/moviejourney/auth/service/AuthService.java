package com.moviejourney.auth.service;

import com.moviejourney.auth.dto.LoginRequest;
import com.moviejourney.auth.dto.LoginResponse;
import com.moviejourney.auth.dto.SignupRequest;
import com.moviejourney.auth.entity.User;
import com.moviejourney.auth.repository.UserRepository;
import com.moviejourney.global.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService
{
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;

  public void signup(SignupRequest request)
  {
    if (userRepository.existsByEmail(request.getEmail()))
    {
      throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
    }
    if (userRepository.existsByNickname(request.getNickname()))
    {
      throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
    }

    User user = User.builder()
      .email(request.getEmail())
      .password(passwordEncoder.encode(request.getPassword()))
      .nickname(request.getNickname())
      .build();

    userRepository.save(user);
  }

  public LoginResponse login(LoginRequest request)
  {
    User user = userRepository.findByEmail(request.getEmail())
      .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다."));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
    {
      throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
    }

    String token = jwtUtil.generateToken(request.getEmail());

    return new LoginResponse(token);
  }
}
