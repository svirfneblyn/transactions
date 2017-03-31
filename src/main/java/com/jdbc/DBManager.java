package com.jdbc;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    @Autowired
    private DataSourceProperties props;

    private static Logger logger = LogManager.getRootLogger();

    //connection pool
    private static final List<Connection> conectionPool = new ArrayList<>();
    private static List<String> strList = new ArrayList<>();

    public DBManager() {
        openConnectionPool();
    }

    private Connection openConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(props.getUrl(), props.getUsername(), props.getPassword());
        } catch (SQLException e) {
            logger.info(e);
        }
        return con;
    }

    public void openConnectionPool() {
        for (int i = 0; i < 3; i++) {
            conectionPool.add(openConnection());
        }
    }

    public static List<Connection> getConectionPool() {
        return conectionPool;
    }
    public Connection getConnection(){
        if(!conectionPool.isEmpty()){
            logger.info("connection executed from pool sucessfully...");
            return conectionPool.get(0);
        }
        return null;
    }
}
