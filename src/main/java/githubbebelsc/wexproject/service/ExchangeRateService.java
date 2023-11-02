package githubbebelsc.wexproject.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


import githubbebelsc.wexproject.model.ApiResponse;
import githubbebelsc.wexproject.model.ExchangeRate;

@Service
public class ExchangeRateService {

    public List<ExchangeRate> getExchangeRates( String currency) {
        ApiResponse apiResponse = new ApiResponse();
        String apiUrl = "https://api.fiscaldata.treasury.gov/services/api/fiscal_service/v1/accounting/od/rates_of_exchange";
        String filters = "?filter=currency:eq:"+currency.toString()+"&sort=-record_date&page[number]=1&page[size]=100";
        String url = apiUrl + filters;

        // Cria um cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Cria uma solicitação HTTP GET
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();

        try {
            // Envia a solicitação e recebe a resposta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();

            ObjectMapper objectMapper = new ObjectMapper();
            apiResponse = objectMapper.readValue(responseBody, ApiResponse.class);

            List<ExchangeRate> exchangeRates = apiResponse.getData();

           return exchangeRates;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
   
}
