package ba.unsa.etf.rpr.rprprojekat;

import ba.unsa.etf.rpr.rprprojekat.exceptions.myException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

/**
 * Database Connection class implemented using Singleton Design Pattern
 */
public class GetConnection {

    private static Connection con = null;
   public static Connection DajConnection() throws myException, IOException {

        String[] konekcioniString = new String[3];


        Properties p = new Properties();
        p.load(ClassLoader.getSystemResource("application.properties").openStream());
        try {
            con = DriverManager.getConnection(p.getProperty("db.connection_string"), p.getProperty("db.username"), p.getProperty("db.password"));
        } catch (Exception e) {
            throw new myException("Konekcija nije uspjela",e);
        }
        return con;
    }

}
