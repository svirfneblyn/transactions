package com.jdbc.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Ihar_Rubanovich on 3/29/2017.
 */
public interface IProcessDAO {
    void insertRow(Connection con, String insertQuery) throws SQLException;
    void updateRow(Connection con, String updateQuery) throws SQLException;
    void deletRow(Connection con, String deleteQuery);
    void selecttRow(Connection con, String selectQuery);
    void setAutocommit(Connection con, boolean autoCommit);
}
