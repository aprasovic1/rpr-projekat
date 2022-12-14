package ba.unsa.etf.rpr.rprprojekat;


import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class GetConnection {

    static Connection DajConnection() {

        String[] konekcioniString = new String[3];
        try {
            File myObj = new File(".idea/DBConn.txt");
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                konekcioniString[i] = myReader.nextLine();
                i++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Connection con;
        try {
            con = DriverManager.getConnection(konekcioniString[0], konekcioniString[1], konekcioniString[2]);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

}
