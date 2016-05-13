package SQLTest;
import java.sql.*;

public class MDBApp1 {
    public static void main(String[] args) throws SQLException {
        MDB1 mdb = new MDB1();

        for (Object[] row : mdb.getFilms()) {
            System.out.println("Titel: "+row[0]+", År: "+row[1]+", Film-ID: "+row[2]);
        }
        mdb.closeConnection();
    }
}
