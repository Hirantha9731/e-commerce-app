package com.example.mobileaccessoriesbackend.controller;


import com.example.mobileaccessoriesbackend.dto.request.UserRequest;
import com.example.mobileaccessoriesbackend.dto.response.StandardResponse;
import com.example.mobileaccessoriesbackend.dto.response.UserResponse;
import com.example.mobileaccessoriesbackend.entity.User;
import com.example.mobileaccessoriesbackend.services.interfaces.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    /**
     * User Service
     */
    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<UserResponse> suppliersList = userService.getAllUsers();
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Users fetch successful",
                suppliersList));
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody UserRequest userRequest){

        UserResponse userResponse = userService.addUser(userRequest);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.CREATED,
                "User saved successfully",
                userResponse
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){

        User user = userService.getUserById(id);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "User fetch successful",
                user
        ));
    }


    @PutMapping
    public ResponseEntity<?> updateUserDetails(@RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.updateUserDetails(userRequest);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "User details updated successfully",
                userResponse
        ));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable Long id){

        boolean response  = userService.deleteUser(id);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "User deleted successfully",
                response
        ));
    }

}
