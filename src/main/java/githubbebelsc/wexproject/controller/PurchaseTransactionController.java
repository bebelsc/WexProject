package githubbebelsc.wexproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import githubbebelsc.wexproject.model.PurchaseTransaction;
import githubbebelsc.wexproject.service.PurchaseTransactionService;

@RestController
@RequestMapping("/api/transactions")
public class PurchaseTransactionController {

    private final PurchaseTransactionService transactionService;

    @Autowired
    public PurchaseTransactionController(PurchaseTransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addTransaction(@RequestBody PurchaseTransaction transactionBody) {

        PurchaseTransaction transaction = new PurchaseTransaction();
        
        transaction.setDescription(transactionBody.getDescription());
        transaction.setTransactionDate(transactionBody.getTransactionDate());
        transaction.setPurchaseAmount(transactionBody.getPurchaseAmount());

        PurchaseTransaction savedTransaction = transactionService.saveTransaction(transaction);

        return ResponseEntity.ok("Transação adicionada com sucesso. ID: " + savedTransaction.getId());
    }

    @GetMapping("/{id}/{currency}")
    public ResponseEntity<String> getTransactionById(@PathVariable Long id, @PathVariable String currency) throws Exception {
        PurchaseTransaction transaction = transactionService.getTransactionById(id);

        if (transaction != null) {
           return ResponseEntity.ok(transactionService.calculateExchangeRate(transaction, currency));
        }
        else{
            return ResponseEntity.notFound().build();
        }
        
        
    }

}