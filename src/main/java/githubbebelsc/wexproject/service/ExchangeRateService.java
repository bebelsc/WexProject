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

    public List<ExchangeRate> getExchangeRates() {
        ApiResponse apiResponse = new ApiResponse();
        String apiUrl = "https://api.fiscaldata.treasury.gov/services/api/fiscal_service/v1/accounting/od/rates_of_exchange";
        String filters = "?filter=currency:eq:Dollar&filter=record_date:gte:2023-09-02";
        String url = apiUrl + filters;

        // Crie um cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Crie uma solicitação HTTP GET
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();

        try {
            // Envie a solicitação e receba a resposta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();

            ObjectMapper objectMapper = new ObjectMapper();
            apiResponse = objectMapper.readValue(responseBody, ApiResponse.class);

            List<ExchangeRate> exchangeRates = apiResponse.getData();

            return exchangeRates;
        } catch (Exception e) {
            // Lidar com erros de solicitação ou análise de resposta.
            return Collections.emptyList();
        }
    }
   
}
