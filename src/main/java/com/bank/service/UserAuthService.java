package com.bank.service;

import com.bank.dto.AuthRequestDTO;
import com.bank.dto.JwtResponseDTO;
import com.bank.dto.UserDto;
import com.bank.model.User;
import com.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @Lazy
    @Autowired
    private JwtService jwtService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                .roles("USER")
                .build();
    }

    public JwtResponseDTO login(AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getName(), authRequestDTO.getPassword())
        );

        if (authentication.isAuthenticated()) {
            User user = userRepository.findByName(authRequestDTO.getName());
            String token = jwtService.generateToken(authRequestDTO.getName());

            UserDto userDto = UserDto.builder()
                    .idU(user.getIdU())
                    .name(user.getName())
                    .email(user.getEmail())
                    .profession(user.getProfession())
                    .phone(user.getPhone())
                    .build();

            return JwtResponseDTO.builder()
                    .accessToken(token)
                    .user(userDto)
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid user request.");
        }
    }

    public JwtResponseDTO signUp(UserDto userRequest) {
        if (userRequest.getName() == null) {
            throw new RuntimeException("Parameter username is not found in request.");
        } else if (userRequest.getPassword() == null) {
            throw new RuntimeException("Parameter password is not found in request.");
        }

        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setProfession(userRequest.getProfession());
        user.setPhone(userRequest.getPhone());

        return JwtResponseDTO.builder()
                .accessToken(jwtService.generateToken(userRepository.save(user).getName()))
                .user(userRequest)
                .build();
    }
}
