package ar.edu.utn.frc.tup.lciii.clients;

import ar.edu.utn.frc.tup.lciii.models.ExchangeRates;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ExchangeClient {
    private final RestTemplate restTemplate;
    private final String BASE_EXCHANGE="https://my-json-server.typicode.com/danielcattaneob/fake-apis/exchange_rates?from=";
    private final String BASE_HISTORICAL="https://my-json-server.typicode.com/danielcattaneob/fake-apis/historical_exchange_rates";
    public ExchangeRates exchangeRates(String currency){
        ResponseEntity<ExchangeRates[]> exchangeR=restTemplate.getForEntity(BASE_EXCHANGE + currency, ExchangeRates[].class);
        if(exchangeR.getStatusCode().is2xxSuccessful()){
            return exchangeR.getBody()[0];
        }
        throw new EntityNotFoundException("Exchange not available");
    }

    public ExchangeRates historicalRate(String currency, LocalDate date){
        ResponseEntity<ExchangeRates[]> exchange=restTemplate.getForEntity
                (BASE_HISTORICAL + "?date=" + date + "&from=" + currency, ExchangeRates[].class);
        if(exchange.getStatusCode().is2xxSuccessful()){
            return exchange.getBody()[0];
        }
        throw new EntityNotFoundException("Historical not available");
    }
}
