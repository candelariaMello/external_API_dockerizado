package ar.edu.utn.frc.tup.lciii.Services.ServiceImpl;

import ar.edu.utn.frc.tup.lciii.Entities.CarEntity;
import ar.edu.utn.frc.tup.lciii.Repositories.JpaCarRepository;
import ar.edu.utn.frc.tup.lciii.Services.CarService;
import ar.edu.utn.frc.tup.lciii.clients.CarClientResponse;
import ar.edu.utn.frc.tup.lciii.clients.CarsClient;
import ar.edu.utn.frc.tup.lciii.clients.ExchangeClient;
import ar.edu.utn.frc.tup.lciii.models.Car;
import ar.edu.utn.frc.tup.lciii.models.ExchangeRates;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarsClient carsClient;
    @Autowired
    private ExchangeClient exchangeRatesClient;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JpaCarRepository jpaCarRepository;
    @Override
    public CarClientResponse getCarById(Long id) {

        Car car = carsClient.fetchCar(id);

        CarClientResponse carClientResponse = modelMapper.map(car, CarClientResponse.class);
       /* carClientResponse.setModel(car.getModel());
        carClientResponse.setBrand(car.getBrand());*/

        if(car.getPrice().getCurrency().equals("ARS")){
            carClientResponse.setLocal_price(car.getPrice().getAmount());
        }
        else{
            ExchangeRates exchangeRates=exchangeRatesClient.exchangeRates(car.getPrice().getCurrency());
            carClientResponse.setLocal_price(car.getPrice().getAmount().multiply(exchangeRates.getExchange_rate()));
        }
        return carClientResponse;
    }
    @Override
    public CarClientResponse getCarByDate(Long carId, LocalDate date){
        Car car = carsClient.fetchCar(carId);

        CarClientResponse carClientResponse = modelMapper.map(car, CarClientResponse.class);

        if(car.getPrice().getCurrency().equals("ARS")){
            carClientResponse.setLocal_price(car.getPrice().getAmount());
        }
        else{
            ExchangeRates exchangeRates=exchangeRatesClient.historicalRate(car.getPrice().getCurrency(),date);
            carClientResponse.setLocal_price(car.getPrice().getAmount().multiply(exchangeRates.getExchange_rate()));
        }
        return carClientResponse;
    }

//    @Override
//    public CarEntity createCar(CarEntity car) {
//        return jpaCarRepository.save(car);
//    }

    @Override
    public Car createCar(Car car) {
        // Convierte el objeto Car en CarEntity
        CarEntity carEntity = new CarEntity();
        carEntity.setModel(car.getModel());
        carEntity.setBrand(car.getBrand());
        // Establece otros campos según sea necesario

        // Guarda la entidad en la base de datos utilizando el repositorio
        CarEntity savedCarEntity = jpaCarRepository.save(carEntity);

        // Convierte la entidad guardada nuevamente a la clase Car
        Car savedCar = new Car();
        savedCar.setId(savedCarEntity.getId());
        savedCar.setModel(savedCarEntity.getModel());
        savedCar.setBrand(savedCarEntity.getBrand());
        // Establece otros campos según sea necesario

        return savedCar;
    }

}
