package com.example.mobileaccessoriesbackend.services;

import com.example.mobileaccessoriesbackend.entity.Branch;
import com.example.mobileaccessoriesbackend.exceptions.ResourceNotFoundException;
import com.example.mobileaccessoriesbackend.repository.BranchRepository;
import com.example.mobileaccessoriesbackend.services.interfaces.IBranchService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BranchService implements IBranchService {

    private BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public ResponseEntity<?> save(Branch branch) {
        return null;
    }

    @Override
    public Branch findByName(String name) {
        return null;
    }

    @Override
    public Branch findById(Long id) {
        return branchRepository.findById(id).
                orElseThrow(()->new  ResourceNotFoundException("Branch is not exist with id : " + id));

    }

    @Override
    public ResponseEntity<?> findAll() {
        return null;
    }
}
