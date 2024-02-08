package org.backend.onlinejudge.Service;

import lombok.RequiredArgsConstructor;
import org.backend.onlinejudge.DTO.UserDTO;
import org.backend.onlinejudge.Entity.User;
import org.backend.onlinejudge.Exception.UserAlreadyExistsException;
import org.backend.onlinejudge.Repository.UserRepository;
import org.backend.onlinejudge.Util.Converter;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDateTime;
import java.util.Optional;



@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SequenceGeneratorService sgs;

    @Override
    public UserDTO signUp(UserDTO userDTO) {

        // Check if the username or email is already in use
        Optional<User> userOptional = userRepository.findUserByUsername(userDTO.getUsername());
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException("Username already exists");
        }
        User user1= userRepository.findByEmail(userDTO.getEmail());
        if (user1!=null) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        User user = Converter.userDtoToUser(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Encrypt the password before storing in the database
//        user.setPassword((userDTO.getPassword()));
        user.setUserId("U" + sgs.generateSequence(User.getSequenceName()));
        user.setCreatedDate(LocalDateTime.now());
        user = userRepository.save(user);
        return Converter.userToUserDto(user);
    }



//    @Override
//    public UserTokenResponse login(String usernameOrEmail, String password) {
//        Authentication authentication=authenticate(loginRequest.getEmail(), loginRequest.getPassword());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String token = jwtProvider.generateToken(authentication);
//
//        UserTokenResponse authResponse = new UserTokenResponse();
//        authResponse.setToken(token);
//        authResponse.setMessage("Sign in Successful");
//    }






    @Override
    public String logout(String token) {
        // Implement your logout logic here
        return "User logged out successfully";
    }

    @Override
    public UserDTO getUserProfile(String username) {
        // Implement your get user profile logic here
        return new UserDTO();
    }
}
