package com.clv.jwat.note2p.api;

import com.clv.jwat.note2p.api.response.ErrorResponse;
import com.clv.jwat.note2p.entity.AppUser;
import com.clv.jwat.note2p.jwt.JWTProvider;
import com.clv.jwat.note2p.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class UserAPI {

    private final IUserService userService;

    @GetMapping("/demo")
    public String demo(HttpServletRequest request) {
        return "s";
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("User refresh token");
        Cookie cookie = WebUtils.getCookie(request, "refresh_token");
        if (cookie != null) {
            try {
                String refreshToken = cookie.getValue();
                String userId = JWTProvider.verifyToken(refreshToken);
                if (userId != null) {
                    AppUser appUser = userService.findById(Long.parseLong(userId), true);
                    String accessToken = JWTProvider.getAccessToken(appUser);
                    Map<String, String> map = Collections.singletonMap("access_token", accessToken);
                    log.info("User {} renew token success", appUser.getEmail());
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), map);
                }
            } catch (Exception exception) {
                log.error("Error request: {}", exception.getMessage());
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.setContentType(APPLICATION_JSON_VALUE);
                ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), HttpStatus.FORBIDDEN.value());
                new ObjectMapper().writeValue(response.getOutputStream(), errorResponse);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
