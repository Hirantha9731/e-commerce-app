package com.example.mobileaccessoriesbackend.controller;


import com.example.mobileaccessoriesbackend.dto.response.BranchResponse;
import com.example.mobileaccessoriesbackend.dto.response.ProductResponse;
import com.example.mobileaccessoriesbackend.dto.response.StandardResponse;
import com.example.mobileaccessoriesbackend.entity.Branch;
import com.example.mobileaccessoriesbackend.services.interfaces.IBranchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/branch")
@CrossOrigin
public class BranchController {

    private IBranchService branchService;

    public BranchController(IBranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<BranchResponse> productsList = branchService.getAllBranches();
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Branches fetch successful",
                productsList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBranchById(@PathVariable Long id){

        Branch branch = branchService.findById(id);
        return ResponseEntity.ok().body(new StandardResponse(
                HttpStatus.OK,
                "Branch fetch successfull",
                branch
        ));
    }
}
