package githubbebelsc.wexproject.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import githubbebelsc.wexproject.model.ExchangeRate;
import githubbebelsc.wexproject.model.PurchaseTransaction;

@Service
public class PurchaseTransactionService {
    
    private final PurchaseTransactionRepository transactionRepository;
    private ExchangeRateService exchangeRateService;

    @Autowired
    public PurchaseTransactionService(PurchaseTransactionRepository transactionRepository,ExchangeRateService exchangeRateService) {
        this.transactionRepository = transactionRepository;
        this.exchangeRateService = exchangeRateService;
    }

    public PurchaseTransaction saveTransaction(PurchaseTransaction transaction) {
        // Salvando a transação no banco de dados usando o repositório.
        return transactionRepository.save(transaction);
    }

    /**
     * @param id
     * @return
     */
    public PurchaseTransaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public String calculateExchangeRate(PurchaseTransaction transaction) {

        List<ExchangeRate> exchangeRates = new ArrayList<>();

        Date starttransactionDate = transaction.getTransactionDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(starttransactionDate);
        calendar.add(Calendar.MONTH, -6);
        Date endtransactionDate= calendar.getTime();

        exchangeRates=exchangeRateService.getExchangeRates();

        double exchangeRate;
        double valorFinal;
        exchangeRate = exchangeRates.get(0).getExchange_rate();
        System.out.println(exchangeRate);

        valorFinal = transaction.getPurchaseAmount()*exchangeRate;

        return "Valor final: " + valorFinal;


    }
}
