package com.revature.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtility {
    private static Connection instance;
    private static Properties properties;

    public static Connection getConnection() throws SQLException{
        if(properties == null){
            properties = loadProperties();
        }

        if(instance == null || instance.isClosed()){
            instance = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("password"));
        }
        return instance;
    }

    public static Properties loadProperties(){
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/db-config.properties");
            properties.load(fileInputStream);
        }catch(IOException e){
            e.printStackTrace();
        }
        return properties;
    }

    private ConnectionUtility(){

    }
}
