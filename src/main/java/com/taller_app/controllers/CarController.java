package com.taller_app.controllers;

import com.taller_app.dtos.inDTOs.CarInDTO;
import com.taller_app.dtos.outDTOs.CarOutDTO;
import com.taller_app.entities.Car;
import com.taller_app.services.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {


    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Car Test!", HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<CarOutDTO> createCar(@RequestBody CarInDTO carInDTO) {
        return new ResponseEntity<>(carService.createCar(carInDTO), HttpStatus.CREATED);
    }

    @GetMapping("/findcar/{carId}")
    public ResponseEntity<CarOutDTO> findCarById(@PathVariable Long carId) {
        return new ResponseEntity<>(carService.findCarById(carId), HttpStatus.OK);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<CarOutDTO>> findAllCars() {
        return new ResponseEntity<>(carService.findAllCars(), HttpStatus.OK);
    }

    @PutMapping("/update/{carId}")
    public ResponseEntity<CarOutDTO> updateCar(@PathVariable Long carId, @RequestBody CarInDTO carInDTO) {
        return new ResponseEntity<>(carService.updateCar(carId, carInDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{carId}")
    public ResponseEntity<String> deleteCarById(@PathVariable Long carId) {
        return new ResponseEntity<>(carService.deleteCar(carId), HttpStatus.OK);
    }

    @PutMapping("/addcustomer/{carId}/{customerId}")
    public ResponseEntity<String> addCustomer(@PathVariable Long carId,@PathVariable Long customerId) {
        return new ResponseEntity<>(carService.addCustomerToCar(carId, customerId), HttpStatus.OK);
    }

    @PutMapping("/deletecustomer/{carId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long carId) {
        return new ResponseEntity<>(carService.deleteCustomerByCarId(carId), HttpStatus.OK);
    }

}
