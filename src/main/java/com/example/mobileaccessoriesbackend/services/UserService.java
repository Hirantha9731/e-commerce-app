package com.example.mobileaccessoriesbackend.services;


import com.example.mobileaccessoriesbackend.dto.request.UserRequest;
import com.example.mobileaccessoriesbackend.dto.response.UserResponse;
import com.example.mobileaccessoriesbackend.entity.Supplier;
import com.example.mobileaccessoriesbackend.entity.User;
import com.example.mobileaccessoriesbackend.exceptions.ResourceNotFoundException;
import com.example.mobileaccessoriesbackend.repository.UserRepository;
import com.example.mobileaccessoriesbackend.services.interfaces.IUserService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService implements IUserService {

    /**
     * User Repository
     */
    private UserRepository userRepository;


    /**
     * Add User
     * @param userRequest
     * @return
     */
    @Override
    public UserResponse addUser(UserRequest userRequest) {
        User user = new User();

        user.setId(userRequest.getId());
        user.setUsername(userRequest.getUsername());
        user.setContactNumber(userRequest.getContactNumber());
        user.setEmail(userRequest.getEmail());
        user.setEmployeeId(userRequest.getEmployeeId());

        User response = userRepository.save(user);

        return new UserResponse(
                response.getId(),
                response.getUsername(),
                response.getContactNumber(),
                response.getEmail(),
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
            user.setEmployeeId(userRequest.getEmployeeId());

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
}
