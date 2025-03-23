package com.taller_app.controllers;

import com.taller_app.services.IRepairOrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/repairorder")
public class RepairOrderController {

    private final IRepairOrderService repairOrderService;

    public RepairOrderController (IRepairOrderService repairOrderService) {
        this.repairOrderService = repairOrderService;
    }
}
