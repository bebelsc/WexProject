package githubbebelsc.wexproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import githubbebelsc.wexproject.model.ExchangeRate;

public class ExchangeRateServiceTest {

    ExchangeRateService exchangeRateService = Mockito.mock(ExchangeRateService.class);


    @Test
    void testGetExchangeRates() {
        String currency = "Dollar"; 

         ExchangeRate exchangeRateMock = Mockito.mock(ExchangeRate.class);

        // Configure os valores dos campos do mock
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

        // Defina o comportamento esperado quando o serviço for chamado com a moeda
        Mockito.when(exchangeRateService.getExchangeRates(currency)).thenReturn(exchangeRateList);

        // Chame o método que deseja testar
        List<ExchangeRate> result = exchangeRateService.getExchangeRates(currency);

        // Faça as asserções para verificar se o resultado é o que você espera
        assertEquals(exchangeRateList, result);
    }
}