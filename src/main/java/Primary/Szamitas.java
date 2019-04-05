package Primary;

import java.sql.*;

public class Szamitas {
    private static double c,h,s,o,n,h2o,hamu,hi;
    public void szilardOsszetevok(){
        double[] c = new double[5];
        double[] h = new double[5];
        double[] s = new double[5];
        double[] o2 = new double[5];
        double[] n2 = new double[5];
        double[] h2o = new double[5];
        double[] hamu = new double[5];
        double[] hi = new double[5];
        int seg = 0;
        String sql = "select c,h,s,o2,n2,h2o,hamu,hi from tuasz1";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            int seged = 0;
            // loop through the result set
            while (rs.next()) {
                c[seged] = rs.getInt("c");
                h[seged] = rs.getInt("h");
                s[seged] = rs.getInt("s");
                o2[seged] = rs.getInt("o2");
                n2[seged] = rs.getInt("n2");
                h2o[seged] = rs.getInt("h2o");
                hamu[seged] = rs.getInt("hamu");
                hi[seged] = rs.getInt("hi");
                System.out.println(h[seged]);

            }
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
}
