package com.main.service;

import com.main.model.Dentist;
import com.main.persistance.impl.DentistDAO;
import java.sql.SQLException;
public class DentistService {

    DentistDAO dao = new DentistDAO();
    public void createDentist(String nombre, String apellido, int matricula){
        try {
            Dentist NewDentist = new Dentist(nombre, apellido, matricula);
            dao.create(NewDentist);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };

    public void listDentists() throws SQLException {
        dao.list();
    }

    public void SearchDentistById(int matricula) throws SQLException {
        dao.SearchById(matricula);
    }
//
}
