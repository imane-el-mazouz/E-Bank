package com.bank.controller;


import com.bank.dto.AuthRequestDTO;
import com.bank.dto.JwtResponseDTO;
import com.bank.dto.UserDto;
import com.bank.model.LoginResponse;
import com.bank.model.User;
import com.bank.service.JwtService;
import com.bank.service.UserAuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private JwtService jwtService;


    @Autowired
    AuthenticationManager authenticationManager;

//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO, HttpServletResponse httpServletResponse) {
//        JwtResponseDTO authenticatedUser  = userAuthService.login(authRequestDTO);
//        String jwtToken = jwtService.generateToken(String.valueOf(authenticatedUser));
//        LoginResponse loginResponse = new LoginResponse();
//        loginResponse.setToken(jwtToken);
//        loginResponse.setExpiresIn(jwtService.getExpirationTime());
//        return ResponseEntity.ok(loginResponse);
//
//    }
@PostMapping("/login")
public ResponseEntity<?> login(@Valid @RequestBody LoginResponse response){
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(response.getName(),response.getPassword()));
    String token= jwtService.generateToken(response.getName());
    return ResponseEntity.ok(token);
}

//        try {
//            JwtResponseDTO jwtResponseDTO = userAuthService.login(authRequestDTO);
//            LoginResponse loginResponse = new LoginResponse();
//            loginResponse.setToken(jwtResponseDTO.getAccessToken());
//            return ResponseEntity.ok(loginResponse);
//        } catch (UsernameNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }





//    @PostMapping("/login")
//    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
//        User authenticatedUser = authenticationService.authenticate(loginUserDto);
//
//        String jwtToken = jwtService.generateToken(authenticatedUser);
//
//        LoginResponse loginResponse = new LoginResponse();
//        loginResponse.setToken(jwtToken);
//        loginResponse.setExpiresIn(jwtService.getExpirationTime());
//
//        return ResponseEntity.ok(loginResponse);
//    }

    @PostMapping("/signup")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto) {
        try {
            var jwtResponseDTO = userAuthService.signUp(userDto);
            return ResponseEntity.ok(jwtResponseDTO);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
