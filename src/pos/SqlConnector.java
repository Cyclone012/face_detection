/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos;

import java.sql.*;

/**
 *
 * @author CycloneTG
 */
public class SqlConnector {

    public Connection dataCon;

    public void ConnectionDB(String ip, String dbName, String user, String password) throws Exception {
        // Load MySQL JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://" + ip + "/" + dbName;
        dataCon = DriverManager.getConnection(url, user, password);
    }
    
    public Connection getConnection() {
        return dataCon;
    }
}
