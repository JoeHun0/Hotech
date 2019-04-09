package Primary;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.*;
import java.sql.*;
import java.util.Date;

public class Szamitas {
    private static double szilard_c,szilard_h,szilard_s,szilard_o2,szilard_n2,szilard_h2o,szilard_hamu,szilard_hi, gaz_ch4, gaz_c2h6, gaz_c3h8, gaz_c4h10, gaz_cxhy, gaz_co, gaz_h2, gaz_co2, gaz_n2, gaz_o2, gaz_h2s, gaz_h2o, gaz_so2, gaz_hi, gaz_c, gaz_h, gaz_ro,ml0,mco2,mn2,mh2o,mso2,mv0,vl0,vco2,vn2,vh2o,vv0,vso2;
    double[] ro = new double[5];
    double rog = 0;
    String projekt;
    String tuam = "";
    XSSFWorkbook workbook = new XSSFWorkbook();
    File file = new File("kimenet.xlsx");

    public void szilardVagyVegyes(){
        String sql = "select projekt from vegyes";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            int seged = 0;

                projekt = rs.getString("projekt");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {



            XSSFSheet sheet = workbook.createSheet("Általános számítások");
            XSSFRow row = sheet.createRow(0);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue("Projekt megnevezése :");
            sheet.autoSizeColumn(0);
            cell = row.createCell(1);
            cell.setCellValue(projekt);
            row = sheet.createRow(1);
            row.createCell(0).setCellValue("Számítás dátuma : ");
            DataFormat format = workbook.createDataFormat();
            CellStyle dateStyle = workbook.createCellStyle();
            dateStyle.setDataFormat(format.getFormat("yyyy.mm.dd"));
            cell = row.createCell(1);
            cell.setCellStyle(dateStyle);
            cell.setCellValue(new Date());

          //  sheet = workbook.createSheet("Gáz");


            workbook.write(new FileOutputStream(file));
            workbook.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException fe) {
            System.out.println(fe.getMessage());
        }
        int[] tuasza = new int[6];
        int[] tuaga = new int[6];
        boolean tuaszaro = false;
        boolean tuagaro = false;
        String sql1 = "select tuasza from tuasz2";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql1)){
            int seged = 0;
            while (rs.next()) {
                tuasza[seged] = rs.getInt("tuasza");
                seged++;
            }
            for (int i = 1; i <= 5; i++) {
                if (tuasza[i-1] !=0){
                    tuaszaro = true;
                }

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String sql2 = "select tuaga from tuag2";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql2)){
            int seged = 0;
            while (rs.next()) {
                tuaga[seged] = rs.getInt("tuaga");
                seged++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        for (int i = 1; i <= 5; i++) {
            if (tuasza[i-1] !=0){
                tuagaro = true;
            }

        }
   /*     if (tuaszaro ==true && tuagaro == false)
        {
            tuam = "szilard";
        }
        if (tuaszaro == false && tuagaro == true)
        {
            tuam = "gaz";
        }
        if (tuaszaro == true && tuagaro == true)
        {
            tuam = "vegyes";
        }
        if (tuam.equals("szilard")){
            szilardOsszetevok();
        }
        if (tuam.equals("gaz")){
     */        gazOsszetevok();
      //  }

    }

    public void szilardOsszetevok(){
        double[] c = new double[6];
        double[] h = new double[6];
        double[] s = new double[6];
        double[] o2 = new double[6];
        double[] n2 = new double[6];
        double[] h2o = new double[6];
        double[] hamu = new double[6];
        double[] hi = new double[6];
        int[] tuasza = new int[6];
        String sql = "select c,h,s,o2,n2,h2o,hamu,hi from tuasz1";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            int seged = 0;
            while (rs.next()) {
                c[seged] = rs.getDouble("c");
                h[seged] = rs.getDouble("h");
                s[seged] = rs.getDouble("s");
                o2[seged] = rs.getDouble("o2");
                n2[seged] = rs.getDouble("n2");
                h2o[seged] = rs.getDouble("h2o");
                hamu[seged] = rs.getDouble("hamu");
                hi[seged] = rs.getDouble("hi");
                seged++;

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String sql1 = "select tuasza from tuasz2";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql1)){
            int seged = 0;
            while (rs.next()) {
                tuasza[seged] = rs.getInt("tuasza");
                seged++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        double valami = 2;
        c[5] = c[0] * tuasza[0] / 100 + c[1] * tuasza[1] / 100 + c[2] * tuasza[2] / 100 + c[3] * tuasza[3] / 100 + c[4] * tuasza[4] / 100;
        h[5] = h[0] * tuasza[0] / 100 + h[1] * tuasza[1] / 100 + h[2] * tuasza[2] / 100 + h[3] * tuasza[3] / 100 + h[4] * tuasza[4] / 100;
        s[5] = s[0] * tuasza[0] / 100 + s[1] * tuasza[1] / 100 + s[2] * tuasza[2] / 100 + s[3] * tuasza[3] / 100 + s[4] * tuasza[4] / 100;
        o2[5] = o2[0] * tuasza[0] / 100 + o2[1] * tuasza[1] / 100 + o2[2] * tuasza[2] / 100 + o2[3] * tuasza[3] / 100 + o2[4] * tuasza[4] / 100;
        n2[5] = n2[0] * tuasza[0] / 100 + n2[1] * tuasza[1] / 100 + n2[2] * tuasza[2] / 100 + n2[3] * tuasza[3] / 100 + n2[4] * tuasza[4] / 100;
        h2o[5] = h2o[0] * tuasza[0] / 100 + h2o[1] * tuasza[1] / 100 + h2o[2] * tuasza[2] / 100 + h2o[3] * tuasza[3] / 100 + h2o[4] * tuasza[4] / 100;
        hamu[5] = hamu[0] * tuasza[0] / 100 + hamu[1] * tuasza[1] / 100 + hamu[2] * tuasza[2] / 100 + hamu[3] * tuasza[3] / 100 + hamu[5] * tuasza[4] / 100;
        hi[5] = hi[0] * tuasza[0] / 100 + hi[1] * tuasza[1] / 100 + hi[2] * tuasza[2] / 100 + hi[3] * tuasza[3] / 100 + hi[4] * tuasza[4] / 100;

        // Elméleti mennyiségek a gázban szilárd, vagy folyékony tüzelőanyag esetén
        ml0 = 11.484 * (c[5] / 100) + 34.209 * (h[5] / 100) + 4.301 * (s[5] / 100) - 4.31 * (o2[5] / 100);
        mco2 = 3.664 * (c[5] / 100);
        mso2 = 1.998 * (s[5] / 100);
        mn2 = 8.82 * (c[5] / 100) + 26.273 * (h[5] / 100) + 3.303 * (s[5] / 100) + 1 * (n2[5] / 100) - 3.31 * (o2[5] / 100);
        mh2o = 8.936 * (h[5] / 100) + h2o[5] / 100;
        mv0 = mco2 + mso2 + mn2 + mh2o;
        this.szilard_c = c[5];
        this.szilard_h = h[5];
        this.szilard_h = s[5];
        this.szilard_o2 = o2[5];
        this.szilard_n2 = n2[5];
        this.szilard_h2o = h2o[5];
        this.szilard_hamu = hamu[5];
        this.szilard_hi = hi[5];
    }


    public void gazOsszetevok(){
        double[] ch4 = new double[6];
        double[] c2h6 = new double[6];
        double[] c3h8 = new double[6];
        double[] c4h10 = new double[6];
        double[] cxhy = new double[6];
        double[] co = new double[6];
        double[] h2 = new double[6];
        double[] co2 = new double[6];
        double[] n2 = new double[6];
        double[] o2 = new double[6];
        double[] h2s = new double[6];
        double[] h2o = new double[6];
        double[] so2 = new double[6];
        double[] hi = new double[6];
        double[] c = new double[6];
        double[] h = new double[6];
        double[] tuaga = new double[6];
        int[] tuasza = new int[6];
        try {
            FileInputStream inputStream = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(inputStream);

        String sql = "select ch4, c2h6, c3h8, c4h10, cxhy, co, h2, co2, n2, o2, h2s, h2o, so2, hi, c, h, ro from tuag1";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            int seged = 0;
            while (rs.next()) {
                ch4[seged] = rs.getDouble("ch4");
                c2h6[seged] = rs.getDouble("c2h6");
                c3h8[seged] = rs.getDouble("c3h8");
                c4h10[seged] = rs.getDouble("c4h10");
                cxhy[seged] = rs.getDouble("cxhy");
                co[seged] = rs.getDouble("co");
                h2[seged] = rs.getDouble("h2");
                co2[seged] = rs.getDouble("co2");
                n2[seged] = rs.getDouble("n2");
                o2[seged] = rs.getDouble("o2");
                h2s[seged] = rs.getDouble("h2s");
                h2o[seged] = rs.getDouble("h2o");
                so2[seged] = rs.getDouble("so2");
                hi[seged] = rs.getDouble("hi");
                c[seged] = rs.getDouble("c");
                h[seged] = rs.getDouble("h");
                ro[seged] = rs.getDouble("ro");
                seged++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String sql1 = "select tuaga from tuag2";
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql1)){
            int seged = 0;
            while (rs.next()) {
                tuaga[seged] = rs.getInt("tuaga");
                seged++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        double valami = 2;
        ch4[5] = ch4[0] * tuaga[0] / 100 + ch4[1] * tuaga[1] / 100 + ch4[2] * tuaga[2] / 100 + ch4[3] * tuaga[3] / 100 + ch4[4] * tuaga[4] / 100;
        c2h6[5] = c2h6[0] * tuaga[0] / 100 + c2h6[1] * tuaga[1] / 100 + c2h6[2] * tuaga[2] / 100 + c2h6[3] * tuaga[3] / 100 + c2h6[4] * tuaga[4] / 100;
        c3h8[5] = c3h8[0] * tuaga[0] / 100 + c3h8[1] * tuaga[1] / 100 + c3h8[2] * tuaga[2] / 100 + c3h8[3] * tuaga[3] / 100 + c3h8[4] * tuaga[4] / 100;
        c4h10[5] = c4h10[0] * tuaga[0] / 100 + c4h10[1] * tuaga[1] / 100 + c4h10[2] * tuaga[2] / 100 + c4h10[3] * tuaga[3] / 100 + c4h10[4] * tuaga[4] / 100;
        cxhy[5] = cxhy[0] * tuaga[0] / 100 + cxhy[1] * tuaga[1] / 100 + cxhy[2] * tuaga[2] / 100 + cxhy[3] * tuaga[3] / 100 + cxhy[4] * tuaga[4] / 100;
        co[5] = co[0] * tuaga[0] / 100 + co[1] * tuaga[1] / 100 + co[2] * tuaga[2] / 100 + co[3] * tuaga[3] / 100 + co[4] * tuaga[4] / 100;
        h2[5] = h2[0] * tuaga[0] / 100 + h2[1] * tuaga[1] / 100 + h2[2] * tuaga[2] / 100 + h2[3] * tuaga[3] / 100 + h2[4] * tuaga[4] / 100;
        co2[5] = co2[0] * tuaga[0] / 100 + co2[1] * tuaga[1] / 100 + co2[2] * tuaga[2] / 100 + co2[3] * tuaga[3] / 100 + co2[4] * tuaga[4] / 100;
        n2[5] = n2[0] * tuaga[0] / 100 + n2[1] * tuaga[1] / 100 + n2[2] * tuaga[2] / 100 + n2[3] * tuaga[3] / 100 + n2[4] * tuaga[4] / 100;
        o2[5] = o2[0] * tuaga[0] / 100 + o2[1] * tuaga[1] / 100 + o2[2] * tuaga[2] / 100 + o2[3] * tuaga[3] / 100 + o2[4] * tuaga[4] / 100;
        h2s[5] = h2s[0] * tuaga[0] / 100 + h2s[1] * tuaga[1] / 100 + h2s[2] * tuaga[2] / 100 + h2s[3] * tuaga[3] / 100 + h2s[4] * tuaga[4] / 100;
        h2o[5] = h2o[0] * tuaga[0] / 100 + h2o[1] * tuaga[1] / 100 + h2o[2] * tuaga[2] / 100 + h2o[3] * tuaga[3] / 100 + h2o[4] * tuaga[4] / 100;
        so2[5] = so2[0] * tuaga[0] / 100 + so2[1] * tuaga[1] / 100 + so2[2] * tuaga[2] / 100 + so2[3] * tuaga[3] / 100 + so2[4] * tuaga[4] / 100;
        hi[5] = hi[0] * tuaga[0] / 100 + hi[1] * tuaga[1] / 100 + hi[2] * tuaga[2] / 100 + hi[3] * tuaga[3] / 100 + hi[4] * tuaga[4] / 100;
        c[5] = c[0] * tuaga[0] / 100 + c[1] * tuaga[1] / 100 + c[2] * tuaga[2] / 100 + c[3] * tuaga[3] / 100 + c[4] * tuaga[4] / 100;
        h[5] = h[0] * tuaga[0] / 100 + h[1] * tuaga[1] / 100 + h[2] * tuaga[2] / 100 + h[3] * tuaga[3] / 100 + h[4] * tuaga[4] / 100;
        //Gáznemű tüzelőanyagok átlagsűrűségének meghatározása "rog"
            for (int i = 1; i <= 5; i++){
                if (ro[i-1] == 0){
                    ro[i-1] = 0.716 * (ch4[5] / 100) + 1.342 * (c2h6[5] / 100) + 1.967 * (c3h8[5] / 100) + 2.593 * (c4h10[5] / 100) + 2.503 * (cxhy[5] / 100) + 1.25 * (co[5] / 100) + 0.09 * (h2[5] / 100) + 1.977 * (co2[5] / 100) + 1.251 * (n2[5] / 100) + 1.428 * (o2[5] / 100) + 1.5384 * (h2s[5] / 100) + 0.804 * (h2o[5] / 100) + 2.928 * (so2[5] / 100);
                }
            }
        rog = ro[0] * tuaga[0] / 100 + ro[1] * tuaga[1] / 100 + ro[2] * tuaga[2] / 100 + ro[3] * tuaga[3] / 100 + ro[4] * tuaga[4] / 100;

        //   Fűstáz összetevők meghatározása (m3/m3) m3 összetevő / m3 tüzelőanyag
        vl0 = (ch4[5] / 100) * 9.524 + (c2h6[5] / 100) * 16.666 + (c3h8[5] / 100) * 23.81 + (c4h10[5] / 100) * 30.952 + (cxhy[5] / 100) * 28.571;
        // fajlagos levegő szükséglet m3/m3
        vco2 = (ch4[5] / 100) + (c2h6[5] / 100) * 2 + (c3h8[5] / 100) * 3 + (c4h10[5] / 100) * 4 + (cxhy[5] / 100) * 4 + co2[5] / 100;
        vn2 = (ch4[5] / 100) * 7.524 + (c2h6[5] / 100) * 13.166 + (c3h8[5] / 100) * 18.81 + (c4h10[5] / 100) * 24.452 + (cxhy[5] / 100) * 22.571 + n2[5] / 100;
        vh2o = (ch4[5] / 100) * 2 + (c2h6[5] / 100) * 3 + (c3h8[5] / 100) * 4 + (c4h10[5] / 100) * 5 + (cxhy[5] / 100) * 4;
        //   keletkezett fajlagos füstgázmennyiség m3 füstgáz / m3 tüzelőanyag
        vv0 = vco2 + vn2 + vh2o + vso2;
        ch4[5] = ch4[5] * 0.716 / rog;
        c2h6[5] = c2h6[5] * 1.342 / rog;
        c3h8[5] = c3h8[5] * 1.967 / rog;
        c4h10[5] = c4h10[5] * 2.593 / rog;
        cxhy[5] = cxhy[5] * 2.503 / rog;
        co[5] = co[5] * 1.25 / rog;
        h2[5] = h2[5] * 0.09 / rog;
        co2[5] = co2[5] * 1.977 / rog;
        n2[5] = n2[5] * 1.251 / rog;
        o2[5] = o2[5] * 1.428 / rog;
        h2s[5] = h2s[5] * 1.5384 / rog;
        h2o[5] = h2o[5] * 0.804 / rog;
        so2[5] = so2[5] * 2.928 / rog;
        hi[5] = hi[5] / rog;
        //   Fűstáz összetevők meghatározása (kg/kg) kg összetevő / kg tüzelőanyg
        ml0 = 17.196 * (ch4[5] / 100) + 16.056 * (c2h6[5] / 100) + 15.64 * (c3h8[5] / 100) + 15.426 * (c4h10[5] / 100) + 14.751 * (cxhy[5] / 100) + 2.461 * (co[5] / 100) + 34.206 * (h2[5] / 100) - 4.31 * (o2[5] / 100) + 6.071 * (h2s[5] / 100);
        // fajlagos levegő szükséglet m3/m3
        mco2 = 2.743 * (ch4[5] / 100) + 2.927 * (c2h6[5] / 100) + 2.994 * (c3h8[5] / 100) + 3.029 * (c4h10[5] / 100) + 3.138 * (cxhy[5] / 100) + 1.571 * (co[5] / 100) + (co2[5] / 100);
        mn2 = 13.207 * (ch4[5] / 100) + 12.331 * (c2h6[5] / 100) + 12.012 * (c3h8[5] / 100) + 11.847 * (c4h10[5] / 100) + 11.328 * (cxhy[5] / 100) + 1.89 * (co[5] / 100) + 26.271 * (h2[5] / 100) + 1 * (n2[5] / 100) - 3.31 * (o2[5] / 100) + 4.662 * (h2s[5] / 100);
        mh2o = 2.246 * (ch4[5] / 100) + 1.798 * (c2h6[5] / 100) + 1.634 * (c3h8[5] / 100) + 1.55 * (c4h10[5] / 100) + 1.258 * (cxhy[5] / 100) + 8.935 * (h2[5] / 100) + h2o[5] / 100;
        mso2 = 1.88 * h2s[5] / 100 + so2[5] / 100;
        //   keletkezett fajlagos füstgázmennyiség kg füstgáz / kg tüzelőanyag
        mv0 = mco2 + mn2 + mh2o + mso2;



        this.gaz_ch4 = ch4[5];
        this.gaz_c2h6 = c2h6[5];
        this.gaz_c3h8 = c3h8[5];
        this.gaz_c4h10 = c4h10[5];
        this.gaz_cxhy = cxhy[5];
        this.gaz_co = co[5];
        this.gaz_h2 = h2[5];
        this.gaz_co2 = co2[5];
        this.gaz_n2 = n2[5];
        this.gaz_o2 = o2[5];
        this.gaz_h2s = h2s[5];
        this.gaz_h2o = h2o[5];
        this.gaz_so2 = so2[5];
        this.gaz_hi = hi[5];
        this.gaz_c = c[5];
        this.gaz_h = h[5];
            Sheet sheet = workbook.createSheet("Gáz");
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("fajlagos levegő szükséglet :");
            sheet.autoSizeColumn(0);
            cell = row.createCell(1);
            System.out.println(ml0);
            cell.setCellValue(ml0);
            cell = row.createCell(2);
            cell.setCellValue("m3/m3");
             row = sheet.createRow(1);
            row = sheet.createRow(0);
            cell = row.createCell(0);
            cell.setCellValue("keletkezett fajlagos füstgázmennyiség :");
            sheet.autoSizeColumn(0);
            cell = row.createCell(1);
            cell.setCellValue(mv0);
            cell = row.createCell(2);
            cell.setCellValue("kg/kg");

            // row.createCell(0).setCellValue("Számítás dátuma : ");
            // DataFormat format = workbook.createDataFormat();
            // CellStyle dateStyle = workbook.createCellStyle();
            // dateStyle.setDataFormat(format.getFormat("yyyy.mm.dd"));
            // cell = row.createCell(1);
            // cell.setCellStyle(dateStyle);
            // cell.setCellValue(new Date());

            inputStream.close();

            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();

        } catch (IOException | EncryptedDocumentException
                | InvalidFormatException ex) {
            ex.printStackTrace();
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
