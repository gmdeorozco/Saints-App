package com.saintsapp.saintsserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saintsapp.saintsserver.entities.ReligiousOrderEntity;
import com.saintsapp.saintsserver.services.ReligiousOrderService;

@RestController
public class ReligiousOrderController {

private final ReligiousOrderService religiousOrderService;

    public ReligiousOrderController( ReligiousOrderService religiousOrderService ){
        this.religiousOrderService = religiousOrderService;
    }
    
    @GetMapping("/orders")
    public List<ReligiousOrderEntity> getAllReligiousOrderEntities(){
        return religiousOrderService.getAllReligiousOrderEntities();
    }

}
