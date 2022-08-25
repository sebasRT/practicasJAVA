package com.main;
import com.main.persistance.impl.DentistDAO;
import com.main.persistance.util.JDBC_config;
import com.main.service.DentistService;
import org.apache.log4j.Logger;
import java.sql.*;


public class Main {
    public static void main(String[] args) throws SQLException {

        Logger logger = Logger.getLogger(Main.class);

//     ------------------------CREACION DE NUEVA BASE DE DATOS --------------------------    //

        Connection connection = null;
        try {
            connection = new JDBC_config().connection();
        String createSQL = "DROP TABLE IF EXISTS Dentistas;\n"+
                "CREATE TABLE Dentistas (ID INT PRIMARY KEY, NAME VARCHAR (255),LASTNAME VARCHAR (255));";
        Statement statement = connection.createStatement();
        statement.execute(createSQL);
        } catch (SQLException e) {
            logger.error(e);
        }finally {
            connection.close();
        }

        DentistDAO dao = new DentistDAO();
        DentistService dentistService = new DentistService();
        System.out.println
                (" / ---------------------------------- TEST : CREACION DE NUEVOS DENTISTAS -----------------------/");


        dentistService.createDentist("Sebastian","Robayo",1007);
        dentistService.createDentist("Steven","Rayo",3215);
        dentistService.createDentist("Steven","Rayo",3215); // se repite matricula y el usuario deberia no poderse registrar

        System.out.println
                ("//--------------------------------- TEST : LISTADO DE DENTISTAS EN BASE DE DATOS --------------------//");

        dentistService.listDentists();

        System.out.println
                ("//---------------------------------- --TEST : BUSCAR USUARIO POR MATRICULA -----------------------------//");

        dentistService.SearchDentistById(1007);
         dentistService.SearchDentistById(3457); // este usuario no existente
    }
}
