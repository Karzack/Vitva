package SQLTest;
import java.sql.*;

public class MDBApp3 {
    public static void main(String[] args) throws SQLException {
        MDB3 mdb = new MDB3();

        mdb.addCrew(1999, 25, "Åskådare");
        
        mdb.closeConnection();
    }
}
