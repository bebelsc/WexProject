package githubbebelsc.wexproject.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity 
@Table(name = "transaction")
public class PurchaseTransaction{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    @Size(max = 50, message = "Description must not exceed 50 characters")
    private String description;

    @Column(name = "date")
    @NotNull(message = "Mandatory field")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date transactionDate;

    @Column(name = "amount")
    @Positive(message = "The Amount must be a positive value")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal purchaseAmount;

    public BigDecimal getPurchaseAmount() {
        return purchaseAmount;
    }
    public void setPurchaseAmount(BigDecimal purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
    



}