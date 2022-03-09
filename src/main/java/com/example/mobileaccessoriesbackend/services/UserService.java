package com.example.mobileaccessoriesbackend.services;


import com.example.mobileaccessoriesbackend.dto.request.UserRequest;
import com.example.mobileaccessoriesbackend.dto.response.AuthResponse;
import com.example.mobileaccessoriesbackend.dto.response.StandardResponse;
import com.example.mobileaccessoriesbackend.dto.response.UserResponse;
import com.example.mobileaccessoriesbackend.entity.Supplier;
import com.example.mobileaccessoriesbackend.entity.User;
import com.example.mobileaccessoriesbackend.enums.UserType;
import com.example.mobileaccessoriesbackend.exceptions.ResourceNotFoundException;
import com.example.mobileaccessoriesbackend.jwt.AuthenticationRequest;
import com.example.mobileaccessoriesbackend.jwt.JwtGenerator;
import com.example.mobileaccessoriesbackend.repository.UserRepository;
import com.example.mobileaccessoriesbackend.services.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserService implements IUserService {

    /**
     * User Repository
     */
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Add User
     * @param userRequest
     * @return
     */
    @Override
    public UserResponse addUser(UserRequest userRequest) {
        User user = new User();

        user.setUsername(userRequest.getUsername());
        user.setUserId(UUID.randomUUID().toString());
        user.setContactNumber(userRequest.getContactNumber());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setUserType(userRequest.getUserType());
        user.setEmail(userRequest.getEmail());

        User response = userRepository.save(user);

        return new UserResponse(
                response.getId(),
                response.getUsername(),
                response.getEmail(),
                response.getContactNumber(),
                response.getEmployeeId());
    }

    /**
     * Get All Users
     * @return
     */
    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::response).collect(Collectors.toList());
    }

    /**
     * Get User By Identifier
     * @param id
     * @return
     */
    @Override
    public User getUserById(Long id) {
        return  userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not exist with id : "+ id));

    }

    /**
     * Update User Details
     * @param userRequest
     * @return
     */
    @Override
    public UserResponse updateUserDetails(UserRequest userRequest) {
        if(userRequest.getId() != null){
            this.getUserById(userRequest.getId());

            User user = userRepository.findById(userRequest.getId())
                    .orElseThrow(()-> new ResourceNotFoundException("User not exist with id :" + userRequest.getId()));

            user.setId(userRequest.getId());
            user.setUsername(userRequest.getUsername());
            user.setContactNumber(userRequest.getContactNumber());
            user.setEmail(userRequest.getEmail());

            User response = userRepository.save(user);

            return new UserResponse(

                    response.getId(),
                    response.getUsername(),
                    response.getContactNumber(),
                    response.getEmail(),
                    response.getEmployeeId()
            );
        }
        else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    /**
     * Delete User
     * @param id
     * @return
     */
    @Override
    public Boolean deleteUser(Long id) {
        if(id != null){
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));

            userRepository.delete(user);
        }
        else {
            throw new ResourceNotFoundException("User not found");
        }
        return true;
    }

    @Override
    public ResponseEntity<?> signIn(AuthenticationRequest authenticationRequest) {
        Optional<User> user = userRepository.findByUsername(authenticationRequest.getUsername());
        if (user.isPresent() && passwordEncoder.matches(authenticationRequest.getPassword(), user.get().getPassword())) {
            String accessToken = createAccessToken(user.get());
            AuthResponse authResponse = new AuthResponse();
            if (accessToken != null) {
                authResponse.setToken(accessToken);
                authResponse.setUserId(user.get().getUserId());
                authResponse.setUserType(user.get().getUserType());
                authResponse.setName(user.get().getUsername());
            }
            return ResponseEntity.ok().body(new StandardResponse(
                            HttpStatus.OK,
                            "Login successful",
                            authResponse
                    )
            );
        }
        return ResponseEntity.badRequest().body(new StandardResponse(
                HttpStatus.UNAUTHORIZED,
                "Login Failed",
                new ArrayList<>()
        ));
    }

    @Override
    public User findUserById(String id) {
        return userRepository.findByUserId(id);
    }

    /**
     * Helper method
     * @param user
     * @return
     */
    public UserResponse response(User user){
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setContactNumber(user.getContactNumber());
        response.setEmail(user.getEmail());
        response.setEmployeeId(user.getEmployeeId());
        return response;
    }

    public String createAccessToken(User user) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getUserType()));
        return JwtGenerator.generateToken(user.getUsername(), user.getUserId(), authorities);
    }
}
