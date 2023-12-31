package com.systechafrica.loginhelper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.systechafrica.dbconfig.DbConnector;

public class LoginHelper {
    java.sql.CallableStatement callableStatement;
    ResultSet resultSet;
    boolean isOwner;

    public boolean checkUserCredentials(String password) {
        try {
            callableStatement=DbConnector.createConnector().getConnection().prepareCall("{CALL selectMember(?)}");
            callableStatement.setString(1, password);
            resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                String storedPassword = resultSet.getString(2);

                if (storedPassword.equals(password)) {
                    isOwner = true;

                } else {
                    isOwner = false;
                }
            }
        } catch (SQLException e) {
            System.out.println("something went terribly wrong");
            e.printStackTrace();
        } finally {
            DbConnector.closeConnection();
        }

        return isOwner;

    }

}
