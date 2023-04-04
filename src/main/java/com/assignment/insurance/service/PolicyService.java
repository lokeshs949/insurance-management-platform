package com.assignment.insurance.service;

import com.assignment.insurance.model.Claim;
import com.assignment.insurance.model.InsurancePolicy;
import com.assignment.insurance.repo.ClientRepository;
import com.assignment.insurance.repo.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyService {

    private final PolicyRepository policyRepository;
    @Autowired
    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;

    }

    public List<InsurancePolicy> getAllPolicies() {
        return policyRepository.findAll();
    }
    public InsurancePolicy createPolicy(InsurancePolicy insurancePolicy){

        return policyRepository.save(insurancePolicy);
    }

    public Optional<InsurancePolicy> getInsurancePolicyById(Long id){
        return policyRepository.findInsurancePolicyById(id);
    }

    public InsurancePolicy updatePolicy(InsurancePolicy updateInsurancePolicy) {
        return policyRepository.save(updateInsurancePolicy);
    }

    public void delete(InsurancePolicy insurancePolicy) {
         policyRepository.delete(insurancePolicy);
    }


//    public Claim createClaim(Claim claim) {
//
//        return policyRepository.save(claim);
//    }
}
