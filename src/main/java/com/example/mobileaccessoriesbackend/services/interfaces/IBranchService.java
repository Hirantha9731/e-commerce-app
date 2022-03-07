package com.example.mobileaccessoriesbackend.services.interfaces;

import com.example.mobileaccessoriesbackend.dto.response.BranchResponse;
import com.example.mobileaccessoriesbackend.entity.Branch;


import java.util.List;

public interface IBranchService {

    List<BranchResponse> getAllBranches();

    List<Branch> allBranches();

    Branch findById(Long id);


}
