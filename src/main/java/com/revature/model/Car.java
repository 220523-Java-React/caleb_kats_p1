package com.revature.model;

import java.io.Serializable;
import java.util.Objects;

public class Car implements Serializable{

    private int id;
    private Color color;
    private Make make;
    private Model model;
    private Availability availability;

    public Car(){

    }

    public Car(int id, Color color, Make make, Model model, Availability availability){
        this.id = id;
        this.color = color;
        this.make = make;
        this.model = model;
        this.availability = availability;
    }

    public int getId() {
        return id;
    }

    public Car setId(int id) {
        this.id = id;
        return this;
    }

    public Color getColor() {
        return color;
    }

    public Car setColor(Color color) {
        this.color = color;
        return this;
    }

    public Make getMake() {
        return make;
    }

    public Car setMake(Make make) {
        this.make = make;
        return this;
    }

    public Model getModel() {
        return model;
    }

    public Car setModel(Model model) {
        this.model = model;
        return this;
    }

    public Availability getAvailability() {
        return availability;
    }

    public Car setAvailability(Availability availability) {
        this.availability = availability;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && color == car.color && make == car.make && model == car.model && availability == car.availability;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, color, make, model, availability);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", availability=" + availability +
                '}';
    }
}
