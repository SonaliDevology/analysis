package com.sports.analysis.utils;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.sports.analysis.utils.Constants.AUTH_ISSUER;

@Component
public class GoogleTokenVerifier {
    private GoogleIdTokenVerifier verifier;

    // Your Google OAuth2 Client ID (use the one created in Google Developer Console)
    @Value("${google.client.id}")
    private String googleClientId;


    public GoogleIdToken.Payload verifyToken(String idTokenString) throws Exception {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(),
                GsonFactory.getDefaultInstance())
                .setAudience(java.util.Collections.singletonList(googleClientId))
                .build();

        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken == null) {
            throw new IllegalArgumentException("Invalid ID token.");
        }

        // Optionally, you can check the token's issuer (`iss` claim)
        String issuer = idToken.getPayload().getIssuer();
        if (!AUTH_ISSUER.equals(issuer)) {
            throw new IllegalArgumentException("Invalid token issuer.");
        }

        // If verification passes, return the payload (user information)
        return idToken.getPayload();
    }

}
