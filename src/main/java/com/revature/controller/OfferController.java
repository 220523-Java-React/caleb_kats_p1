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

    };

    public Handler getOfferById = ctx -> {

    };

    public Handler updateOfferById = ctx -> {

    };

    public Handler deleteOfferById = ctx -> {

    };

    public Handler getOpenOffersByUserId = ctx -> {

    };
}