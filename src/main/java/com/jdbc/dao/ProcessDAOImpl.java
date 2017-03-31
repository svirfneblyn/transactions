package com.jdbc.dao;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class ProcessDAOImpl implements IProcessDAO {
    private static Logger logger = LogManager.getRootLogger();

    @Override
    public void insertRow(Connection con, String insertQuery) throws SQLException {
        logger.info("insertRow operation begin");
        executeCuery(con, insertQuery);
    }

    @Override
    public void updateRow(Connection con, String updateQuery) throws SQLException {
        executeCuery(con, updateQuery);
    }

    @Override
    public void deletRow(Connection con, String deleteQuery) {
        executeCuery(con, deleteQuery);
    }

    @Override
    public void selecttRow(Connection con, String selectQuery) {
        executeCuery(con, selectQuery);
    }

    @Override
    public void setAutocommit(Connection con, boolean autoCommit) {
        try {
            con.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            logger.info("exception during set autocommit to false \n" + e);
        }
    }

    public void executeCuery(Connection con, String query) {
        Statement st = null;
        try {
            st = con.createStatement();
            st.executeQuery(query);
        } catch (SQLException e) {
            logger.error("execute query error \n" + e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    logger.error("close sttment  error \n" + e);
                }
            }
        }
    }
}
