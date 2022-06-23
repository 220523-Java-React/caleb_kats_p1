package com.revature.repository;

import com.revature.model.*;
import com.revature.util.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarRepository implements DAO<Car> {

    private List<Car> cars;

    public CarRepository(){
        cars = new ArrayList<>();
    }

    public CarRepository(List<Car> cars){
        this.cars = cars;
    }

    @Override
    public Car create(Car car) {
        String sql = "insert into cars(make, model, color) values(?,?,?)";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, car.getMake().name());
            stmt.setString(2, car.getModel().name());
            stmt.setString(3, car.getColor().name());
            stmt.setString(4, car.getAvailability().name());


            int success = stmt.executeUpdate();

            if (success == 1) {
                return car;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        String sql = "select * from cars";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                cars.add(new Car().
                        setId(results.getInt("car_id")).
                        setMake(Make.valueOf(results.getString("make"))).
                        setModel(Model.valueOf(results.getString("model"))).
                        setColor(Color.valueOf(results.getString("color"))).
                        setAvailability(Availability.valueOf(results.getString("availability"))));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    @Override
    public Car getById(int id) {
        String sql = "select * from cars where car_id = " + id;

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet results = stmt.executeQuery();

            if (results.next()) {
                Car car = new Car();
                cars.add(car.
                        setId(results.getInt("car_id")).
                        setMake(Make.valueOf(results.getString("make"))).
                        setModel(Model.valueOf(results.getString("model"))).
                        setColor(Color.valueOf(results.getString("color"))).
                        setAvailability(Availability.valueOf(results.getString("availability"))));

                return car;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Car update(Car car) {
        String sql = "update cars set make = ?, model = ?, color = ?, availability = ? where car_id = ?";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, car.getMake().name());
            stmt.setString(2, car.getModel().name());
            stmt.setString(3, car.getColor().name());
            stmt.setString(4, car.getAvailability().name());
            stmt.setInt(5, car.getId());

            int success = stmt.executeUpdate();

            if(success == 1){
                return car;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "delete from cars where car_id = " + id;

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet results = stmt.executeQuery();

            if(results.next()){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int count() {
        String sql = "select count(*) from cars";
        int count = 0;

        try (Connection connection = ConnectionUtility.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet results = stmt.executeQuery();

            if(results.next()){
                count = results.getInt("id");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    public List<Car> getCarByMake(Make make){
        List<Car> cars = new ArrayList<>();
        String sql = "select * from cars where make = ?";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, make.name());
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                cars.add(new Car().
                        setMake(Make.valueOf(results.getString("make"))).
                        setModel(Model.valueOf(results.getString("model"))).
                        setColor(Color.valueOf(results.getString("color"))).
                        setAvailability(Availability.valueOf(results.getString("availability"))).
                        setId(results.getInt("user_id")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cars;
    }

    public List<Car> getCarByModel(Model model){
        List<Car> cars = new ArrayList<>();
        String sql = "select * from cars where model = ?";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, model.name());
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                cars.add(new Car().
                        setMake(Make.valueOf(results.getString("make"))).
                        setModel(Model.valueOf(results.getString("model"))).
                        setColor(Color.valueOf(results.getString("color"))).
                        setAvailability(Availability.valueOf(results.getString("availability"))).
                        setId(results.getInt("user_id")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cars;
    }

    public List<Car> getCarByColor(Color color){
        List<Car> cars = new ArrayList<>();
        String sql = "select * from cars where color = ?";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, color.name());
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                cars.add(new Car().
                        setMake(Make.valueOf(results.getString("make"))).
                        setModel(Model.valueOf(results.getString("model"))).
                        setColor(Color.valueOf(results.getString("color"))).
                        setAvailability(Availability.valueOf(results.getString("availability"))).
                        setId(results.getInt("user_id")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cars;
    }

    public List<Car> getCarByAvailability(Availability availability){
        List<Car> cars = new ArrayList<>();
        String sql = "select * from cars where availability = ?";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, availability.name());
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                cars.add(new Car().
                        setMake(Make.valueOf(results.getString("make"))).
                        setModel(Model.valueOf(results.getString("model"))).
                        setColor(Color.valueOf(results.getString("color"))).
                        setAvailability(Availability.valueOf(results.getString("availability"))).
                        setId(results.getInt("user_id")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cars;
    }

    public List<Car> getCarsByUserId(int userId) {
        List<Car> cars = new ArrayList<>();
        String sql = "select * from cars where user_id = ?";

        try(Connection connection = ConnectionUtility.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                cars.add(new Car().
                        setMake(Make.valueOf(results.getString("make"))).
                        setModel(Model.valueOf(results.getString("model"))).
                        setColor(Color.valueOf(results.getString("color"))).
                        setAvailability(Availability.valueOf(results.getString("availability"))).
                        setId(results.getInt("user_id")));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return cars;
    }
}