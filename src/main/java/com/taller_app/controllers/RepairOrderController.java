package com.taller_app.controllers;

import com.taller_app.dtos.inDTOs.RepairOrderInDTO;
import com.taller_app.dtos.outDTOs.RepairOrderOutDTO;
import com.taller_app.services.IRepairOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/repairorder")
public class RepairOrderController {

    private final IRepairOrderService repairOrderService;

    public RepairOrderController (IRepairOrderService repairOrderService) {
        this.repairOrderService = repairOrderService;
    }

    @PostMapping("/create")
    public ResponseEntity<RepairOrderOutDTO> createRepairOrder (@RequestBody RepairOrderInDTO repairOrderInDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repairOrderService.createRepairOrder(repairOrderInDTO));
    }

    @GetMapping("/findbyid/{repairOrderId}")
    public ResponseEntity<RepairOrderOutDTO> findRepairOrderById (@PathVariable Long repairOrderId) {
        return ResponseEntity.ok(repairOrderService.findRepairOrderById(repairOrderId));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<RepairOrderOutDTO>> findAllRepairOrders () {
        return ResponseEntity.ok(repairOrderService.findAllRepairOrders());
    }

    @PutMapping("/update/{repairOrderId}")
    public ResponseEntity<String> updateRepairOrder (@PathVariable Long repairOrderId, @RequestBody RepairOrderInDTO repairOrderInDTO) {
        return ResponseEntity.ok(repairOrderService.updateRepairOrder(repairOrderId, repairOrderInDTO));
    }

    @DeleteMapping("/delete/{repairOrderId}")
    public ResponseEntity<String> deleteRepairOrder (@PathVariable Long repairOrderId) {
        return ResponseEntity.ok(repairOrderService.deleteRepairOrder(repairOrderId));
    }

    @PutMapping("/addrepairs/{repairOrderId}")
    public ResponseEntity<String> addRepairs (@PathVariable Long repairOrderId, @RequestBody Map<String, Double> repairs) {
        return ResponseEntity.ok(repairOrderService.addRepairs(repairOrderId, repairs));
    }

    @DeleteMapping("/deleterepair/{repairOrderId}")
    public ResponseEntity<String> deleteRepair (@PathVariable Long repairOrderId, @RequestBody String key) {
        return ResponseEntity.ok(repairOrderService.deleteRepair(repairOrderId, key));
    }

    @PutMapping("/repaircompleted/{repairOrderId}")
    public ResponseEntity<String> updateRepairCompleted (@PathVariable Long repairOrderId, @RequestBody boolean value) {
        return ResponseEntity.ok(repairOrderService.updateRepairCompleted(repairOrderId, value));
    }

    @GetMapping("/findallpending")
    public ResponseEntity<List<RepairOrderOutDTO>> findPendingRepairOrders () {
        return ResponseEntity.ok(repairOrderService.findPendingRepairOrders());
    }

    @GetMapping("/findallcompleted")
    public ResponseEntity<List<RepairOrderOutDTO>> findCompletedRepairOrders () {
        return ResponseEntity.ok(repairOrderService.findCompletedRepairOrders());
    }
}
