package com.main.persistance.impl;
import com.main.persistance.IDAO;
import com.main.persistance.util.JDBC_config;
import com.main.model.Dentist;
import org.apache.log4j.Logger;
import java.sql.*;


public class DentistDAO implements IDAO<Dentist>  {
    public static final  Logger logger = Logger.getLogger(DentistDAO.class);
    JDBC_config config =  new JDBC_config();
    @Override
    public Dentist create(Dentist entity) throws SQLException {
        System.out.println("se esta creando un usuario");
        String ask = "INSERT INTO Dentistas VALUES (?,?,?)";
        try {
            Connection connection = config.connection();
            PreparedStatement stm = connection.prepareStatement(ask);
            stm.setInt(1,entity.getMatricula());
            stm.setString(2, entity.getNombre());
            stm.setString(3, entity.getApellido());
            stm.execute();
            logger.info("se añadio correctamente al usuario :" +entity.getNombre()+" "+entity.getApellido());

        } catch (SQLException e) {
            if(e.getMessage().contains("nique index or primary key violation")){
               logger.error("ya hay un usuario registrado con esta matricula");
            }else {
                logger.error(e);
            }
        }finally {
            config.connection().close();
        }
        return entity;
    }

    @Override
    public Dentist SearchById(int id) throws SQLException {
        System.out.println("buscando usuario con matricula: "+ id);
        try {
            Connection connection= config.connection();
            String ask = "SELECT * FROM Dentistas WHERE ID="+ id;
            CallableStatement statement = connection.prepareCall(ask);
            ResultSet rs = statement.executeQuery();
           if (rs.next()){
               logger.info(rs.getString(1)+ " " + rs.getString(2)+ " " + rs.getString(3));
           } else {
               logger.info("el usuario no fue encontrado, rectifica los parametros");
           }
        } catch (SQLException e) {
            logger.error(e);
        }finally {
            config.connection().close();
        }
        return null;
    }

    public void list() throws SQLException {
        System.out.println("se esta consultan en la base datos todos los usuarios registrados ");
        try {
            Connection connection = config.connection();
            String sql = "select * from Dentistas";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
            logger.info(rs.getString(1)+ " " + rs.getString(2)+ " " + rs.getString(3));}
        } catch (SQLException e) {
            logger.error(e);
        }finally {
            config.connection().close();
        }

    }
}
