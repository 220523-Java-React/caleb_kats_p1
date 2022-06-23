package com.revature.model;

import java.io.Serializable;
import java.util.Objects;

public class Offer implements Serializable{

    private int offerId;
    private int userId;
    private int carId;
    private Status status;

    public Offer(){

    }

    public Offer (int offerId, int userId, int carId, Status status){
        this.offerId = offerId;
        this.userId = userId;
        this.carId = carId;
        this.status = status;
    }

    public int getOfferId() {
        return offerId;
    }

    public Offer setOfferId(int offerId) {
        this.offerId = offerId;
        return this;
    }

    public int getUserId() {
        return userId;
    }

    public Offer setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public int getCarId() {
        return carId;
    }

    public Offer setCarId(int carId) {
        this.carId = carId;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public Offer setStatus(Status status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return offerId == offer.offerId && userId == offer.userId && carId == offer.carId && status == offer.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(offerId, userId, carId, status);
    }

    @Override
    public String toString() {
        return "Offer{" +
                "offerId=" + offerId +
                ", userId=" + userId +
                ", carId=" + carId +
                ", status=" + status +
                '}';
    }
}