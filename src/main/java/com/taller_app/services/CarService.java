package com.taller_app.services;

import com.taller_app.dtos.inDTOs.CarInDTO;
import com.taller_app.dtos.outDTOs.CarOutDTO;
import com.taller_app.entities.Car;
import com.taller_app.entities.Customer;
import com.taller_app.exceptions.GeneralException;
import com.taller_app.mappers.carMappers.CarInDTOToCar;
import com.taller_app.mappers.carMappers.CarProjectionListToCarOutDTOList;
import com.taller_app.mappers.carMappers.CarProjectionToCarOutDTO;
import com.taller_app.mappers.carMappers.CarToCarOutDTO;
import com.taller_app.projections.ICarProjection;
import com.taller_app.repositories.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final CarInDTOToCar carInDTOToCar;
    private final CustomerService customerService;
    private final CarProjectionToCarOutDTO carProjectionToCarOutDTO;
    private final CarToCarOutDTO carToCarOutDTO;
    private final CarProjectionListToCarOutDTOList carProjectionListToCarOutDTOList;

    public CarService(CarRepository carRepository, CarInDTOToCar carInDTOToCar, CustomerService customerService,
                      CarProjectionToCarOutDTO carProjectionToCarOutDTO, CarToCarOutDTO carToCarOutDTO,
                      CarProjectionListToCarOutDTOList carProjectionListToCarOutDTOList) {
        this.carRepository = carRepository;
        this.carInDTOToCar = carInDTOToCar;
        this.customerService = customerService;
        this.carProjectionToCarOutDTO = carProjectionToCarOutDTO;
        this.carToCarOutDTO = carToCarOutDTO;
        this.carProjectionListToCarOutDTOList = carProjectionListToCarOutDTOList;
    }



    public CarOutDTO createCar(CarInDTO carInDTO) {
        Car car = carInDTOToCar.map(carInDTO);
        carRepository.save(car);
        CarOutDTO carOutDTO = carToCarOutDTO.map(car);
        return carOutDTO;
    }

    public CarOutDTO findCarById (Long carId) {
        ICarProjection iCarProjection = this.findCarProjection(carId);
        CarOutDTO carOutDTO = carProjectionToCarOutDTO.map(iCarProjection);
        return carOutDTO;
    }

    public List<CarOutDTO> findAllCars() {
        List<ICarProjection> carProjectionList = carRepository.findAllProjectedBy();
        List<CarOutDTO> carOutDTOList = carProjectionListToCarOutDTOList.map(carProjectionList);
        return carOutDTOList;
    }

    public CarOutDTO updateCar (Long carId, CarInDTO carInDTO) {
        Car car = this.findCar(carId);

        car.setNumber(carInDTO.getNumber());
        car.setBrand(carInDTO.getBrand());

        carRepository.save(car);
        return carToCarOutDTO.map(car);
    }

    public String deleteCar(Long carId) {
        Car car = this.findCar(carId);

        carRepository.delete(car);
        return "Car id: " + carId + " was successfully deleted!";
    }

    public String addCustomerToCar(Long carId, Long customerId) {
        Car car = this.findCar(carId);
        Customer customer = customerService.findCustomer(customerId);

        car.setCustomer(customer);
        carRepository.save(car);
        return "Customer id " + customerId + " was successfully added to Car id " + carId;
    }

    public String deleteCustomerByCarId(Long carId) {
        Car car = this.findCar(carId);

        car.setCustomer(null);
        carRepository.save(car);
        return "Customer was successfully deleted from Car id " + carId;
    }




    /*------------------------------------------------------------------------------------------------*/
    /*                                    CAR: UTIlS                                                  */
    /*------------------------------------------------------------------------------------------------*/


    public Car findCar (Long carId) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        if(optionalCar.isEmpty()) {
            throw new GeneralException("CarId does not exist.", HttpStatus.NOT_FOUND);
        }
        return optionalCar.get();
    }

    public ICarProjection findCarProjection (Long carId) {
        Optional<ICarProjection> optionalCarProjection = carRepository.findCarById(carId);
        if(optionalCarProjection.isEmpty()) {
            throw new GeneralException("CarId does not exist.", HttpStatus.NOT_FOUND);
        }
        return optionalCarProjection.get();
    }

}
