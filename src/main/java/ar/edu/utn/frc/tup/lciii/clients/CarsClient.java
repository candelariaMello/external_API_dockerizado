package ar.edu.utn.frc.tup.lciii.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import ar.edu.utn.frc.tup.lciii.models.Car;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Slf4j
@Service

public class CarsClient {

    //   public static final String FAKE_APIS_CARS = "/fake-apis/cars/";
    @Autowired
    private RestTemplate restTemplate;
    private final String BASE_URL = "https://my-json-server.typicode.com/danielcattaneob/fake-apis/cars/";

    private int counter = 0;

    @CircuitBreaker(name = "carsService", fallbackMethod = "fallbackForFetchCar")
    public Car fetchCar(Long id) {
        counter++;
        log.info("Attempt #" + counter + " to fetch car with ID: " + id);

        // Simular un error en las primeras 3 llamadas
        if (counter <= 3) {
            log.error("Simulated error: Failed to fetch car");
            throw new RuntimeException("Simulated error: Car not found");
        }

        ResponseEntity<Car> response = restTemplate.getForEntity(BASE_URL + id, Car.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }
        throw new EntityNotFoundException("Car not found");
    }

    public Car fallbackForFetchCar(Long id, Exception ex) {
        log.error("Circuit breaker triggered for fetchCar: " + ex.getMessage());
        return new Car(); // Puedes retornar un valor predeterminado o manejar el error de otra manera.
    }
}
