package com.ftn.isa.auth;

import com.ftn.isa.config.JwtService;
import com.ftn.isa.enums.Role;
import com.ftn.isa.model.Address;
import com.ftn.isa.model.BaseUser;
import com.ftn.isa.model.RegisteredUser;
import com.ftn.isa.repository.AddressRepository;
import com.ftn.isa.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

        private final UserRepository repository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;
        private final AddressRepository addressRepository;
        private final JavaMailSender javaMailSender;

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getUsername(),
                                                request.getPassword()));
                var user = repository.findOneByUsername(request.getUsername())
                                .orElseThrow();
                var jwtToken = jwtService.generateTokenRegisteredUser(user);
                return AuthenticationResponse.builder()
                                .token(jwtToken)
                                .build();
        }

        public AuthenticationResponse register(RegisteredUser request) {
                request.setPassword(passwordEncoder.encode(request.getPassword()));
                Address address = addressRepository.save(request.getAddress());
                request.setAddress(address);
                request.setEnabled(false);
                sendEmail(request);
                repository.save(request);
                var jwtToken = jwtService.generateTokenRegisteredUser(request);
                return AuthenticationResponse.builder()
                                .token(jwtToken)
                                .build();
        }

        public void sendEmail(RegisteredUser request) {
                // Send email to user to activate their account
                String activationToken = UUID.randomUUID().toString();
                activationToken = activationToken.replace("-", "");

                String activationUrl = "http://localhost:8080/users/activate/" + activationToken;

                String content = "Hi " + request.getName() + ", \n\n" +
                                "Please click on the link below to activate your account: \n\n" +
                                activationUrl + "\n\n" +
                                "If you did not create this account, please ignore this email.";

                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(request.getEmail());
                message.setSubject("Activate your account");
                message.setText(content);
                javaMailSender.send(message);

                request.setActivationToken(activationToken);
        }
}