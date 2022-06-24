package com.revature;

import com.revature.controller.UserController;
import com.revature.controller.CarController;
import com.revature.controller.OfferController;

import io.javalin.Javalin;


import static io.javalin.apibuilder.ApiBuilder.*;


public class Driver {

    public static void main(String[] args) {


        CarController carController = new CarController();
        UserController userController = new UserController();
        OfferController offerController = new OfferController();

        Javalin app = Javalin.create().start(8080);

        app.routes(() -> {
            path("/", () -> {
                get(ctx -> ctx.result("Welcome to Crazy Caleb's Car Dealership"));
            });
            path("users", () -> {
                get(userController.getAllUsers);
                post(userController.createNewUser);
                path("{id}", () -> {
                    get(userController.getUserById);
                    put(userController.updateUserById);
                    delete(userController.deleteUserById);
                });
            });
            path("cars", () -> {
                get(carController.getAllCars);
                post(carController.createNewCar);
                path("{id}", () -> {
                    get(carController.getCarById);
                    put(carController.updateCarById);
                    delete(carController.deleteCarById);
                });
                path("offers", () -> {
                    post(offerController.createNewOffer);
                    get(offerController.getAllOffers);
                    path("{id}", () -> {
                        get(offerController.getOfferById);
                        put(offerController.updateOfferById);
                        delete(offerController.deleteOfferById);
                    });
                });
            });
        });
    }
}
