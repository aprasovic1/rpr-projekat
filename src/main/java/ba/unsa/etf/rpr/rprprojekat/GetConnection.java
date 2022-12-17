package ba.unsa.etf.rpr.rprprojekat;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class GetConnection {

   public static Connection DajConnection() throws IOException {

        String[] konekcioniString = new String[3];

        Connection con = null;
        Properties p = new Properties();
        p.load(ClassLoader.getSystemResource("application.properties").openStream());
        try {
            con = DriverManager.getConnection(p.getProperty("db.connection_string"), p.getProperty("db.username"), p.getProperty("db.password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

}
