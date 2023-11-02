package githubbebelsc.wexproject.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
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

    public String calculateExchangeRate(PurchaseTransaction transaction, String currency) {

        List<ExchangeRate> exchangeRates=exchangeRateService.getExchangeRates(currency);

        Date endTransactionDate = calculateEndDate(transaction.getTransactionDate());
        removeExchangeRatesBeforeDate(exchangeRates, endTransactionDate);

        if(exchangeRates.isEmpty()){
            return "Error";
        }
        else{
            double exchangeRate=exchangeRates.get(0).getExchange_rate();
            double convertedAmount;

            convertedAmount = transaction.getPurchaseAmount()*exchangeRate;

            return "Description: " + transaction.getDescription() +"\n"
                    + "Converted Amount: " + convertedAmount +"\n"
                    + "Transaction Date: " + transaction.getTransactionDate() +"\n"
                    +"Purchase Amount in Dollars:" + transaction.getPurchaseAmount() +"\n"
                    +"Conversion Rate: "+ exchangeRate;
        }
    }

    private Date calculateEndDate(Date startDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, -6);
        return calendar.getTime();
    }

    public void removeExchangeRatesBeforeDate(List<ExchangeRate> exchangeRates, Date endtransactionDate) {
        // Crie um Calendar para comparar as datas
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(endtransactionDate);
    
        Iterator<ExchangeRate> iterator = exchangeRates.iterator();
    
        while (iterator.hasNext()) {
            ExchangeRate exchangeRate = iterator.next();
    
            if (exchangeRate.getRecord_date().before(calendarEnd.getTime())) {
                iterator.remove();
            }
        }
    }
    
}
