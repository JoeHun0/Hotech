package Primary;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Math.*;
import static java.lang.StrictMath.sqrt;

public class Szamitas {
    private static double c, h, szilard_s, szilard_o2, szilard_n2, szilard_h2o, hi, gaz_ch4, gaz_c2h6, gaz_c3h8, gaz_c4h10, gaz_cxhy, gaz_co, gaz_h2, gaz_co2, gaz_n2, gaz_o2, gaz_h2s, gaz_h2o, gaz_so2, gaz_c, gaz_h, gaz_ro, ml0, mco2, mn2, mh2o, mso2, mv0, vl0, vco2, vn2, vh2o, vv0, vv, vo2, rofgg, mup, rofg, hk;
    double szilard_hamu = 0.0;
    double a = 0.0;
    double b = 0.0;
    double g = 0.0;
    long tetakk= 0;
    double[] ro = new double[5];
    double vso2 = 0.0;
    Double m;
    double rog = 0;
    String projekt;
    String tuam = "";
    XSSFWorkbook workbook = new XSSFWorkbook();
    File file = new File("kimenet.xlsx");
    double mv = 0.0;
    double mo2 = 0.0;



    public void szilardVagyVegyes() {
        double[] lambda_be = new double[2];
        int[] tuasza = new int[6];
        int[] tuaga = new int[6];
        String sql4 = "select tuasza from tuasz2";
        double m = 0.0;


        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql4)) {
            int seged = 0;
            while (rs.next()) {
                tuasza[seged] = rs.getInt("tuasza");
                seged++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String sql5 = "select tuaga from tuag2";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql5)) {
            int seged = 0;
            while (rs.next()) {
                tuaga[seged] = rs.getInt("tuaga");
                seged++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        int[] gozpar = new int[5];
        String sql3 = "select gozpar_be from gozpar";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql3)) {
            int seged = 0;
            for (int i = 0; i != 5; i++) {
                rs.next();
                gozpar[i] = rs.getInt("gozpar_be");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String sql = "select projekt from vegyes";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            int seged = 0;

            projekt = rs.getString("projekt");


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        this.g=gozpar[1];
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
            row = sheet.createRow(3);
            row.createCell(0).setCellValue("Kazán névleges teljesítménye : ");
            row.createCell(1).setCellValue(gozpar[0]);
            row.createCell(2).setCellValue("t/h");
            row = sheet.createRow(4);
            row.createCell(0).setCellValue("Kazán teljesítménye : ");
            row.createCell(1).setCellValue(gozpar[1]);
            row.createCell(2).setCellValue("t/h");
            row = sheet.createRow(5);
            row.createCell(0).setCellValue("Kazánból kilépő gőz nyomása : ");
            sheet.autoSizeColumn(0);
            row.createCell(1).setCellValue(gozpar[2]);
            row.createCell(2).setCellValue("bar");
            row = sheet.createRow(6);
            row.createCell(0).setCellValue("Kazánba belépő tápvíz hőmérséklete : ");
            row.createCell(1).setCellValue(gozpar[3]);
            row.createCell(2).setCellValue("C");
            row = sheet.createRow(7);
            row.createCell(0).setCellValue("Kazán teljesítménye : ");
            row.createCell(1).setCellValue(gozpar[4]);
            row.createCell(2).setCellValue("t/h");
            row = sheet.createRow(9);
            row.createCell(0).setCellValue("Tüzelő anyag arányok szilárd ");
            row = sheet.createRow(10);
            row.createCell(0).setCellValue("Szén tüzelőanyag : ");
            row.createCell(1).setCellValue(tuasza[0]);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(11);
            row.createCell(0).setCellValue("Nád tüzelőanyag : ");
            row.createCell(1).setCellValue(tuasza[1]);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(12);
            row.createCell(0).setCellValue("Fa tüzelőanyag : ");
            row.createCell(1).setCellValue(tuasza[2]);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(13);
            row.createCell(0).setCellValue("Olaj tüzelőanyag : ");
            row.createCell(1).setCellValue(tuasza[3]);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(14);
            row.createCell(0).setCellValue("Alternatív tüzelőanyag : ");
            row.createCell(1).setCellValue(tuasza[4]);
            row.createCell(2).setCellValue("%");

            row = sheet.createRow(16);
            row.createCell(0).setCellValue("Tüzelő anyag arányok gáz ");
            row = sheet.createRow(17);
            row.createCell(0).setCellValue("Földgáz : ");
            row.createCell(1).setCellValue(tuaga[0]);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(18);
            row.createCell(0).setCellValue("alternatív földgáz : ");
            row.createCell(1).setCellValue(tuaga[1]);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(19);
            row.createCell(0).setCellValue("hidrogéngáz : ");
            row.createCell(1).setCellValue(tuaga[2]);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(20);
            row.createCell(0).setCellValue("Bio gáz : ");
            row.createCell(1).setCellValue(tuaga[3]);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(21);
            row.createCell(0).setCellValue("Egyéb gáz : ");
            row.createCell(1).setCellValue(tuaga[4]);
            row.createCell(2).setCellValue("%");


            workbook.write(new FileOutputStream(file));
            workbook.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException fe) {
            System.out.println(fe.getMessage());
        }
        boolean tuaszaro = false;
        boolean tuagaro = false;
        for (int i = 0; i != 5; i++) {
            if (tuasza[i] != 0) {
                tuaszaro = true;
            }

        }

        for (int i = 0; i != 5; i++) {
            if (tuaga[i] != 0) {
                tuagaro = true;
            }

        }
        System.out.println(tuaszaro);
        System.out.println(tuagaro);
        String sql6 = "select lambda_be from lambda";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql6)) {
            int seged = 0;
            while (rs.next()) {
                lambda_be[seged] = rs.getDouble("lambda_be");
                seged++;
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if (tuaszaro == true && tuagaro == false) {
            tuam = "szilard";
        }
        if (tuaszaro == false && tuagaro == true) {
            tuam = "gaz";
        }
        if (tuaszaro == true && tuagaro == true) {
            tuam = "vegyes";
        }
        this.tuam = tuam;
        System.out.println("akármi");
        if (tuam.equals("szilard")) {
            m = lambda_be[0];

            szilardOsszetevok();
        }
        if (tuam.equals("gaz")) {
            m = lambda_be[1];
            this.m = m;
            gazOsszetevok();
            System.out.println(m);
            fojtatás();
        }
    }


    public void szilardOsszetevok() {
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
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
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
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql1)) {
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
        this.ml0 = 11.484 * (c[5] / 100) + 34.209 * (h[5] / 100) + 4.301 * (s[5] / 100) - 4.31 * (o2[5] / 100);
        this.mco2 = 3.664 * (c[5] / 100);
        this.mso2 = 1.998 * (s[5] / 100);
        this.mn2 = 8.82 * (c[5] / 100) + 26.273 * (h[5] / 100) + 3.303 * (s[5] / 100) + 1 * (n2[5] / 100) - 3.31 * (o2[5] / 100);
        this.mh2o = 8.936 * (h[5] / 100) + h2o[5] / 100;
        this.mv0 = this.mco2 + this.mso2 + this.mn2 + this.mh2o;
        this.c = c[5];
        this.h = h[5];
        this.szilard_s = s[5];
        this.szilard_o2 = o2[5];
        this.szilard_n2 = n2[5];
        this.szilard_h2o = h2o[5];
        this.szilard_hamu = hamu[5];
        this.hi = hi[5];
        this.ml0 = 11.484 * (c[5] / 100) + 34.209 * (h[5] / 100) + 4.301 * (s[5] / 100) - 4.31 * (o2[5] / 100);
        this.mco2 = 3.664 * (c[5] / 100);
        this.mso2 = 1.998 * (s[5] / 100);
        this.mn2 = 8.82 * (c[5] / 100) + 26.273 * (h[5] / 100) + 3.303 * (s[5] / 100) + 1 * (n2[5] / 100) - 3.31 * (o2[5] / 100);
        this.mh2o = 8.936 * (h[5] / 100) + h2o[5] / 100;
        this.mv0 = this.mco2 + this.mso2 + this.mn2 + this.mh2o;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);


            Row row = sheet.createRow(23);
            row.createCell(0).setCellValue("Szilárd tüzelőanyag esetén :");
            row = sheet.createRow(24);
            row.createCell(0).setCellValue("Karbon : ");
            row.createCell(1).setCellValue(c[5]);
            row.createCell(2).setCellValue("(kg/kg)");
            row = sheet.createRow(25);
            row.createCell(0).setCellValue("hidrogén : ");
            row.createCell(1).setCellValue(h[5]);
            row.createCell(2).setCellValue("(kg/kg)");
            row = sheet.createRow(26);
            row.createCell(0).setCellValue("kén : ");
            row.createCell(1).setCellValue(s[5]);
            row.createCell(2).setCellValue("(kg/kg)");
            row = sheet.createRow(27);
            row.createCell(0).setCellValue("Oxigén : ");
            row.createCell(1).setCellValue(o2[5]);
            row.createCell(2).setCellValue("(kg/kg)");
            row = sheet.createRow(28);
            row.createCell(0).setCellValue("Nitrogén : ");
            row.createCell(1).setCellValue(n2[5]);
            row.createCell(2).setCellValue("(kg/kg)");
            row = sheet.createRow(29);
            row.createCell(0).setCellValue("Nedvesség : ");
            row.createCell(1).setCellValue(h2o[5]);
            row.createCell(2).setCellValue("(kg/kg)");
            row = sheet.createRow(30);
            row.createCell(0).setCellValue("Hamu : ");
            row.createCell(1).setCellValue(hamu[5]);
            row.createCell(2).setCellValue("(kg/kg)");
            row = sheet.createRow(31);
            row.createCell(0).setCellValue("Fűtőérték : ");
            row.createCell(1).setCellValue(hi[5]);
            row.createCell(2).setCellValue("kj/kg");

            row = sheet.createRow(33);
            row.createCell(0).setCellValue("Elméleti mennyiségek a füstgázban szilárd, vagy folyékony tüzelőanyag esetén (kg füstgáz/ kg tüzelőanyag)");
            row = sheet.createRow(34);
            row.createCell(0).setCellValue("Levegő mennyiség");
            row.createCell(1).setCellValue(this.ml0);
            row.createCell(2).setCellValue("(kg/kg)");
            row = sheet.createRow(35);
            row.createCell(0).setCellValue("mco2 mennyiség");
            row.createCell(1).setCellValue(this.mco2);
            row.createCell(2).setCellValue("(kg/kg)");
            row = sheet.createRow(36);
            row.createCell(0).setCellValue("mso2 mennyiség");
            row.createCell(1).setCellValue(this.mso2);
            row.createCell(2).setCellValue("(kg/kg)");
            row = sheet.createRow(37);
            row.createCell(0).setCellValue("mno2 mennyiség");
            row.createCell(1).setCellValue(this.mn2);
            row.createCell(2).setCellValue("(kg/kg)");
            row = sheet.createRow(38);
            row.createCell(0).setCellValue("mh2o mennyiség");
            row.createCell(1).setCellValue(this.mh2o);
            row.createCell(2).setCellValue("(kg/kg)");


            workbook.write(new FileOutputStream(file));
            workbook.close();
        } catch (InvalidFormatException ie) {
            System.out.println(ie.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException fe) {
            System.out.println(fe.getMessage());
        }

    }


    public void gazOsszetevok() {
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
            Sheet sheet = workbook.getSheetAt(0);

            String sql = "select ch4, c2h6, c3h8, c4h10, cxhy, co, h2, co2, n2, o2, h2s, h2o, so2, hi, c, h, ro from tuag1";

            try (Connection conn = this.connect();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
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
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql1)) {
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
            for (int i = 1; i <= 5; i++) {
                if (ro[i - 1] == 0) {
                    ro[i - 1] = 0.716 * (ch4[5] / 100) + 1.342 * (c2h6[5] / 100) + 1.967 * (c3h8[5] / 100) + 2.593 * (c4h10[5] / 100) + 2.503 * (cxhy[5] / 100) + 1.25 * (co[5] / 100) + 0.09 * (h2[5] / 100) + 1.977 * (co2[5] / 100) + 1.251 * (n2[5] / 100) + 1.428 * (o2[5] / 100) + 1.5384 * (h2s[5] / 100) + 0.804 * (h2o[5] / 100) + 2.928 * (so2[5] / 100);
                }
            }
            rog = ro[0] * tuaga[0] / 100 + ro[1] * tuaga[1] / 100 + ro[2] * tuaga[2] / 100 + ro[3] * tuaga[3] / 100 + ro[4] * tuaga[4] / 100;


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
            this.hi = hi[5];
            this.c = c[5];
            this.h = h[5];

            Row row = sheet.createRow(40);
            row.createCell(0).setCellValue("Gáz tüzelőanyag esetén :");
            row = sheet.createRow(41);
            row.createCell(0).setCellValue("ch4 : ");
            row.createCell(1).setCellValue(ch4[5]);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(42);
            row.createCell(0).setCellValue("c2h6 : ");
            row.createCell(1).setCellValue(c2h6[5]);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(43);
            row.createCell(0).setCellValue("c3h8 : ");
            row.createCell(1).setCellValue(c3h8[5]);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(44);
            row.createCell(0).setCellValue("c4h10 : ");
            row.createCell(1).setCellValue(c4h10[5]);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(45);
            row.createCell(0).setCellValue("cxhy : ");
            row.createCell(1).setCellValue(cxhy[5]);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(46);
            row.createCell(0).setCellValue("co : ");
            row.createCell(1).setCellValue(co[5]);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(47);
            row.createCell(0).setCellValue("h2 : ");
            row.createCell(1).setCellValue(h2[5]);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(48);
            row.createCell(0).setCellValue("co2 : ");
            row.createCell(1).setCellValue(co2[5]);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(49);
            row.createCell(0).setCellValue("n2 : ");
            row.createCell(1).setCellValue(n2[5]);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(50);
            row.createCell(0).setCellValue("o2 : ");
            row.createCell(1).setCellValue(o2[5]);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(51);
            row.createCell(0).setCellValue("h2s : ");
            row.createCell(1).setCellValue(h2s[5]);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(52);
            row.createCell(0).setCellValue("h2o : ");
            row.createCell(1).setCellValue(h2o[5]);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(53);
            row.createCell(0).setCellValue("so2 : ");
            row.createCell(1).setCellValue(so2[5]);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(54);
            row.createCell(0).setCellValue("hi : ");
            row.createCell(1).setCellValue(hi[5]);
            row.createCell(2).setCellValue("kj/kg");
            row = sheet.createRow(55);
            row.createCell(0).setCellValue("c : ");
            row.createCell(1).setCellValue(c[5]);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(56);
            row.createCell(0).setCellValue("h : ");
            row.createCell(1).setCellValue(h[5]);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(57);
            row.createCell(0).setCellValue("rog : ");
            row.createCell(1).setCellValue(rog);
            row.createCell(2).setCellValue("kg/Nm3");

            row = sheet.createRow(83);
            row.createCell(0).setCellValue("Összesen : ");
            row.createCell(1).setCellValue(this.vo2/*+this.vco2+this.vn2+vso2+vh2o*/);
            row.createCell(2).setCellValue("kg/Nm3");


            this.gaz_ch4 = ch4[5] * 0.716 / rog;
            this.gaz_c2h6 = c2h6[5] * 1.342 / rog;
            this.gaz_c3h8 = c3h8[5] * 1.967 / rog;
            this.gaz_c4h10 = c4h10[5] * 2.593 / rog;
            this.gaz_cxhy = cxhy[5] * 2.503 / rog;
            this.gaz_co = co[5] * 1.25 / rog;
            this.gaz_h2 = h2[5] * 0.09 / rog;
            this.gaz_co2 = co2[5] * 1.977 / rog;
            this.gaz_n2 = n2[5] * 1.251 / rog;
            this.gaz_o2 = o2[5] * 1.428 / rog;
            this.gaz_h2s = h2s[5] * 1.5384 / rog;
            this.gaz_h2o = h2o[5] * 0.804 / rog;
            this.gaz_so2 = so2[5] * 2.928 / rog;
            this.hi = hi[5] / rog;

            //   Fűstáz összetevők meghatározása (m3/m3) m3 összetevő / m3 tüzelőanyag
            this.vl0 = (ch4[5] / 100) * 9.524 + (c2h6[5] / 100) * 16.666 + (c3h8[5] / 100) * 23.81 + (c4h10[5] / 100) * 30.952 + (cxhy[5] / 100) * 28.571;

            // fajlagos levegő szükséglet m3/m3
            this.vco2 = (ch4[5] / 100) + (c2h6[5] / 100) * 2 + (c3h8[5] / 100) * 3 + (c4h10[5] / 100) * 4 + (cxhy[5] / 100) * 4 + co2[5] / 100;
            this.vn2 = (ch4[5] / 100) * 7.524 + (c2h6[5] / 100) * 13.166 + (c3h8[5] / 100) * 18.81 + (c4h10[5] / 100) * 24.452 + (cxhy[5] / 100) * 22.571 + n2[5] / 100;
            this.vh2o = (ch4[5] / 100) * 2 + (c2h6[5] / 100) * 3 + (c3h8[5] / 100) * 4 + (c4h10[5] / 100) * 5 + (cxhy[5] / 100) * 4;
            //   keletkezett fajlagos füstgázmennyiség m3 füstgáz / m3 tüzelőanyag
            this.vv0 = this.vco2 + this.vn2 + this.vh2o + this.vso2;

            this.vv = this.vv0 + (this.m - 1) * this.vl0;
            // fajlagos mennyiségek a füstgázra vonatkozóan (m3 összetevő / m3 füstgáz)
            this.vo2 = (this.m - 1) * 0.21 * this.vl0 / vv;
            this.vco2 = this.vco2 / vv;
            this.vso2 = this.vso2 / vv;
            this.vn2 = this.vn2 / vv + (this.m - 1) * 0.79 * this.vl0 / this.vv;
            this.vh2o = this.vh2o / vv;
            this.rofgg = (this.vco2 * 1.977 + this.vn2 * 1.251 + this.vh2o * 0.804 + this.vo2 * 1.428) / (this.vco2 + this.vn2 + this.vh2o + this.vo2);


            //   Fűstáz összetevők meghatározása (kg/kg) kg összetevő / kg tüzelőanyg
            this.ml0 = 17.196 * (this.gaz_ch4 / 100) + 16.056 * (this.gaz_c2h6 / 100) + 15.64 * (this.gaz_c3h8 / 100) + 15.426 * (this.gaz_c4h10 / 100) + 14.751 * (this.gaz_cxhy / 100) + 2.461 * (this.gaz_co / 100) + 34.206 * (this.gaz_h2 / 100) - 4.31 * (this.gaz_o2 / 100) + 6.071 * (this.gaz_h2s / 100);
            // fajlagos levegő szükséglet m3/m3
            this.mco2 = 2.743 * (this.gaz_ch4 / 100) + 2.927 * (this.gaz_c2h6 / 100) + 2.994 * (this.gaz_c3h8 / 100) + 3.029 * (this.gaz_c4h10 / 100) + 3.138 * (this.gaz_cxhy / 100) + 1.571 * (this.gaz_co / 100) + (co2[5] / 100);
            this.mn2 = 13.207 * (this.gaz_ch4 / 100) + 12.331 * (this.gaz_c2h6 / 100) + 12.012 * (this.gaz_c3h8 / 100) + 11.847 * (this.gaz_c4h10 / 100) + 11.328 * (this.gaz_cxhy / 100) + 1.89 * (this.gaz_co / 100) + 26.271 * (this.gaz_h2 / 100) + 1 * (this.gaz_n2 / 100) - 3.31 * (this.gaz_o2 / 100) + 4.662 * (this.gaz_h2s / 100);
            this.mh2o = 2.246 * (this.gaz_ch4 / 100) + 1.798 * (this.gaz_c2h6 / 100) + 1.634 * (this.gaz_c3h8 / 100) + 1.55 * (this.gaz_c4h10 / 100) + 1.258 * (this.gaz_cxhy / 100) + 8.935 * (this.gaz_h2 / 100) + this.gaz_h2o / 100;
            this.mso2 = 1.88 * this.gaz_h2s / 100 + this.gaz_so2 / 100;
            //   keletkezett fajlagos füstgázmennyiség kg füstgáz / kg tüzelőanyag
            this.mv0 = this.mco2 + this.mn2 + this.mh2o + this.mso2;


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

    private void fojtatás() {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            this.mv = this.mv0 + (this.m - 1) * this.ml0;
            this.mo2 = (this.m - 1) * 0.238 * this.ml0 / this.mv;
            this.mco2 = this.mco2 / this.mv;
            this.mso2 = this.mso2 / this.mv;
            this.mn2 = this.mn2 / this.mv + (this.m - 1) * 0.762 * this.ml0 / this.mv;
            this.mh2o = mh2o / this.mv;
            this.mup = 0.9 * szilard_hamu / this.mv;
            rofg = 1 / (this.mco2 / 1.977 + this.mso2 / 2.928 + this.mn2 / 1.251 + this.mo2 / 1.428 + this.mh2o / 0.804);
            allandok1();



            Row row = sheet.createRow(65);
            row.createCell(0).setCellValue("Fajlagos levegőmennyiség 1 kg tüzelőanyaghoz (ml0) : ");
            row.createCell(1).setCellValue(ml0);
            row.createCell(2).setCellValue("(kg/kg)");
            row = sheet.createRow(66);
            row.createCell(0).setCellValue("Fajlagos füstgázmennyiség 1 kg tüzelőanyaghoz(mv0) : ");
            row.createCell(1).setCellValue(mv0);
            row.createCell(2).setCellValue("(kg/kg)");

            row = sheet.createRow(61);
            row.createCell(0).setCellValue("Légfeleslegtényező a tűztérben : ");
            row.createCell(1).setCellValue(m);
            row.createCell(2).setCellValue("-");
            row = sheet.createRow(62);
            row.createCell(0).setCellValue("O2 tartalom a tűztérben nedves füstgázra vonatkoztatva % : ");
            row.createCell(1).setCellValue((mo2 / 1.428) * 100);

            row = sheet.createRow(68);
            row.createCell(0).setCellValue("Fajlagos értékek a füstgázban légfelesleggel (kg összetevő/kg füstgáz)");
            row = sheet.createRow(69);
            row.createCell(0).setCellValue("Oxigén mo2");
            row.createCell(1).setCellValue((this.mo2));
            row.createCell(2).setCellValue("(kg/kg)");
            row = sheet.createRow(70);
            row.createCell(0).setCellValue("Széndioxid mco2");
            row.createCell(1).setCellValue((this.mco2));
            row.createCell(2).setCellValue("(kg/kg)");
            row = sheet.createRow(71);
            row.createCell(0).setCellValue("Kéndioxid mso2");
            row.createCell(1).setCellValue((this.mso2));
            row.createCell(2).setCellValue("(kg/kg)");
            row = sheet.createRow(72);
            row.createCell(0).setCellValue("Nitrogén mn2");
            row.createCell(1).setCellValue((this.mn2));
            row.createCell(2).setCellValue("(kg/kg)");
            row = sheet.createRow(73);
            row.createCell(0).setCellValue("Vízgőz mh2o");
            row.createCell(1).setCellValue((this.mh2o));
            row.createCell(2).setCellValue("(kg/kg)");
            row = sheet.createRow(74);
            row.createCell(0).setCellValue("Füstgáz sűrűség");
            row.createCell(1).setCellValue((this.rofg));
            row.createCell(2).setCellValue("(kg/kg)");

            row = sheet.createRow(76);
            row.createCell(0).setCellValue("Gáz tüzelőanyag esetén");
            row = sheet.createRow(77);
            row.createCell(0).setCellValue("Fajlagos értékek a füstgázban légfelesleggel (m3/m3)");
            row = sheet.createRow(78);
            row.createCell(0).setCellValue("vv (füstgáz mennyiség 1 m3 tüzelőanyagból (m3))");
            row.createCell(1).setCellValue((this.vv));
            row.createCell(2).setCellValue("(kg/kg)");
            row = sheet.createRow(79);
            row.createCell(0).setCellValue("vo2");
            row.createCell(1).setCellValue((this.vo2));
            row.createCell(2).setCellValue("(m3/m3)");
            row = sheet.createRow(80);
            row.createCell(0).setCellValue("vco2");
            row.createCell(1).setCellValue((vco2));
            row.createCell(2).setCellValue("(m3/m3)");
            row = sheet.createRow(81);
            row.createCell(0).setCellValue("vso2");
            row.createCell(1).setCellValue((vso2));
            row.createCell(2).setCellValue("(m3/m3)");
            row = sheet.createRow(82);
            row.createCell(0).setCellValue("vn2");
            row.createCell(1).setCellValue((vn2));
            row.createCell(2).setCellValue("(m3/m3)");
            row = sheet.createRow(83);
            row.createCell(0).setCellValue("vh2o");
            row.createCell(1).setCellValue((this.vh2o));
            row.createCell(2).setCellValue("(m3/m3)");
            row = sheet.createRow(84);
            row.createCell(0).setCellValue("Összesen");
            row.createCell(1).setCellValue((this.rofg));
            row.createCell(2).setCellValue("(m3/m3)");
            row = sheet.createRow(85);
            row.createCell(0).setCellValue("Füstgáz sűrűsége");
            row.createCell(1).setCellValue((this.rofgg));
            row.createCell(2).setCellValue("(kg/nm3)");

            row = sheet.createRow(87);
            row.createCell(0).setCellValue("Fajlagos értékek a füstgázban légfelesleggel (mg/m3)");
            row = sheet.createRow(88);
            row.createCell(0).setCellValue("po2");
            row.createCell(1).setCellValue((this.vo2 * 1.428 * 1000));
            row = sheet.createRow(89);
            row.createCell(0).setCellValue("pco2");
            row.createCell(1).setCellValue((this.vco2 * 1.977 * 1000));
            row = sheet.createRow(90);
            row.createCell(0).setCellValue("pn2");
            row.createCell(1).setCellValue((this.vn2 * 1.251 * 1000));
            row = sheet.createRow(91);
            row.createCell(0).setCellValue("ph2o");
            row.createCell(1).setCellValue((this.vh2o * 0.804 * 1000));


            workbook.write(new FileOutputStream(file));
            workbook.close();
        } catch (InvalidFormatException ie) {
            System.out.println(ie.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException fe) {
            System.out.println(fe.getMessage());
        }
        teljesitmenySzamitas(file);
    }

    public void allandok1() {
        double t2 = 0;
        double i2 = 0;
        double t3 = 0;
        double t4 = 0;
        double i = 0;
        double n = 0;
        double a = 0;
        double b = 0;
        double i1;
        double cpco2, cpn2, cpo2, cph2o, ip;

        for (int X = 100; X <= 2000; X += 100) {
            cpco2 = 0.38231 + X * (0.000252 - X * (0.00000016633 - X * (0.000000000076427 - X * (2.0555E-14 - X * (2.3407E-18)))));
            cpn2 = 0.30929 - X * (0.000005 - X * (0.00000006262 - X * (0.00000000004771 - X * (1.5436E-14 - X * (1.8819E-18)))));
            cpo2 = 0.31519 + X * (0.000004 + X * (0.000000060761 - X * (5.13003E-11 - X * (1.7716E-14 - X * (2.2617E-18)))));
            cph2o = 0.35672 + X * (0.000025 + X * (0.000000057207 - X * (0.000000000035393 - X * (9.1539E-15 - X * (9.2691E-19)))));
            ip = ((this.mco2 / 1.977 + this.mso2 / 2.928) * cpco2 + (this.mn2 - this.mo2 * 76.8 / 23.2) * cpn2 / 1.251 + this.mh2o / 0.804 * cph2o + this.mo2 / 1.428 * 100 / 23.2 * cpo2) * 4.1816 * X;
            double t1 = Math.log(X);
            i1 = Math.log(ip);
            t2 = t2 + t1;
            i2 = i2 + i1;
            t3 = t3 + t1 * i1;
            t4 = t4 + t1 * t1;
            n = n + 1;
        }
        b = (t3 - (t2 * i2) / n) / (t4 - (t2 * t2 / n));
        a = Math.exp(i2 / n - b * t2 / n);
        hk = this.hi;
        this.a = a;
        this.b = b;
        this.hk = hk;
    }

    public void teljesitmenySzamitas(File file) {
        long gn, g, pk, tkig, ttv, pku, tkiu, pbu, tbeu, dpd, dpvh, gu, gusz, tkibefl, bb;
        gn = g = pk = tkig = ttv = pku = tkiu = pbu = tbeu = dpd = dpvh = gu = gusz = tkibefl = bb = 0;
        double ktt, fott, fhtl, ttm, xszt, ftth, ffracs, hego, fszfe, tfole, mvvrec, aa, b1, xe, mup, bsa, kc, kapa1, kapa2, mx, tlevk, klar, atu, btu, tfole2, tfolit;
        ktt = fott = fhtl = ttm = xszt = ftth = ffracs = hego = fszfe = tfole = mvvrec = aa = b1 = xe = mup = bsa = kc = kapa1 = kapa2 = mx = tlevk = klar = atu = btu = tfole2 = tfolit = 0.0;
        long tetakk, ke, keta, salsz, persz, coeln, celn;
        tetakk = ke = keta = salsz = persz = coeln = celn = 0;
        ;
        String sql3 = "select gozpar_be from gozpar";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql3)) {
            rs.next();
            gn = rs.getLong("gozpar_be");
            rs.next();
            g = rs.getLong("gozpar_be");
            rs.next();
            pk = rs.getLong("gozpar_be");
            rs.next();
            tkig = rs.getLong("gozpar_be");
            rs.next();
            ttv = rs.getLong("gozpar_be");
            rs.next();
            pku = rs.getLong("gozpar_be");
            rs.next();
            tkiu = rs.getLong("gozpar_be");
            rs.next();
            pbu = rs.getLong("gozpar_be");
            rs.next();
            tbeu = rs.getLong("gozpar_be");
            rs.next();
            dpd = rs.getLong("gozpar_be");
            rs.next();
            dpvh = rs.getLong("gozpar_be");
            rs.next();
            gu = rs.getLong("gozpar_be");
            rs.next();
            gusz = rs.getLong("gozpar_be");
            rs.next();
            tkibefl = rs.getLong("gozpar_be");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        String sql = "select tuztadat_be from tuztadat";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            rs.next();
            ktt = rs.getDouble("tuztadat_be");
            rs.next();
            fott = rs.getDouble("tuztadat_be");
            rs.next();
            fhtl = rs.getDouble("tuztadat_be");
            rs.next();
            ttm = rs.getDouble("tuztadat_be");
            rs.next();
            xszt = rs.getDouble("tuztadat_be");
            rs.next();
            ftth = rs.getDouble("tuztadat_be");
            rs.next();
            ffracs = rs.getDouble("tuztadat_be");
            rs.next();
            hego = rs.getDouble("tuztadat_be");
            rs.next();
            fszfe = rs.getDouble("tuztadat_be");
            rs.next();
            tfole = rs.getDouble("tuztadat_be");
            rs.next();
            mvvrec = rs.getDouble("tuztadat_be");
            rs.next();
            aa = rs.getDouble("tuztadat_be");
            rs.next();
            b1 = rs.getDouble("tuztadat_be");
            rs.next();
            xe = rs.getDouble("tuztadat_be");
            rs.next();
            mup = rs.getDouble("tuztadat_be");
            rs.next();
            bsa = rs.getDouble("tuztadat_be");
            rs.next();
            kc = rs.getDouble("tuztadat_be");
            rs.next();
            kapa1 = rs.getDouble("tuztadat_be");
            rs.next();
            kapa2 = rs.getDouble("tuztadat_be");
            rs.next();
            mx = rs.getDouble("tuztadat_be");
            rs.next();
            tlevk = rs.getDouble("tuztadat_be");
            rs.next();
            klar = rs.getDouble("tuztadat_be");
            rs.next();
            atu = rs.getDouble("tuztadat_be");
            rs.next();
            btu = rs.getDouble("tuztadat_be");
            rs.next();
            tfole2 = rs.getDouble("tuztadat_be");
            rs.next();
            tfolit = rs.getDouble("tuztadat_be");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String sql1 = "select hatfok_be from hatfok";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql1)) {
            rs.next();
            tetakk = rs.getLong("hatfok_be");
            rs.next();
            ke = rs.getLong("hatfok_be");
            rs.next();
            keta = rs.getLong("hatfok_be");
            rs.next();
            salsz = rs.getLong("hatfok_be");
            rs.next();
            persz = rs.getLong("hatfok_be");
            rs.next();
            coeln = rs.getLong("hatfok_be");
            rs.next();
            celn = rs.getLong("hatfok_be");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        this.tetakk = tetakk;
        double pd, ts, pvh, pki, t0, i1, itv, qvh, i2, qe, tki1, ia1, qth, qgh, ia2, ia3, quh, qgo, qsv;
        pd = pk + dpd;
        ts = tsf(pd);
        pvh = pd + dpvh;
        pki = pk;
        t0 = tsf(pd);
        i1 = isvf(t0);
        itv = isvf(ttv);
        qvh = (gn + bb) / 3.6 * (i1 - itv);
        i2 = isgf(t0);
        qe = gn / 3.6 * (i2 - i1);
        tki1 = tkig;
        ia1 = igf(pk, tki1);
        qth = (gn + bb) / 3.6 * (ia1 - i2);
        qgh = qvh + qe + qth;
        ia2 = igf(pku, tkiu);
        ia3 = igf(pbu, tbeu);
        quh = gu / 3.6 * (ia2 - ia3);
        qgo = qgh + quh;
        qsv = qgo * (sqrt(100 / (gn + gu))) / 100;

        //gőz előállítására fordított hőmennyiség
        pd = pk + dpd;
        ts = tsf(pd);
        pvh = pd + dpvh;
        pki = pk;
        t0 = tsf(pd);
        i1 = isvf(t0);
        itv = isvf(ttv);
        qvh = (g + bb) / 3.6 * (i1 - itv);
        i2 = isgf(t0);
        qe = g / 3.6 * (i2 - i1);
        tki1 = tkig;
        ia1 = igf(pk, tki1);
        qth = (g + bb) / 3.6 * (ia1 - i2);
        qgh = qvh + qe + qth;
        ia2 = igf(pku, tkiu);
        ia3 = igf(pbu, tbeu);
        quh = gu / 3.6 * (ia2 - ia3);
        qgo = qgh + quh;


        //kazán hatásfok
        double i, ml, kfgv, ge, ks, btuaf, hamus, salak, salakhv, pernye, pernyehv, bgkf, mvv0f, vfgf, cof, coho, cohv, cne, cho, chov, etak, qtua, btua, bgk;
        i = (a * Math.pow(this.tetakk, b)) + 4;
        ml = mv * mo2 * 100 / 23.2 / (m - 1);
        kfgv = (100 - ke) * (mv * i - m * ml0 * 25.1) / hk;
        ge = g;


        ks = qsv / (qgo + qsv) * 100 + 0.8;
        // eredeti egyenlet: ks = qsv / (qgo + qsv) * 100 - 0.8

        // salakhő veszteség
        //Bevitt tüzelőanyag vesztesége fixre adva 92 % hatásfok mellett
        btuaf = qgo / 0.92 / this.hi;
        hamus = btuaf * szilard_hamu / 100;
        salak = hamus * 0.05;
        salakhv = (salak * 500 * 0.916) * 100 / qgo;

        // pernyehő veszteség
        pernye = hamus * 0.95;
        pernyehv = (pernye * 500 * 0.822) * 100 / qgo;

        //Elégetlen CO okozta veszteség
        // Figyelembe vett Elégetlen CO mennyiség 200 (mg/Nm3)
        int kef = 0;
        bgkf = btuaf * (1 - (kef / 100));
        mvv0f = bgkf * mv;
        vfgf = mvv0f / rofg;
        cof = vfgf * coeln / 1000000;
        coho = cof * 10098;
        cohv = coho / qgo * 100;

        // Salak és pernye elégetlen veszteség, maradó C a hamuban 4 %
        cne = hamus * 0.04;
        cho = cne * 50103;
        chov = cho * 100 / qgo;
        // Füztgázban elégetlen tüzelőanyag


        etak = 100 - (ke + kfgv + ks + salakhv + pernyehv + cohv + chov);


        qtua = qgo / (etak / 100);
        btua = qtua / hk;
        bgk = (1 - (ke / 100)) * btua;


        //  tfol = ((tfole + 273) * (1 - klar / 100) + (tfole1 + 273) * (klar / 100)) - 273
        try {
            FileInputStream inputStream = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            Row row = sheet.createRow(93);
            row.createCell(0).setCellValue("Névleges teljesítmény");
            row.createCell(1).setCellValue(round(qgo));
            row.createCell(2).setCellValue("(kW)");
            row = sheet.createRow(94);
            row.createCell(0).setCellValue("Kazán felületén kisugárzott hőmennyiség");
            row.createCell(1).setCellValue(round(qsv));
            row.createCell(2).setCellValue("(kW)");

            row = sheet.createRow(96);
            row.createCell(0).setCellValue("Füstgáz sűrűsége");
            row.createCell(1).setCellValue(round(rofg * 1000) / 1000);
            row.createCell(2).setCellValue("kg/Nm3");
            row = sheet.createRow(97);
            row.createCell(0).setCellValue("Tüzelőanyaq fűtőértékeFüstgáz sűrűsége");
            row.createCell(1).setCellValue(this.hi);
            row.createCell(2).setCellValue("Kj/kg");

            row = sheet.createRow(99);
            row.createCell(0).setCellValue("Kazánhatasfok");
            row.createCell(1).setCellValue(etak);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(100);
            row.createCell(0).setCellValue("Füstgáz hője okozta veszteség");
            row.createCell(1).setCellValue(kfgv);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(101);
            row.createCell(0).setCellValue("Sugárzási veszteség");
            row.createCell(1).setCellValue(ks);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(102);
            row.createCell(0).setCellValue("Salak hővesztesége");
            row.createCell(1).setCellValue(salakhv);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(103);
            row.createCell(0).setCellValue("Pernye hővesztesége");
            row.createCell(1).setCellValue(pernyehv);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(104);
            row.createCell(0).setCellValue("Elégetlen CO veszteség");
            row.createCell(1).setCellValue(cohv);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(105);
            row.createCell(0).setCellValue("Salak és pernye elégetlen veszteség");
            row.createCell(1).setCellValue(chov);
            row.createCell(2).setCellValue("%");
            row = sheet.createRow(106);
            row.createCell(0).setCellValue("Elégetlen egyéb veszteség (maradó szerves vegyületek a gázban)");
            row.createCell(1).setCellValue(ke);
            row.createCell(2).setCellValue("%");

            row = sheet.createRow(108);
            row.createCell(0).setCellValue("Elégetlen CO veszteség");

            row = sheet.createRow(110);
            row.createCell(0).setCellValue("Vízmelegítési hő");
            row.createCell(1).setCellValue(qvh);
            row.createCell(2).setCellValue("kW");
            row = sheet.createRow(111);
            row.createCell(0).setCellValue("Párolgási hő");
            row.createCell(1).setCellValue(qe);
            row = sheet.createRow(112);
            row.createCell(0).setCellValue("Túlhevítési hő");
            row.createCell(1).setCellValue(qth);
            row = sheet.createRow(113);
            row.createCell(0).setCellValue("Újrahevítési hő");
            row.createCell(1).setCellValue(quh);
            row = sheet.createRow(114);
            row.createCell(0).setCellValue("Gőzzel hasznosított hő");
            row.createCell(1).setCellValue(qgo);
            row = sheet.createRow(115);
            row.createCell(0).setCellValue("Tüzelőanyaggal bevitt hő");
            row.createCell(1).setCellValue(qtua);
            row = sheet.createRow(116);
            row.createCell(0).setCellValue("Betáplált tüzelőanyag (szilárd esetén)");
            row.createCell(1).setCellValue(btua);
            row.createCell(2).setCellValue("kg/s");
            row = sheet.createRow(117);
            row.createCell(1).setCellValue(btua * 3.6);
            row.createCell(2).setCellValue("t/h");

            row = sheet.createRow(119);
            row.createCell(0).setCellValue("Betáplált tüzelőanyag (gáz tüzelanyag esetén)Gőzzel hasznosított hő");
            row.createCell(1).setCellValue(btua / rog * 3600);
            row.createCell(2).setCellValue("Nm3/h");

            row = sheet.createRow(121);
            row.createCell(0).setCellValue("Betáplált tüzelőanyag (szilárd esetén)");
            row.createCell(1).setCellValue(bgk);
            row.createCell(2).setCellValue("kg/s");
            row = sheet.createRow(122);
            row.createCell(1).setCellValue(bgk * 3.6);
            row.createCell(2).setCellValue("t/h");

            row = sheet.createRow(124);
            row.createCell(0).setCellValue("a együttható");
            row.createCell(1).setCellValue(a);
            row.createCell(2).setCellValue("-");

            row = sheet.createRow(126);
            row.createCell(0).setCellValue("b együttható");
            row.createCell(1).setCellValue(b);
            row.createCell(2).setCellValue("-");
          /*  row = sheet.createRow(127);
            row.createCell(0).setCellValue("ForrÓ levegő hőmérséklete");
            row.createCell(1).setCellValue(tfol);
            row.createCell(2).setCellValue("C");*/
            row = sheet.createRow(128);
            row.createCell(0).setCellValue("Levegő tömegárama");
            row.createCell(1).setCellValue(ml);
            row.createCell(2).setCellValue("kg/s");
            row = sheet.createRow(129);
            row.createCell(0).setCellValue("Levegő térfogatárama");
            row.createCell(1).setCellValue(ml / 1.291 * 3600);
            row.createCell(2).setCellValue("Nm3/s");
            row = sheet.createRow(130);
            row.createCell(0).setCellValue("Fustgáz tömegárama tűztérben");
            row.createCell(1).setCellValue(bgk * mv);
            row.createCell(2).setCellValue("kg/s");
            row = sheet.createRow(131);
            row.createCell(0).setCellValue("Füstgáz térfogatárama tűztérben");
            row.createCell(1).setCellValue((bgk * mv) / rofg * 3600);
            row.createCell(2).setCellValue("Nm3/h");
            row = sheet.createRow(132);
            row.createCell(0).setCellValue("Recirkuláció mennyisége");
            row.createCell(1).setCellValue(mvvrec);
            row.createCell(2).setCellValue("%");








        // Tűztér számítás
        int s1 = 0;
        List<Double> sufoelo = new ArrayList<>();
        String sql9 = "select suf from thom";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql9)) {
            while (rs.next()) {

                sufoelo.add(rs.getDouble("suf"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        Double sufo = 0.0;
        for (int i3 = 0; i <= sufoelo.size(); i3++) {
            sufo = sufoelo.get(i3);
        }

        sufo = sufo + fott; // sufo = a sugarzasi veszteseget ado osszes felulet. Tűztér és konvektív felületek.
        double tfole1, tfol, ifol, itua, mvv0;
        tfole1 = tfol = 0.0;
// Tüztérbe bevezetett égéslevegő átlaghőmérséklete
        if (tfolit == 0) {
            tfole1 = tfole2;
        } else {
            String sql10 = "select tgki from rhom where viz = " + tfolit;
            try (Connection conn = this.connect();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql10)) {

                tfole1 = rs.getInt("suf");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

//klar = kiégető levegő arány. Égéslevegő előmelegítőnél felhaszálva!!

        tfol = ((tfole + 273) * (1 - klar / 100) + (tfole1 + 273) * (klar / 100)) - 273;

        ifol = tfol + 0.00006 * tfol * tfol; // Debug.Print " ifol"; ifol: INPUT in
        int ttua = 25;
// Tűztérbe bevezetett tüzelőanyag entalpiája (itt még csak a gáz tüzelőanyag van beírva)
        itua = ttua + 0.00006 * ttua * ttua;

//Füstgáz számítások

// Füstgáz mennyiség recirkuláció nélkül (kg/s)
        mvv0 = bgk * mv;


// Égéslevegő mennyiség (kg/s)
        ml = bgk * ml0 * m;
        Double mvv,mvver,tetaki,i00,teta0,xl,e,ph2o,pco2,pso2,phag,p,ss,kg,kk,kv,alv,alnv,qkvv,omeg;
        omeg = 0.0;
        mvv = mvv0 * (1 + (mvvrec / 100));
        mvver = mvv0;
        qtua = bgk * (hk + ml0 * m * ifol + itua);
        tetaki = 1100.0;
        i00 = qtua / mvv;
        teta0 = Math.pow(i00 / a, 1 / b);
        xl = hego / ttm;
        e = aa - b1 * xl;
        int dd = 0;
        Double ffi,kszi,pszi,khi,epsz,ale,ii1,l00,qs,bol,tetak1;
        tetak1 = 1050.0;
        do{
            if (abs(tetaki - tetak1) > 1) {
                tetaki = tetak1;

            }

        dd = dd + 1;
        ph2o = mh2o / 0.804 * rofg;
        pco2 = mco2 / 1.977 * rofg;
        pso2 = mso2 / 2.928 * rofg;
        phag = ph2o + pco2 + pso2;
        p = 1.0;
        ss = 3.6 * (ktt / fott);
        kg = ((1.6 * ph2o + 0.78) / sqrt(phag * ss) - 0.1) * (1 - ((tetaki + 273) / 1000 * 0.37));
        kk = 0.03 * (2 - m) * (1.6 * (tetaki + 273) / 1000 - 0.5) * this.c / this.h;
        kv = kg * phag + kk;
        alv = 1 - exp(-kv * ss * p);
        alnv = 1 - exp(-kg * phag * ss * p);
        qkvv = qtua / ktt;
        if (this.tuam.equals("szilard")) {


            if (qkvv <= 407) {
                omeg = 0.55;
            } else if (qkvv >= 1163) {
                omeg = 1.0;
            } else {
                omeg = 0.55 + 0.45 * ((qkvv - 407) / 756);
            }


        } else if (this.tuam.equals("gaz")) {
            if (qkvv <= 407) {
                omeg = 0.1;
            } else if (qkvv >= 1163) {
                omeg = 0.6;
            } else {
                omeg = 0.1 + 0.5 * ((qkvv - 407) / 756);
            }
        }

        ale = alv * omeg + (1 - omeg) * alnv;
        ffi = (fott - fhtl) / fott;
        kszi = ffi * mup;
        pszi = (fott - fhtl) * xszt / fott;
        khi = pszi * kszi;
        epsz = ale / (ale + (1 - ale) * khi);
        ii1 = a * Math.pow(tetaki, b);
        qs = mvv * (i00 - ii1);
            System.out.println(mvv +" mvv");
            System.out.println(i00 + " i00");
            System.out.println(ii1 + " ll1");
        bol = qs / (0.0000000000578 * fott * khi * Math.pow(teta0 + 273, 3) * (teta0 - tetaki));
        tetak1 = (teta0 + 273) / (e * Math.pow(epsz / bol, 0.6) + 1) - 273;
            System.out.println(teta0 + " tetak0");
            System.out.println(tetak1 + " tetak1");
            System.out.println(dd + " dd");
            System.out.println(qtua + " qtua");
            System.out.println(qs + " qs");
           // System.out.println(qsh + " qsh");
            System.out.println(epsz + " epsz");
            System.out.println(alv+ " alv");
            System.out.println(alnv + " alnv");
            System.out.println(omeg + " omeg");
            System.out.println(teta0 + " teta0");
    }while(abs(tetaki - tetak1) > 1);
        Double ftt,vfgt,quki,quttv,qsh,fhtt,qski;
//Füstgázsebesség a tűztérben vfgt
        vfgt = mvv / rofg * ((273 + (tetak1 + teta0) / 2) / 273) / (atu * btu);

//Füstgáz tartózkodása a tűztérben ftt
        ftt = (ttm - hego) / vfgt;

//Kilépő füstgáz hőmérséklet, csökkentve a tűztéri lesugárzással

// Füstgáz hője
        quki = a * Math.pow(tetaki, b) * mvv;
// Felületen elmenő hőveszteség
        quttv = qsv * fott / sufo;

// Füstgáz kilépő hőmérséklet lesugárzási veszteség után
        tetaki = Math.pow((quki - quttv) / a / mvv, 1 / b);

// Tűztérben hasznosan lesugárzott hő = teljes lesugárzás - felületi hőveszteség = qsh
        qsh = qs - quttv;
    /*
        thom(0, 3) = (int)Math.floor((double)tetaki);
    */
// Tűztérből kisugárzotthő
        fhtt = fott - fhtl;
        qski = qs * ftth / fhtt;


       /* if (pkazn > 0) // Vizsgálja, hogy forró víz kazán e vagy gőzkazán. pkazn > 0 esetén forróvíz kazán.
        {
            cpviz = cpvf((rhom(1, 1) + tbev) / 2);
            tvkit = tbev + qsh / (gn / 3.6 * cpviz);
            rhom(1, 1) = tvkit;
        }*/
            Sheet sheet2 = workbook.createSheet("Tűztér");
            Row row2 = sheet2.createRow(0);
            row2.createCell(0).setCellValue("Tűztér számítás");
            row2 = sheet2.createRow(1);
            row2.createCell(0).setCellValue("Tűztér kilepő hőmérséklet");
            row2.createCell(1).setCellValue(tetak1);
            row2.createCell(2).setCellValue("C");
            row2 = sheet2.createRow(2);
            row2.createCell(0).setCellValue("Az iteració lépésszáma");
            row2.createCell(1).setCellValue(dd);
            row2.createCell(2).setCellValue("-");
            row2 = sheet2.createRow(3);
            row2.createCell(0).setCellValue("TETA 0 elméleti égési hőmérséklet");
            row2.createCell(1).setCellValue(teta0);
            row2.createCell(2).setCellValue("C");
            row2 = sheet2.createRow(4);
            row2.createCell(0).setCellValue("Q tua tüzelőanyaggal a kazánba bevitt hőmennyiség");
            sheet2.autoSizeColumn(0);
            row2.createCell(1).setCellValue(qtua);
            row2.createCell(2).setCellValue("(kW)");
            row2 = sheet2.createRow(5);
            row2.createCell(0).setCellValue("Qs tűztérben lesugárzott hőmennyiség");
            row2.createCell(1).setCellValue(qs);
            row2.createCell(2).setCellValue("(kW)");
            row2 = sheet2.createRow(6);
            row2.createCell(0).setCellValue("Qsh   Tűztérben hasznosan lesugárzott hő");
            row2.createCell(1).setCellValue(qs);
            row2.createCell(2).setCellValue("(kW)");
            row2 = sheet2.createRow(7);
            row2.createCell(0).setCellValue("Epsz elpiszkolódási tényező");
            row2.createCell(1).setCellValue(epsz);
            row2.createCell(2).setCellValue("-");
            row2 = sheet2.createRow(8);
            row2.createCell(0).setCellValue("a lv (láng világító részének feketeségi foka)");
            row2.createCell(1).setCellValue(alv);
            row2.createCell(2).setCellValue("-");
            row2 = sheet2.createRow(9);
            row2.createCell(0).setCellValue("a lnv (a láng nem világító részének feketeségi foka)");
            row2.createCell(1).setCellValue(alnv);
            row2.createCell(2).setCellValue("-");
            row2 = sheet2.createRow(10);
            row2.createCell(0).setCellValue("Omega (tűztér világító lánggal kitöltött hányada)");
            row2.createCell(1).setCellValue(omeg);
            row2.createCell(2).setCellValue("-");
            row2 = sheet2.createRow(11);
            row2.createCell(0).setCellValue("Q kkv");
            row2.createCell(1).setCellValue(qkvv);
            row2.createCell(2).setCellValue("-");
            row2 = sheet2.createRow(13);
            row2.createCell(0).setCellValue("Tűztér térfogati hőterhelése");
            row2.createCell(1).setCellValue(qs/ktt);
            row2.createCell(2).setCellValue("(kW/m3)");
            row2 = sheet2.createRow(14);
            row2.createCell(0).setCellValue("Tűztér felületi hőterhelése");
            row2.createCell(1).setCellValue(qs/fott);
            row2.createCell(2).setCellValue("(kW/m2)");
            row2 = sheet2.createRow(15);
            row2.createCell(0).setCellValue("Füstgáz átlagsebessége a tűztérben");
            row2.createCell(1).setCellValue(vfgf);
            row2.createCell(2).setCellValue("m/s");
            row2 = sheet2.createRow(16);
            row2.createCell(0).setCellValue("Füstgáz tartózkodási ideje a tűztérben");
            row2.createCell(1).setCellValue(ftt);
            row2.createCell(2).setCellValue("-");
            row2 = sheet2.createRow(18);
            row2.createCell(0).setCellValue("Tűztérből átsugárzott hő");
            row2.createCell(1).setCellValue(qski);
            row2.createCell(2).setCellValue("kW");



            workbook.write(new FileOutputStream(file));
            workbook.close();
        } catch (InvalidFormatException ie) {
            System.out.println(ie.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException fe) {
            System.out.println(fe.getMessage());
        }

    }


    public double tsf(double ps){

        return 4659.28 / (12.585 - Math.log(ps)) - 273.15;
    }
    public double isvf(double ts){
// isvf(ts) víz entalpiája hőmérséklet alapján, hőmérséklet C-ban
        return 1000 * (2.6 - sqrt(5.9931 - ts / 67.9909));
    }
    private double isgf(double ts){
        // isgf(ts) gőz entalpiája hőmérséklet alapján hőmérséklet C-ban
     return (-0.097491 * pow(abs((ts - 236) / 100), 6) - 0.619909 * pow(abs((ts - 236) / 100), 4) - 0.89176 * pow(abs((ts - 236) / 100), 2) + 28.023) * 100;
}
    public double igf(long p, double t)
    {
// igf(p,t) gőz entalpiája nyomás és hőmérséklet alapján p-bar ban t C-ban megadva
        return 4.1868 * (597.2 + 0.44 * t + 0.000065 * pow(t, 2) - 139.2 * p / pow(((t + 273) / 100), 3.1) - 656500 * p * p * p / pow(((t + 273) / 100), 13.5));
    }


    public double getA() {
        return a;
    }

    public double getB() {
        return b;
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
