package nttdata.bootcamp.quarkus.loan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Cacheable
@Table(name = "LOANS")
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLoan;
    private String loanNumber;

    private String monthlyPaymentDate;
    private String expirationDate;
    private int quotaNumber;
    private String validationCode;
    private double initialBalance;
    private double currentBalance;
}