package com.revature.controller;

import com.revature.model.*;

import com.revature.service.OfferService;
import io.javalin.http.Handler;
import io.javalin.http.HttpCode;
import org.eclipse.jetty.http.HttpStatus;

import java.util.List;

public class OfferController {

    OfferService offerService = new OfferService();

    public Handler getAllOffers = ctx -> {
        List<Offer> offers = offerService.getAllOffers();
        ctx.json(offers);
    };

    public Handler createNewOffer = ctx -> {
        Offer offer = ctx.bodyAsClass(Offer.class);
        try {
            ctx.status(HttpCode.CREATED).json(offerService.createNewOffer(offer));
        }catch (NullPointerException | NumberFormatException e){
            ctx.status(HttpCode.BAD_REQUEST).result("Could not create user");
        }
    };

    public Handler getOfferById = ctx -> {

    };

    public Handler updateOfferById = ctx -> {

    };

    public Handler deleteOfferById = ctx -> {
        String param = ctx.pathParam("id");
        int id = 0;

        try{
            id = Integer.parseInt(param);
            boolean result = offerService.delete(id);
            if (result) {
                ctx.status(HttpCode.OK).result("Offer " + id + " has been deleted");
            }
            else {
                ctx.status(HttpCode.BAD_REQUEST).result("Offer " + id + " could not be deleted");
            }
        }catch (NullPointerException e) {
            ctx.status(HttpCode.NOT_FOUND).result("Offer " + id + " could not found");
        }
    };

    public Handler getOpenOffersByUserId = ctx -> {

    };
}