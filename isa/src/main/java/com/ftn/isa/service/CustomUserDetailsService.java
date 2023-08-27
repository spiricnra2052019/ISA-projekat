package com.ftn.isa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ftn.isa.repository.AdministratorRepository;
import com.ftn.isa.repository.BloodCenterAdministratorRepository;
import com.ftn.isa.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private BloodCenterAdministratorRepository bloodCenterAdministratorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findOneByUsername(username).orElse(null);
        var admin = administratorRepository.findOneByUsername(username).orElse(null);
        var bloodAdmin = bloodCenterAdministratorRepository.findOneByUsername(username).orElse(null);

        if (user == null && admin == null && bloodAdmin == null) {
            throw new RuntimeException("User not found");
        } else if (user != null) {
            return user;
        } else if (bloodAdmin != null) {
            return bloodAdmin;
        } else {
            return admin;
        }
    }

}