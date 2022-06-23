package com.revature.service;

import com.revature.model.*;
import com.revature.repository.CarRepository;

import java.util.List;

public class CarService {

    private CarRepository carRepository;

    public CarService(){
        carRepository = new CarRepository();
    }

    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public Car createNewCar(Car car) {
        return carRepository.create(car);
    }

    public List<Car> getAllCars(){
        return carRepository.getAll();
    }

    public Car getCarById(int id) {
        return carRepository.getById(id);
    }

    public Car updateCarById(Car car){
        return carRepository.update(car);
    }

    public boolean deleteCarById(int id){
        return carRepository.deleteById(id);
    }

    public List<Car> getCarByMake(Make make){
        return carRepository.getCarByMake(make);
    }

    public List<Car> getCarByModel(Model model){
        return carRepository.getCarByModel(model);
    }

    public List<Car> getCarByColor(Color color){
        return carRepository.getCarByColor(color);
    }

    public List<Car> getCarByAvailability(Availability availability){
        return carRepository.getCarByAvailability(availability);
    }

}