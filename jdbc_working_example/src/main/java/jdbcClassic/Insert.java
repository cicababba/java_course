package jdbcClassic;

import java.sql.*;

public class Insert {

    public static void main(String[] args) {
        // Open a connection
        try(Connection conn = DriverManager.getConnection(DbParams.DB_URL, DbParams.USER, DbParams.PASS);
            Statement stmt = conn.createStatement();) {
            // Execute a query
            System.out.println("Inserimento in corso...");
            String sql = "INSERT INTO User (`first`, `last`, age) VALUES ('Zara', 'Ali', 18)";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO User (`first`, `last`, age)  VALUES ('Pippo', 'Franco', 22)";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO User (`first`, `last`, age)  VALUES ('Jonny', 'Jonson', 43)";
            stmt.executeUpdate(sql);
            System.out.println("Record inseriti!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
