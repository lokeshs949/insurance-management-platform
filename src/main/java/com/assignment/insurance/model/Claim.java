package com.assignment.insurance.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name="Claim")
@Table(name="claim")
public class Claim {

    @Id
    @SequenceGenerator(
            name="client_sequence",
            sequenceName = "client_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "client_sequence"
    )
    private Long id;

    @Column(name = "claimNumber")
    private String claimNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "claimDate")
    private LocalDate claimDate;

    @Column(name = "claimStatus")
    private String claimStatus;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="policy_id",nullable=false,referencedColumnName = "id",foreignKey = @ForeignKey(name = "claim_fk"))
    private InsurancePolicy insurancePolicy;
    @Column(insertable=false, updatable=false)
    private Long policy_id;

    public Claim(String claimNumber, String description, LocalDate claimDate, String claimStatus, InsurancePolicy insurancePolicy, Long policy_id) {
        this.claimNumber = claimNumber;
        this.description = description;
        this.claimDate = claimDate;
        this.claimStatus = claimStatus;
        this.insurancePolicy = insurancePolicy;
        this.policy_id = policy_id;
    }
}

