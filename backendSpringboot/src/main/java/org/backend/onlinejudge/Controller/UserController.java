package org.backend.onlinejudge.Controller;

import org.backend.onlinejudge.Config.JwtProvider;
import org.backend.onlinejudge.DTO.UserDTO;
import org.backend.onlinejudge.DTO.UserLogin;
import org.backend.onlinejudge.DTO.UserResponse;
import org.backend.onlinejudge.DTO.UserTokenResponse;
import org.backend.onlinejudge.Entity.User;
import org.backend.onlinejudge.Repository.UserRepository;
import org.backend.onlinejudge.Service.CustomUserServiceImpl;
import org.backend.onlinejudge.Service.UserService;
import org.backend.onlinejudge.Util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@PropertySource("classpath:message.properties")
@RequestMapping("/user")
public class UserController {


    private final UserService us;
    private final UserRepository ur;
    private final JwtProvider jp;
    private final Environment env;

    private final PasswordEncoder pe;

    private final CustomUserServiceImpl cus;

    @Autowired
    public UserController(UserService us, UserRepository ur, JwtProvider jp, Environment env, PasswordEncoder pe, CustomUserServiceImpl cus){
        this.us = us;
        this.ur = ur;
        this.jp = jp;
        this.env = env;
        this.pe = pe;
        this.cus = cus;
    }

    @GetMapping("/status")
        public ResponseEntity<String> status(){
            return new ResponseEntity<>("Site is up and Running",HttpStatus.OK);
        }


    @PostMapping("/signup")
    public ResponseEntity<UserResponse> signUp(@RequestBody UserDTO user) {
        UserDTO userDTO = us.signUp(user);
        String message = userDTO.getUsername() + " " + env.getProperty("UserController.ADD_USER");
        UserResponse userRegistration = new UserResponse(userDTO.getUsername(), message);
        return new ResponseEntity<>(userRegistration, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserTokenResponse> login(@RequestBody UserLogin user) {
        Authentication authentication=authenticate(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jp.generateToken(authentication);
        User user1 = ur.findByEmail(user.getEmail());

        UserTokenResponse authResponse = new UserTokenResponse(Converter.userToUserDto(user1), "Sign in Successful", token);

        return new ResponseEntity<UserTokenResponse>(authResponse, HttpStatus.CREATED);
    }

    private Authentication authenticate(String email, String password){
        UserDetails userDetails= cus.loadUserByUsername(email);
        if (userDetails==null){
            throw new BadCredentialsException("Invalid Username");
        }
        if(!pe.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }



    @GetMapping("/profile/{username}")
    public UserDTO getUserProfile(@PathVariable String username) {

        return us.getUserProfile(username);
    }





}
