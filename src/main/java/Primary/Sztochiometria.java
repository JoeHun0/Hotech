package Primary;

import java.sql.*;

public class Sztochiometria {
    private static int gn;
    double c,h,s,o,n,h2o,hamu,hi;

    public void setSztohiometriaStatic() {
        String sql = "SELECT gozpar_be capacity FROM gozpar WHERE gozpar_id =1";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            gn =rs.getInt("capacity");
          //  System.out.println(rs.getInt("capacity"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:src/main/resources/hotech.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // részösszetevők meghatározása végső szilárd tüzelőanyag keverékben
    public void rmvSztk(){

    }
}
