package com.revature.repository;

import com.revature.model.Offer;
import com.revature.model.Status;
import com.revature.util.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfferRepository implements DAO<Offer> {

    private List<Offer> offers;

    public OfferRepository() {
        offers = new ArrayList<>();
    }

    public OfferRepository(List<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public Offer create(Offer offer) {
        String sql = "insert into offers(status, user_id, car_id) values(?,?,?)";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, offer.getStatus().name());
            stmt.setInt(2, offer.getUserId());
            stmt.setInt(3, offer.getCarId());

            int success = stmt.executeUpdate();

            if (success == 1) {
                return offer;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Offer> getAll() {
        List<Offer> offers = new ArrayList<>();
        String sql = "select * from offers";

        try(Connection connection = ConnectionUtility.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            while(result.next()){
                offers.add(new Offer()
                        .setOfferId(result.getInt("offer_id"))
                        .setStatus(Status.valueOf(result.getString("status")))
                        .setUserId(result.getInt("user_id"))
                        .setCarId(result.getInt("car_id")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return offers;
    }

    @Override
    public Offer getById(int id) {
        String sql = "select * from offers where offer_id = " + id;

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                Offer offer = new Offer();
                offers.add(offer.
                        setStatus(Status.valueOf(result.getString("status"))).
                        setUserId(result.getInt("user_id")).
                        setCarId(result.getInt("id")));

                return offer;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Offer update(Offer offer) {
        String sql = "update offers set status = ?, car_id = ?, user_id = ? where offer_id = ?";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, offer.getStatus().name());
            stmt.setInt(2, offer.getCarId());
            stmt.setInt(3, offer.getUserId());
            stmt.setInt(4, offer.getOfferId());


            int success = stmt.executeUpdate();

            if (success == 1) {
                return offer;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "delete from offers where offer_id = " + id;

        try (Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);

            int success = stmt.executeUpdate();

            return success == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int count() {
        String sql = "select count(*) from offers";
        int count = 0;
        try (Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();


            while(result.next()){
                count = result.getInt("count");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<Offer> getOffersByStatus(Status status) {
        List<Offer> offers = new ArrayList<>();
        String sql = "select * from offers where status = ?";

        try (Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, status.name());
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                offers.add(new Offer().
                        setStatus(Status.valueOf(result.getString("status"))).
                        setUserId(result.getInt("user_id")).
                        setCarId(result.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offers;
    }

    public List<Offer> getAllOpenOffersByUserId(int userId) {
        List<Offer> offers = new ArrayList<>();
        String sql = "select * from offers where user_id = ? and status = ?";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setString(2, Status.OPEN.name());
            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                offers.add(new Offer().
                        setStatus(Status.valueOf(results.getString("status"))).
                        setUserId(results.getInt("user_id")).
                        setCarId(results.getInt("id")));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offers;
    }

}
