package com.jdbc;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private static Logger logger = LogManager.getRootLogger();

    public DBManager() {
    }

    public Connection openConnection() {
        Connection con = null;
        try {
           // con = DriverManager.getConnection(props.getUrl(), props.getUsername(), props.getPassword());
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jmp?useSSL=false", "root", "12345");

        } catch (SQLException e) {
            logger.info(e);
        }
        return con;
    }

}
