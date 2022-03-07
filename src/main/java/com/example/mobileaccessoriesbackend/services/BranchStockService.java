package com.example.mobileaccessoriesbackend.services;

import com.example.mobileaccessoriesbackend.entity.Branch;
import com.example.mobileaccessoriesbackend.entity.BranchStock;
import com.example.mobileaccessoriesbackend.entity.Product;
import com.example.mobileaccessoriesbackend.repository.BranchStockRepository;
import com.example.mobileaccessoriesbackend.services.interfaces.IBranchService;
import com.example.mobileaccessoriesbackend.services.interfaces.IBranchStockService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchStockService implements IBranchStockService {

    private final IBranchService branchService;
    private final BranchStockRepository branchStockRepository;

    public BranchStockService(IBranchService branchService,
                              BranchStockRepository branchStockRepository) {
        this.branchService = branchService;
        this.branchStockRepository = branchStockRepository;
    }

    @Override
    public void addQtyToAll(Product product) {
        List<Branch> allBranches = branchService.allBranches();
        allBranches.forEach(branch -> {
            BranchStock branchStock = new BranchStock();
            branchStock.setBranch(branch);
            branchStock.setProduct(product);
            branchStock.setAvailableQt(5);

            branchStockRepository.save(branchStock);
        });
    }
}
