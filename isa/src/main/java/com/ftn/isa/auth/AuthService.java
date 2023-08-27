package com.ftn.isa.auth;

import com.ftn.isa.config.JwtService;
import com.ftn.isa.enums.Role;
import com.ftn.isa.model.Address;
import com.ftn.isa.model.Administrator;
import com.ftn.isa.model.BaseUser;
import com.ftn.isa.model.BloodCenterAdministrator;
import com.ftn.isa.model.RegisteredUser;
import com.ftn.isa.repository.AddressRepository;
import com.ftn.isa.repository.AdministratorRepository;
import com.ftn.isa.repository.BloodCenterAdministratorRepository;
import com.ftn.isa.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

        @Autowired
        private final UserRepository repository;

        private final AdministratorRepository administratorRepository;
        private final BloodCenterAdministratorRepository bloodCenterAdministratorRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        @Autowired
        private final AuthenticationManager authenticationManager;
        private final AddressRepository addressRepository;
        private final JavaMailSender javaMailSender;

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getUsername(),
                                                request.getPassword()));
                var user = repository.findOneByUsername(request.getUsername())
                                .orElse(null);
                var admin = administratorRepository.findOneByUsername(request.getUsername())
                                .orElse(null);
                var bloodAdmin = bloodCenterAdministratorRepository.findOneByUsername(request.getUsername())
                                .orElse(null);

                if (user == null && admin == null && bloodAdmin == null) {
                        throw new RuntimeException("User not found");
                } else if (user != null) {
                        return generateTokenRegistered(user);
                } else if (bloodAdmin != null) {
                        return generateTokenBloodAdmin(bloodAdmin);
                } else {
                        return generateTokenAdmin(admin);
                }
        }

        public AuthenticationResponse generateTokenRegistered(RegisteredUser user) {
                var jwtToken = jwtService.generateTokenRegisteredUser(user);
                return AuthenticationResponse.builder()
                                .token(jwtToken)
                                .build();
        }

        public AuthenticationResponse generateTokenAdmin(Administrator admin) {
                var jwtToken = jwtService.generateTokenAdmin(admin);
                return AuthenticationResponse.builder()
                                .token(jwtToken)
                                .build();
        }

        public AuthenticationResponse generateTokenBloodAdmin(BloodCenterAdministrator bloodAdmin) {
                var jwtToken = jwtService.generateTokenBloodAdmin(bloodAdmin);
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

        public AuthenticationResponse edit(RegisteredUser request) {
                request.setPassword(passwordEncoder.encode(request.getPassword()));
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
                message.setTo(request.getUsername());
                message.setSubject("Activate your account");
                message.setText(content);
                javaMailSender.send(message);

                request.setActivationToken(activationToken);
        }
}