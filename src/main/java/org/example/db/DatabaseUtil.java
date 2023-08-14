package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {
    public static void createTable(){
        String sql="create table if not exists contact("+
                " id serial primary key," +
                "name varchar(25) not null,"+
                "surname varchar(25) not null,"+
                "phone varchar(25) not null unique "+
                ")";
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/contactDb", "postgres", "behzod2428");
           Statement statement= con.createStatement();
           statement.executeUpdate(sql);
           con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
