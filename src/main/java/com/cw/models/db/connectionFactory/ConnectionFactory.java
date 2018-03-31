package com.cw.models.db.connectionFactory;

import com.mysql.jdbc.Driver;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Макс on 10.03.2018.
 */

public class ConnectionFactory {
    private static Connection connection = null;
    public static final String URL = "jdbc:mysql://localhost:3306/code_wars_db";
    public static final String USER = "root";
    public static final String PASS = "root";

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver());
            if (ConnectionFactory.connection == null) {
                ConnectionFactory.connection = DriverManager.getConnection(URL, USER, PASS);
                return ConnectionFactory.connection;
            } else {
                return ConnectionFactory.connection;
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }
}