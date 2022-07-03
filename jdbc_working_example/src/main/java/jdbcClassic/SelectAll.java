package jdbcClassic;

import java.sql.*;

public class SelectAll {
    static final String QUERY = "SELECT * FROM User";

    public static void main(String[] args) {
        // Open a connection
        try(Connection conn = DriverManager.getConnection(DbParams.DB_URL, DbParams.USER, DbParams.PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);) {
            while (rs.next()) {
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", Age: " + rs.getInt("age"));
                System.out.print(", First: " + rs.getString("first"));
                System.out.println(", Last: " + rs.getString("last"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
