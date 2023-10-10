package ar.edu.utn.frc.tup.lciii.scheduled;

import ar.edu.utn.frc.tup.lciii.clients.CarsClient;
import ar.edu.utn.frc.tup.lciii.models.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@EnableScheduling
public class Schedule {

    @Autowired
    private CarsClient carsClient;

    @Scheduled(fixedDelay = 1000)
    public void scheduleActionCallMicroB() {
        try {
            Long carId = 1L; // Define aquí el ID del automóvil que deseas obtener
            Car car = carsClient.fetchCar(carId); // Proporciona el ID como argumento
            log.info("Car fetched: " + car); // Haz algo con el automóvil obtenido
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
