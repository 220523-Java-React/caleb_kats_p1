package com.revature.service;

import com.revature.model.Offer;
import com.revature.repository.OfferRepository;

import java.util.List;

public class OfferService {

    OfferRepository offerRepository;

    public OfferService() {
        offerRepository = new OfferRepository();
    }

    public OfferService(OfferRepository offerRepository){
        this.offerRepository = offerRepository;
    }

    public Offer createNewOffer(Offer offer){
        return offerRepository.create(offer);
    }

    public List<Offer> getAllOffers(){
        return offerRepository.getAll();
    }

    public Offer getOfferById(int id){
        return offerRepository.getById(id);
    }

    public Offer updateOfferById(Offer offer){
        return offerRepository.update(offer);
    }

    public boolean delete(int id){
        return offerRepository.deleteById(id);
    }

    public List<Offer> getOpenOffersByUserId(int userId) {
        return offerRepository.getAllOpenOffersByUserId(userId);
    }
}