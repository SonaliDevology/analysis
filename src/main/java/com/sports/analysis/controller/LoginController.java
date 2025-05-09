package com.sports.analysis.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.sports.analysis.utils.GoogleTokenVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    GoogleTokenVerifier tokenVerifier;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        try {
            var payload = tokenVerifier.verifyToken(token);

            // You can use email, sub (user id), name, etc. from payload
            String email = payload.getEmail();

            // Set session, or just return success
            return ResponseEntity.ok(Map.of("status", "success", "email", email));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid token"));
        }
    }
}
