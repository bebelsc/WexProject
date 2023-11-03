package githubbebelsc.wexproject.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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

    public String calculateExchangeRate(PurchaseTransaction transaction, String currency) throws Exception {

        List<ExchangeRate> exchangeRates=exchangeRateService.getExchangeRates(currency);

        Date endTransactionDate = calculateEndDate(transaction.getTransactionDate());
        removeExchangeRatesBeforeDate(exchangeRates, endTransactionDate);

        if(exchangeRates.isEmpty()){
            throw new Exception("No conversion rate available");
        }
        else{
            BigDecimal exchangeRate= new BigDecimal(exchangeRates.get(0).getExchange_rate());
            BigDecimal convertedAmount;
            DecimalFormat df = new DecimalFormat("#.00");

            convertedAmount = transaction.getPurchaseAmount().multiply(exchangeRate);

            return "Identifier: " + transaction.getId() +"\n"
                    +"Description: " + transaction.getDescription() +"\n"
                    +"Transaction Date: " + transaction.getTransactionDate() +"\n"
                    +"Purchase Amount in Dollars: U$" + transaction.getPurchaseAmount() +"\n"
                    +"Conversion Rate: "+ df.format(exchangeRate) + "\n"
                    +"Converted Amount: " + df.format(convertedAmount);   
                    
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
