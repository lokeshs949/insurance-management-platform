package com.assignment.insurance.controller;

import com.assignment.insurance.exception.ResourceNotFoundException;
import com.assignment.insurance.model.Claim;
import com.assignment.insurance.model.Client;
import com.assignment.insurance.model.InsurancePolicy;
import com.assignment.insurance.repo.ClaimRepository;
import com.assignment.insurance.repo.PolicyRepository;
import com.assignment.insurance.service.ClaimService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api")
public class ClaimController {
    private final ClaimService claimService;
    private final ClaimRepository claimRepository;
    private final PolicyRepository policyRepository;

    public ClaimController(ClaimService claimService,
                           ClaimRepository claimRepository,
                           PolicyRepository policyRepository) {
        this.claimService = claimService;
        this.claimRepository = claimRepository;
        this.policyRepository = policyRepository;
    }
    @GetMapping(path = "/claims")
    public List<Claim> getClaims(){
        return claimService.getClaims();
    }
    @GetMapping(path = "/claim/{id}")
    public ResponseEntity<Claim> getClaimById(@PathVariable(value = "id")Long id){
        Claim claim= claimService.getClaimById(id).orElseThrow(() -> new ResourceNotFoundException("claim not exist with id:" + id));
        return ResponseEntity.ok(claim);
    }
    @PostMapping(path = "/claim")
    public Claim ceateClaim(@RequestBody Claim claim) {
        Optional<InsurancePolicy> optionalPolicy = policyRepository.findInsurancePolicyById(claim.getPolicy_id());
        InsurancePolicy insurancePolicy = optionalPolicy.map(c -> {
            return new InsurancePolicy(c.getId(),c.getPolicyNumber(), c.getType(),c.getCoverageAmount(), c.getPremium(),c.getStartDate(),c.getEndDate(),c.getClient(),c.getClient_id());
        }).orElseThrow(() -> new ResourceNotFoundException("Client not found with id: "));
        claim.setInsurancePolicy(insurancePolicy);

        return claimService.createClaim(claim);
    }

    @PutMapping(path = "/Uclaim/{id}")
    public ResponseEntity<Claim> updateClaim(@PathVariable Long id, @RequestBody Claim newClaim) {
        Claim claimToUpdate = claimService.getClaimById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not exist with id: " + id));

        if (newClaim.getClaimNumber() != null) {
            claimToUpdate.setClaimNumber(newClaim.getClaimNumber());
        }
        if (newClaim.getDescription() != null) {
            claimToUpdate.setDescription(newClaim.getDescription());
        }
        if (newClaim.getClaimDate() != null) {
            claimToUpdate.setClaimDate(newClaim.getClaimDate());
        }
        if (newClaim.getClaimStatus() != null) {
            claimToUpdate.setClaimStatus(newClaim.getClaimStatus());
        }

        claimService.saveClaim(claimToUpdate);

        return ResponseEntity.ok(claimToUpdate);
    }

    @DeleteMapping(path = "/Dclaim/{id}")
    public ResponseEntity<HttpStatus> deleteClaim(@PathVariable long id){

        Claim claim = claimService.getClaimById(id)
                .orElseThrow(() -> new ResourceNotFoundException("claim not exist with id: " + id));

                claimService.delete(claim);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
