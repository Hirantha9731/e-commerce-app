package com.example.mobileaccessoriesbackend.services;

import com.example.mobileaccessoriesbackend.dto.response.BranchResponse;
import com.example.mobileaccessoriesbackend.entity.Branch;
import com.example.mobileaccessoriesbackend.exceptions.ResourceNotFoundException;
import com.example.mobileaccessoriesbackend.repository.BranchRepository;
import com.example.mobileaccessoriesbackend.services.interfaces.IBranchService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BranchService implements IBranchService {

    private BranchRepository branchRepository;

    /**
     * Constructor
     * @param branchRepository
     */
    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }


    @Override
    public List<BranchResponse> getAllBranches() {
        List<Branch> branches = branchRepository.findAll();
        return branches.stream().map(this::response).collect(Collectors.toList());
    }

    @Override
    public List<Branch> allBranches() {
        return branchRepository.findAll();
    }


    @Override
    public Branch findById(Long id) {
        return  branchRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product not exist with id : "+ id));

    }


    public BranchResponse response(Branch branch){

        BranchResponse response = new BranchResponse();
        response.setId(branch.getId());
        response.setBranchName(branch.getBranchName());
        response.setBranchLocation(branch.getBranchLocation());

        return response;
    }

}
