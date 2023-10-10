package ar.edu.utn.frc.tup.lciii.Services;

import ar.edu.utn.frc.tup.lciii.Entities.CarEntity;
import ar.edu.utn.frc.tup.lciii.clients.CarClientResponse;
import ar.edu.utn.frc.tup.lciii.models.Car;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface CarService {

    CarClientResponse getCarById(Long id);

    CarClientResponse getCarByDate(Long id, LocalDate date);

    Car createCar(Car car);

//    CarEntity createCar(CarEntity car);
}
