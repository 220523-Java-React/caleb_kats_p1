package com.revature.repository;

import com.revature.model.Role;
import com.revature.model.User;
import com.revature.util.ConnectionUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements DAO<User>{

    @Override
    public User create(User user){
        String sql = "insert into users(user_id, first_name, last_name, username, password, role) values(?,?,?,?,?,?)";

        try(Connection connection = ConnectionUtility.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getfName());
            stmt.setString(3, user.getlName());
            stmt.setString(4, user.getUsername());
            stmt.setString(5, user.getPassword());
            stmt.setString(6, user.getRole().name());

            int success = stmt.executeUpdate();

            if (success == 1){
                return user;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAll(){
        List<User> users = new ArrayList<>();
        String sql = "select * from users";

        try(Connection connection = ConnectionUtility.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            while(result.next()){
                users.add(new User()
                        .setId(result.getInt("user_id"))
                        .setfName(result.getString("first_name"))
                        .setlName(result.getString("last_name"))
                        .setUsername(result.getString("username"))
                        .setPassword(result.getString("password"))
                        .setRole(Role.valueOf(result.getString("role"))));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getById(int id){
        List<User> users = new ArrayList<>();
        String sql = "select * from users where id = ?";

        try(Connection connection = ConnectionUtility.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

           if(result.next()) {
               User user = new User();
               users.add(user
                       .setId(result.getInt("id"))
                       .setfName(result.getString("first_name"))
                       .setlName(result.getString("last_name"))
                       .setUsername(result.getString("username"))
                       .setPassword(result.getString("password"))
                       .setRole(Role.valueOf(result.getString("role"))));

               return user;

           }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public User getByUsername(String username){
        List<User> users = new ArrayList<>();
        String sql = "select * from users where username = ?";

        try(Connection connection = ConnectionUtility.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);

            ResultSet result = stmt.executeQuery();

            if(result.next()) {
                User user = new User();
                users.add(user
                        .setId(result.getInt("id"))
                        .setfName(result.getString("first_name"))
                        .setlName(result.getString("last_name"))
                        .setUsername(result.getString("username"))
                        .setPassword(result.getString("password"))
                        .setRole(Role.valueOf(result.getString("role"))));

                return user;

            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User update(User user){

        String sql = "update users set first_name = ?, last_name = ?, username = ?, password = ? where id = ?";

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getfName());
            stmt.setString(2, user.getlName());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, Integer.toString(user.getId()));
            stmt.setString(6, user.getRole().name());
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteById(int id){
        String sql = "delete from users where id = " + id;

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet results = stmt.executeQuery();

            if(results.next()){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int count(){
        String sql = "select count(*) from users";
        int count = 0;

        try(Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

        if(result.next()) {
            count = result.getInt("id");
        }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }

    public List<User> getUserByRole(Role role) {
        List<User> users = new ArrayList<>();
        String sql = "select * from users where role = ?";

        try (Connection connection = ConnectionUtility.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                users.add(new User()
                        .setId(result.getInt("id"))
                        .setfName(result.getString("first_name"))
                        .setlName(result.getString("last_name"))
                        .setUsername(result.getString("username"))
                        .setPassword(result.getString("password"))
                        .setRole(Role.valueOf(result.getString("role"))));

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return users;
    }
}
