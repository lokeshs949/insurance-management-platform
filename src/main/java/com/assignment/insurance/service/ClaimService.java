package com.assignment.insurance.service;

import com.assignment.insurance.model.Claim;
import com.assignment.insurance.repo.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaimService {
    private final ClaimRepository claimRepository;
    @Autowired
    public ClaimService(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }
    public List<Claim> getClaims(){
        return claimRepository.findAll();
    }
    public Optional<Claim> getClaimById(Long id){
        return claimRepository.getClaimById(id);
    }
    public Claim createClaim(Claim claim){
        return claimRepository.save(claim);
    };

    public Claim saveClaim(Claim claimToUpdate) {
        return claimRepository.save(claimToUpdate);
    }

    public void delete(Claim claim) {
        claimRepository.delete(claim);
    }
}
