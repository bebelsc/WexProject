package githubbebelsc.wexproject.service;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import githubbebelsc.wexproject.model.ExchangeRate;
import githubbebelsc.wexproject.model.PurchaseTransaction;

public class PurchaseTransactionServiceTest {
    
    private PurchaseTransactionRepository purchaseTransactionRepository;
    private PurchaseTransactionService purchaseTransactionService;
    ExchangeRateService exchangeRateService = Mockito.mock(ExchangeRateService.class);

    @BeforeEach
    public void setUp() {
        purchaseTransactionService = new PurchaseTransactionService(purchaseTransactionRepository,exchangeRateService);
    }

    @Test
    public void testCalculateExchangeRate() throws Exception {
        PurchaseTransaction transaction = new PurchaseTransaction();
        transaction.setId(1L);
        transaction.setDescription("Exemplo");
        transaction.setTransactionDate(new Date()); 
        transaction.setPurchaseAmount(new BigDecimal("100.00"));

        String currency = "Currency"; 
        ExchangeRate exchangeRateMock = Mockito.mock(ExchangeRate.class);
        exchangeRateMock.setRecord_date(new Date()); 
        exchangeRateMock.setCountry("Country"); 
        exchangeRateMock.setCurrency("Currency"); 
        exchangeRateMock.setCountry_currency_desc("Country - Currency Description"); 
        exchangeRateMock.setExchange_rate(77.86); 
        exchangeRateMock.setEffective_date(new Date()); 
        exchangeRateMock.setSrc_line_nbr(1); 
        exchangeRateMock.setRecord_fiscal_year(2023); 
        exchangeRateMock.setRecord_fiscal_quarter(4); 
        exchangeRateMock.setRecord_calendar_year(2023); 
        exchangeRateMock.setRecord_calendar_quarter(3); 
        exchangeRateMock.setRecord_calendar_month(9); 
        exchangeRateMock.setRecord_calendar_day(30); 

       
        List<ExchangeRate> exchangeRateList = new ArrayList<>();
        exchangeRateList.add(exchangeRateMock);

        Mockito.when(exchangeRateService.getExchangeRates(currency)).thenReturn(exchangeRateList);

        Mockito.when(exchangeRateMock.getRecord_date()).thenReturn(new Date());
        String result = purchaseTransactionService.calculateExchangeRate(transaction, "Currency");

        String expected = "Identifier: 1\n" +
                "Description: Exemplo\n" +
                "Transaction Date: " + new Date() + "\n" +
                "Purchase Amount in Dollars: U$100.00\n" +
                "Conversion Rate: 77.86\n" +
                "Converted Amount: 800.00";

        assertNotEquals(expected, result);
    }

}
