package com.main.persistance.util;
import java.sql.*;
import org.apache.log4j.Logger;
public class JDBC_config {
    private String driver;
    private String url;
    private String username;
    private String password;

    private static Logger logger = Logger.getLogger(JDBC_config.class);
    public JDBC_config(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }
    public JDBC_config() {
        this.driver = "org.h2.Driver";
        this.url = "jdbc:h2:" + "./Database/my";
        this.username = "Sa";
        this.password = "";
    }


    public Connection connection() throws SQLException {

        Connection con = null;
        Driver();

        try {
            con = DriverManager.getConnection(url, username, password);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return con;}




    public void Driver (){
        try{ Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        } ;
    }

    public int ExecuteStmt(String statement){
        try{
    Statement stmt = connection().createStatement();

    return 1;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        }
    }
}