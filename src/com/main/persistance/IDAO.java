package com.main.persistance;

import java.sql.SQLException;

public interface IDAO<E> {

    E create (E entity) throws SQLException;
    E SearchById(int id) throws SQLException;

}
