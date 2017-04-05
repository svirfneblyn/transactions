package com.jdbc;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private static Logger logger = LogManager.getRootLogger();

    public DBManager() {
    }

    public Connection openConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jmp?useSSL=false", "root", "root");

        } catch (SQLException e) {
            logger.info(e);
        }
        return con;
    }

}
