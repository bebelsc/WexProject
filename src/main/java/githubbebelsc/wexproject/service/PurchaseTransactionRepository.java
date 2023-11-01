package githubbebelsc.wexproject.service;

import org.springframework.data.jpa.repository.JpaRepository;

import githubbebelsc.wexproject.model.PurchaseTransaction;

public interface PurchaseTransactionRepository extends JpaRepository<PurchaseTransaction, Long> {


}
