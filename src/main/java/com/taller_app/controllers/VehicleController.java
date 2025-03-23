package com.taller_app.controllers;

import com.taller_app.dtos.inDTOs.VehicleInDTO;
import com.taller_app.dtos.outDTOs.VehicleOutDTO;
import com.taller_app.services.IVehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {


    private final IVehicleService vehicleService;

    public VehicleController(IVehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Vehicle Test!", HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<VehicleOutDTO> createVehicle (@RequestBody VehicleInDTO vehicleInDTO) {
        return new ResponseEntity<>(vehicleService.createVehicle(vehicleInDTO), HttpStatus.CREATED);
    }

    @GetMapping("/findvehicle/{vehicleId}")
    public ResponseEntity<VehicleOutDTO> findVehicleById (@PathVariable Long vehicleId) {
        return new ResponseEntity<>(vehicleService.findVehicleById(vehicleId), HttpStatus.OK);
    }

    @GetMapping("/findall")
    public ResponseEntity<List<VehicleOutDTO>> findAllVehicles() {
        return new ResponseEntity<>(vehicleService.findAllVehicles(), HttpStatus.OK);
    }

    @PutMapping("/update/{vehicleId}")
    public ResponseEntity<VehicleOutDTO> updateVehicle (@PathVariable Long vehicleId, @RequestBody VehicleInDTO vehicleInDTO) {
        return new ResponseEntity<>(vehicleService.updateVehicle(vehicleId, vehicleInDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{vehicleId}")
    public ResponseEntity<String> deleteVehicleById(@PathVariable Long vehicleId) {
        return new ResponseEntity<>(vehicleService.deleteVehicle(vehicleId), HttpStatus.OK);
    }

    @PutMapping("/addcustomer/{vehicleId}/{customerId}")
    public ResponseEntity<String> addCustomer(@PathVariable Long vehicleId,@PathVariable Long customerId) {
        return new ResponseEntity<>(vehicleService.addCustomerToVehicle(vehicleId, customerId), HttpStatus.OK);
    }

    @PutMapping("/deletecustomer/{vehicleId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long vehicleId) {
        return new ResponseEntity<>(vehicleService.deleteCustomerByVehicleId(vehicleId), HttpStatus.OK);
    }

}
