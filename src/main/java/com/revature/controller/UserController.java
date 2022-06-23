package com.revature.controller;

import com.revature.model.Model;
import com.revature.model.Role;
import com.revature.model.User;
import com.revature.service.UserService;

import io.javalin.http.Handler;
import io.javalin.http.HttpCode;
import org.eclipse.jetty.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class UserController {
    UserService userService = new UserService();

    public Handler getAllUsers = ctx -> {
        List<User> users = userService.getAllUsers();
        ctx.json(users);
    };

    public Handler createNewUser = ctx -> {
        User user = ctx.bodyAsClass(User.class);
        try {
            ctx.status(HttpCode.CREATED).json(userService.createUser(user));
        }catch (NullPointerException | NumberFormatException e){
            ctx.status(HttpCode.BAD_REQUEST).result("Could not create user");
        }
    };

    public Handler getUserById = ctx -> {
        String param = ctx.pathParam("user_id");
        int id = 0;
        try {
            id = Integer.parseInt(param);
            ctx.json(userService.getUserById(id));
        } catch (NumberFormatException e){
            ctx.result("Please input only numbers");
            ctx.status(HttpStatus.BAD_REQUEST_400);
        }catch (NullPointerException e){
            ctx.status(HttpCode.NOT_FOUND).result("User " + id + " could not be found");
        }
    };

    public Handler updateUserById = ctx -> {
        User user = ctx.bodyAsClass(User.class);
        ctx.json(userService.updateUserById(user));
    };

    public Handler deleteUserById = ctx -> {
      String param = ctx.pathParam("id");
      int id = 0;

      try{
          id = Integer.parseInt(param);
          boolean result = userService.deleteUserById(id);
          if (result) {
              ctx.status(HttpCode.OK).result("User " + id + " has been deleted");
          }
          else {
              ctx.status(HttpCode.BAD_REQUEST).result("User " + id + " could not be deleted");
          }
      }catch (NullPointerException e) {
          ctx.status(HttpCode.NOT_FOUND).result("User " + id + " could not found");
      }
    };

    public Handler getUserByRole = ctx -> {
        List<User> users;

        String roleParam = ctx.queryParam("role");

        try {
            Role role = Role.valueOf(roleParam.toUpperCase(Locale.ROOT));
            users = userService.getUserByRole(role);
        }catch (IllegalArgumentException e){
            String failedMessage = "Please enter only the following values: " + Arrays.toString(Model.values());
            ctx.status(400).json(failedMessage);
        }
    };

}