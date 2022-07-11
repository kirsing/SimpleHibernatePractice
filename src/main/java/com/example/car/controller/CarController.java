package com.example.car.controller;


import com.example.car.model.Car;
import com.example.car.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@AllArgsConstructor
public class CarController {
    CarService carService;

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        carService.createCar(car);
        return car;
    }

    @GetMapping("/all")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/byId/{id}")
    public Car getCarById(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteCarById(@PathVariable Long id) {
        carService.deleteCarById(id);
        return "Deleted: " + id;
    }

    @GetMapping("/ht/{price}")
    public List<Car> listCarsHt(@PathVariable Long price) {
        return carService.listHigherThan(price);
    }


}
