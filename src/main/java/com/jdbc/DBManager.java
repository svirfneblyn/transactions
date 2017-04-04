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
/*    @Autowired
    private DataSourceProperties props;*/

    private static Logger logger = LogManager.getRootLogger();

    //connection pool
    private static final List<Connection> conectionPool = new ArrayList<>();
    private static List<String> strList = new ArrayList<>();

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

    public void openConnectionPool(int count) {
        for (int i = 0; i < count; i++) {
            conectionPool.add(openConnection());
        }
    }

    public  List<Connection> getConectionPool() {
        return conectionPool;
    }
    public Connection getConnection(){
        if(!conectionPool.isEmpty()){
            logger.info("connection executed from pool sucessfully...");
            Connection con = conectionPool.get(0);
            conectionPool.remove(0);
            return con;
        }
        return null;
    }


}
