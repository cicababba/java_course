package jdbcClassic;

import java.sql.*;
import java.sql.Connection;


public class ConnectionTest {

    public static void main(String[] args) {
        // Open a connection
        try(Connection conn = DriverManager.getConnection(DbParams.DB_URL, DbParams.USER, DbParams.PASS);) {
            System.out.println(conn.isValid(1));
        } catch (SQLException e) {
            System.out.println("Qualcosa é andato storto");
            e.printStackTrace();
        }
    }
}