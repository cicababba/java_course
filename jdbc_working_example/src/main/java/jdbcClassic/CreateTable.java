package jdbcClassic;

import java.sql.*;

public class CreateTable {

    static String QUERY = "CREATE TABLE qbit.`User` (id BIGINT auto_increment NOT NULL, `first` varchar(100) NULL, `last` varchar(100) NULL, age BIGINT NULL, PRIMARY KEY (id))";

    public static void main(String[] args) {
        // Open a connection
        try(Connection conn = DriverManager.getConnection(DbParams.DB_URL, DbParams.USER, DbParams.PASS);
            Statement stmt = conn.createStatement();
        ) {
            stmt.executeUpdate(QUERY);
            System.out.println("Tabella creata ...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
