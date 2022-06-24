package com.revature.controller;

import com.revature.model.*;
import com.revature.service.CarService;

import io.javalin.http.Handler;
import io.javalin.http.HttpCode;
import org.eclipse.jetty.http.HttpStatus;

import java.util.*;

public class CarController {

    CarService carService = new CarService();

    public Handler getAllCars = ctx -> {
        List<Car> cars = carService.getAllCars();
        ctx.json(cars);
    };

    public Handler getCarById = ctx -> {

        String param = ctx.pathParam("id");
        int id = 0;
        try {
            id = Integer.parseInt(param);
            ctx.json(carService.getCarById(id));
        } catch (NumberFormatException e){
            ctx.result("Please input only numbers");
            ctx.status(HttpStatus.BAD_REQUEST_400);
        }catch (NullPointerException e){
            ctx.status(HttpCode.NOT_FOUND).result("Car " + id + " could not be found");
        }

    };

    public Handler createNewCar = ctx -> {
        Car car = ctx.bodyAsClass(Car.class);
        try {
            ctx.status(HttpCode.CREATED).json(carService.createNewCar(car));
        }catch (NullPointerException | NumberFormatException e){
            ctx.status(HttpCode.BAD_REQUEST).result("Could not create car");
        }
    };

    public Handler updateCarById = ctx -> {
        Car car = ctx.bodyAsClass(Car.class);
        ctx.json(carService.updateCarById(car));
    };

    public Handler deleteCarById = ctx -> {
        String param = ctx.pathParam("id");
        int id = 0;

        try{
            id = Integer.parseInt(param);
            boolean result = carService.deleteCarById(id);
            if (result == true) {
                ctx.status(HttpCode.OK).result("Car " + id + " has been deleted");
            }
            else {
                ctx.status(HttpCode.BAD_REQUEST).result("Car " + id + " could not be deleted");
            }
        }catch (NullPointerException e) {
            ctx.status(HttpCode.NOT_FOUND).result("Car " + id + " could not found");
        }
    };

    public Handler getCarByMake = ctx -> {
        List<Car> cars;

        String makeParam = ctx.queryParam("make");

        try {
            Make make = Make.valueOf(makeParam.toUpperCase(Locale.ROOT));
            cars = carService.getCarByMake(make);
        }catch (IllegalArgumentException e){
            String failedMessage = "Please enter only the following values: " + Arrays.toString(Make.values());
            ctx.status(400).json(failedMessage);
        }
    };

    public Handler getCarByModel = ctx -> {
        List<Car> cars;

        String modelParam = ctx.queryParam("model");

        try {
            Model model = Model.valueOf(modelParam.toUpperCase(Locale.ROOT));
            cars = carService.getCarByModel(model);
        }catch (IllegalArgumentException e){
            String failedMessage = "Please enter only the following values: " + Arrays.toString(Model.values());
            ctx.status(400).json(failedMessage);
        }
    };

    public Handler getCarByColor = ctx -> {
        List<Car> cars;

        String colorParam = ctx.queryParam("color");

        try {
            Color color = Color.valueOf(colorParam.toUpperCase(Locale.ROOT));
            cars = carService.getCarByColor(color);
        }catch (IllegalArgumentException e){
            String failedMessage = "Please enter only the following values: " + Arrays.toString(Model.values());
            ctx.status(400).json(failedMessage);
        }
    };

    public Handler getCarByAvailability = ctx -> {
        List<Car> cars;

        String availParam = ctx.queryParam("availability");

        try {
            Availability availability = Availability.valueOf(availParam.toUpperCase(Locale.ROOT));
            cars = carService.getCarByAvailability(availability);
        }catch (IllegalArgumentException e){
            String failedMessage = "Please enter only the following values: " + Arrays.toString(Model.values());
            ctx.status(400).json(failedMessage);
        }
    };
}