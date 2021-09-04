package com.clv.jwat.note2p.api;

import com.clv.jwat.note2p.api.request.LoginRequest;
import com.clv.jwat.note2p.api.response.ErrorResponse;
import com.clv.jwat.note2p.entity.AppUser;
import com.clv.jwat.note2p.jwt.JWTProvider;
import com.clv.jwat.note2p.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Collections;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class UserAPI {

    private final IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        log.info("{} login", loginRequest.getEmail());
        AppUser appUser = userService.checkLogin(loginRequest.getEmail(), loginRequest.getPassword());
        if (appUser == null) {
            log.error("{} login fail", loginRequest.getEmail());
            ErrorResponse errorResponse = new ErrorResponse("Incorrect email or password", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(errorResponse);
        } else {
            log.info("{} login success", loginRequest.getEmail());
            return ResponseEntity.ok(getAuthenticated(appUser, response));
        }
    }

    @GetMapping("/token/refresh")
    public ResponseEntity<Object> refreshToken(HttpServletRequest request) {
        log.info("User refresh token");
        Cookie cookie = WebUtils.getCookie(request, "refresh_token");
        if (cookie != null) {
            try {
                String refreshToken = cookie.getValue();
                String userId = JWTProvider.verifyToken(refreshToken);
                AppUser appUser = userService.findById(Long.parseLong(userId), true);
                String accessToken = JWTProvider.getAccessToken(appUser);
                Map<String, String> map = Collections.singletonMap("access_token", accessToken);
                log.info("User {} renew token success", appUser.getEmail());
                return ResponseEntity.ok(map);
            } catch (Exception exception) {
                log.error("Error refresh token: {}", exception.getMessage());
                ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), HttpStatus.FORBIDDEN.value());
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
            }
        } else {
            log.error("Refresh token is missing");
            ErrorResponse errorResponse = new ErrorResponse("Refresh token is missing", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody AppUser appUser, HttpServletResponse response) {
        log.info("User register");
        appUser = userService.register(appUser);
        if (appUser == null) {
            log.error("Email already exists");
            ErrorResponse errorResponse = new ErrorResponse("Email already exists", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(errorResponse);
        } else {
            log.info("User register success");
            return ResponseEntity.ok(getAuthenticated(appUser, response));
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<Object> logOut(HttpServletResponse response) {
        log.info("User logout");
        Cookie cookie = new Cookie("refresh_token", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/api/token/refresh");
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }

    private Map<String, String> getAuthenticated(AppUser appUser, HttpServletResponse response) {
        String accessToken = JWTProvider.getAccessToken(appUser);
        String refreshToken = JWTProvider.getRefreshToken(appUser);
        Map<String, String> map = Collections.singletonMap("access_token", accessToken);
        Cookie cookie = new Cookie("refresh_token", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/api/token/refresh");
        cookie.setMaxAge(Calendar.HOUR * 24 * 7);
        response.addCookie(cookie);
        return map;
    }
}