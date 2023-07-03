/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swing;

import java.sql.Connection;
import java.sql.DriverManager;

public class connectionDB {
   private static Connection mysqlconfig;
    public static Connection configDB(){
        try {
            String url="jdbc:postgresql://localhost:5432/nqh"; //url database
            String user="datle"; //user database
            String pass="123456"; //password database
            mysqlconfig=DriverManager.getConnection(url, user, pass);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (Exception e) {
            System.err.println("Error "+e.getMessage());
        }
        return mysqlconfig;
    }     
}
