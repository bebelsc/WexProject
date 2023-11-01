package githubbebelsc.wexproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import githubbebelsc.wexproject.model.PurchaseTransaction;

@Service
public class PurchaseTransactionService {
    
    private final PurchaseTransactionRepository transactionRepository;

    @Autowired
    public PurchaseTransactionService(PurchaseTransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public PurchaseTransaction saveTransaction(PurchaseTransaction transaction) {
        // Salvando a transação no banco de dados usando o repositório.
        return transactionRepository.save(transaction);
    }
}
