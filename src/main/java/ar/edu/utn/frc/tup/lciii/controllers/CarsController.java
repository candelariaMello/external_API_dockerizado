package ar.edu.utn.frc.tup.lciii.controllers;


import ar.edu.utn.frc.tup.lciii.Entities.CarEntity;
import ar.edu.utn.frc.tup.lciii.Repositories.JpaCarRepository;
import ar.edu.utn.frc.tup.lciii.Services.CarService;
import ar.edu.utn.frc.tup.lciii.clients.CarClientResponse;
import ar.edu.utn.frc.tup.lciii.models.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
public class CarsController {

    @Autowired
    private CarService carService;
    @Autowired
    private JpaCarRepository jpaCarRepository;

    @GetMapping("/{id}")
    CarClientResponse getCarById(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @GetMapping("/historic/{car-id}")
    CarClientResponse getCarByDate (@PathVariable("car-id")Long carId,
    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate date){
        return carService.getCarByDate(carId,date);
    }

    @PostMapping("")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car createdCar = carService.createCar(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);
    }

    @GetMapping("/todos")
    public List<CarEntity> getAll(){
        return jpaCarRepository.findAll();
    }

}
