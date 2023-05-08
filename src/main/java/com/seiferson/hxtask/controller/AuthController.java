package com.seiferson.hxtask.controller;

import com.seiferson.hxtask.config.security.CustomUserDetails;
import com.seiferson.hxtask.model.security.Account;
import com.seiferson.hxtask.repository.security.AccountRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Value("${com.seiferson.hxtask.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("${com.seiferson.hxtask.jwtSecret}")
    private String jwtSecret;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/token")
    public ResponseEntity<?> authenticateUser(@RequestBody HashMap<String, String> credentials) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.get("username"), credentials.get("password")));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();

        String jwt = Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

        List<String> roles = userPrincipal.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        HashMap<String, String> response = new HashMap<>();
        response.put("token", jwt);
        response.put("username", userPrincipal.getUsername());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody HashMap<String, String> request) {
        HashMap<String, String> response = new HashMap<>();

        if (accountRepo.existsByNickname(request.get("username"))) {
            response.put("message", "Error: Username is already taken!");
            return ResponseEntity
                    .badRequest()
                    .body(response);
        }

        if (accountRepo.existsByEmail(request.get("email"))) {
            response.put("message", "Error: email is already in use!");
            return ResponseEntity
                    .badRequest()
                    .body(response);
        }

        // Create new user's account
        Account account = new Account();
        account.setEmail(request.get("email"));
        account.setPassword(encoder.encode(request.get("password")));
        account.setEnabled(true);
        account.setNickname(request.get("username"));

        List<String> roles = new ArrayList<>();
        roles.add("USER");

        account.setRoles(roles);

        accountRepo.insert(account);

        response.put("message", "Success");
        return ResponseEntity.ok(response);
    }

}
