package jdbcClassic;

import java.sql.*;

public class DeleteTest {
    static final String QUERY = "DELETE FROM User WHERE age=18";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DbParams.DB_URL, DbParams.USER, DbParams.PASS);
             Statement stmt = conn.createStatement();) {
            stmt.execute(QUERY);
            System.out.println("Dati eliminati...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}