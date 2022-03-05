package com.example.mobileaccessoriesbackend.services.interfaces;

import com.example.mobileaccessoriesbackend.entity.Branch;
import org.springframework.http.ResponseEntity;

public interface IBranchService {

    ResponseEntity<?> save(Branch branch);

    Branch findByName(String name);

    Branch findById(Long id);

    ResponseEntity<?> findAll();
}
