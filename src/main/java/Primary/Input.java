package Primary;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Input {


    public void databaseTorol(){


        String[] tablak = { "gozpar", "hatfok", "logika", "rhom", "sqlite_sequence", "thom", "tua", "tuag1", "tuag2", "tuasz1","tuasz2", "tuztadat" , "vegyes" , "vizpar"};
            String sql = "DELETE FROM ";
        for (String tabla  : tablak)
        {
            System.out.println(tabla);
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql + tabla)) {
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public void insertThom(XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheetAt(1);
        int firstRow = 3;
        XSSFRow row = sheet.getRow(firstRow);
        while (row.getCell(0).getNumericCellValue() != 0) {
            int ife, igs, fmf, mf2, mf3, mf4, feltip;
            double tetabe, tetaki, suf, mx, kx;
            String felnam;
            String sql = "INSERT INTO thom(ife,tetabe,igs,tetaki,fmf,mf2,mf3,mf4,suf,mx,kx,feltip,felnam) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                if (row.getCell(0) != null) {
                    ife = (int) row.getCell(0).getNumericCellValue();
                    System.out.println(ife);
                    pstmt.setInt(1, ife);
                }
                if (row.getCell(1) != null) {
                    tetabe = row.getCell(1).getNumericCellValue();
                    System.out.println(tetabe);
                    pstmt.setDouble(2, tetabe);
                }
                if (row.getCell(2) != null) {
                    igs = (int) row.getCell(2).getNumericCellValue();
                    System.out.println(igs);
                    pstmt.setInt(3, igs);
                }
                if (row.getCell(3) != null) {
                    tetaki = row.getCell(3).getNumericCellValue();
                    System.out.println(tetaki);
                    pstmt.setDouble(4, tetaki);
                }
                if (row.getCell(4) != null) {
                    fmf = (int) row.getCell(4).getNumericCellValue();
                    System.out.println(fmf);
                    pstmt.setInt(5, fmf);
                }
                if (row.getCell(5) != null) {
                    mf2 = (int) row.getCell(5).getNumericCellValue();
                    System.out.println(mf2);
                    pstmt.setInt(6, mf2);
                }
                if (row.getCell(6) != null) {
                    mf3 = (int) row.getCell(6).getNumericCellValue();
                    System.out.println(mf3);
                    pstmt.setInt(7, mf3);
                }
                if (row.getCell(7) != null) {
                    mf4 = (int) row.getCell(7).getNumericCellValue();
                    System.out.println(mf4);
                    pstmt.setInt(8, mf4);
                }
                if (row.getCell(8) != null) {
                    suf = row.getCell(8).getNumericCellValue();
                    System.out.println(suf);
                    pstmt.setDouble(9, suf);
                }
                if (row.getCell(9) != null) {
                    mx = row.getCell(9).getNumericCellValue();
                    System.out.println(mx);
                    pstmt.setDouble(10, mx);
                }
                if (row.getCell(10) != null) {
                    kx = row.getCell(10).getNumericCellValue();
                    System.out.println(kx);
                    pstmt.setDouble(11, kx);
                }
                if (row.getCell(11) != null) {
                    try {
                        feltip = Integer.parseInt(row.getCell(11).getStringCellValue());
                        System.out.println(feltip);
                        pstmt.setInt(12, feltip);

                    } catch (NumberFormatException ne) {
                        System.out.println(ne);
                    }

                }


                if (row.getCell(12) != null) {
                    felnam = row.getCell(12).getStringCellValue();
                    System.out.println(felnam);
                    pstmt.setString(13, felnam);
                }
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
            firstRow += 1;
            row = sheet.getRow(firstRow);
        }

    }

    public void insertRhom(XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheetAt(2);
        int firstRow = 5;
        XSSFRow row = sheet.getRow(firstRow);
        while (row.getCell(0).getNumericCellValue() != 0) {
            int viz, tgbe, tgki, felt, zz, csr, fgar, kpcs, kere, bef, z1, z2, fmocs, fmok, borda, besug, tbo, gkr, ife, fmfh, bfs, kil, bosz;
            String felnamr;
            double dk, fal, t1, t2, akf, bkf, akl, bkl, lcs, fmo, af, pcs, bsf, pf, fkht, betab, hbo;

            String sql = "INSERT INTO rhom(viz,felnamr,tgbe,tgki,felt,zz,csr,fgar,kpcs,kere,bef,dk,fal,z1,z2,t1,t2,akf,bkf,akl,bkl,lcs,fmo,fmocs,fmok,borda,af,pcs,besug,bsf,pf,tbo,gkr,ife,fmfh,bfs,kil,fkht,betab,hbo,bosz) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                if (row.getCell(0) != null) {
                    viz = (int) row.getCell(0).getNumericCellValue();
                    System.out.println(viz);
                    pstmt.setInt(1, viz);
                }
                if (row.getCell(1) != null) {
                    felnamr = row.getCell(1).getStringCellValue();
                    System.out.println(felnamr);
                    pstmt.setString(2, felnamr);
                }
                if (row.getCell(2) != null) {
                    tgbe = (int) row.getCell(2).getNumericCellValue();
                    System.out.println(tgbe);
                    pstmt.setInt(3, tgbe);
                }
                if (row.getCell(3) != null) {
                    tgki = (int) row.getCell(3).getNumericCellValue();
                    System.out.println(tgki);
                    pstmt.setDouble(4, tgki);
                }
                if (row.getCell(4) != null) {
                    felt = (int) row.getCell(4).getNumericCellValue();
                    System.out.println(felt);
                    pstmt.setInt(5, felt);
                }
                if (row.getCell(5) != null) {
                    zz = (int) row.getCell(5).getNumericCellValue();
                    System.out.println(zz);
                    pstmt.setInt(6, zz);
                }
                if (row.getCell(6) != null) {
                    csr = (int) row.getCell(6).getNumericCellValue();
                    System.out.println(csr);
                    pstmt.setInt(7, csr);
                }
                if (row.getCell(7) != null) {
                    fgar = (int) row.getCell(7).getNumericCellValue();
                    System.out.println(fgar);
                    pstmt.setInt(8, fgar);
                }
                if (row.getCell(8) != null) {
                    kpcs = (int) row.getCell(8).getNumericCellValue();
                    System.out.println(kpcs);
                    pstmt.setInt(9, kpcs);
                }
                if (row.getCell(9) != null) {
                    kere = (int) row.getCell(9).getNumericCellValue();
                    System.out.println(kere);
                    pstmt.setDouble(10, kere);
                }
                if (row.getCell(10) != null) {
                    bef = (int) row.getCell(10).getNumericCellValue();
                    System.out.println(bef);
                    pstmt.setDouble(11, bef);
                }

                if (row.getCell(11) != null) {
                    dk = row.getCell(11).getNumericCellValue();
                    System.out.println(dk);
                    pstmt.setDouble(12, dk);
                }
                if (row.getCell(12) != null) {
                    fal = row.getCell(12).getNumericCellValue();
                    System.out.println(fal);
                    pstmt.setDouble(13, fal);
                }
                if (row.getCell(13) != null) {
                    z1 = (int) row.getCell(13).getNumericCellValue();
                    System.out.println(z1);
                    pstmt.setInt(14, z1);
                }
                if (row.getCell(14) != null) {
                    z2 = (int) row.getCell(14).getNumericCellValue();
                    System.out.println(z2);
                    pstmt.setInt(15, z2);
                }
                if (row.getCell(15) != null) {
                    t1 = row.getCell(15).getNumericCellValue();
                    System.out.println(t1);
                    pstmt.setDouble(16, t1);
                }
                if (row.getCell(16) != null) {
                    t2 = row.getCell(16).getNumericCellValue();
                    System.out.println(t2);
                    pstmt.setDouble(17, t2);
                }
                if (row.getCell(17) != null) {
                    akf = row.getCell(17).getNumericCellValue();
                    System.out.println(akf);
                    pstmt.setDouble(18, akf);
                }
                if (row.getCell(18) != null) {
                    bkf = row.getCell(18).getNumericCellValue();
                    System.out.println(bkf);
                    pstmt.setDouble(19, bkf);
                }
                if (row.getCell(19) != null) {
                    akl = row.getCell(19).getNumericCellValue();
                    System.out.println(akl);
                    pstmt.setDouble(20, akl);
                }
                if (row.getCell(20) != null) {
                    bkl = row.getCell(20).getNumericCellValue();
                    System.out.println(bkl);
                    pstmt.setDouble(21, bkl);
                }
                if (row.getCell(21) != null) {
                    lcs = row.getCell(21).getNumericCellValue();
                    System.out.println(lcs);
                    pstmt.setDouble(22, lcs);
                }
                if (row.getCell(22) != null) {
                    fmo = row.getCell(22).getNumericCellValue();
                    System.out.println(fmo);
                    pstmt.setDouble(23, fmo);
                }
                if (row.getCell(23) != null) {
                    fmocs = (int) row.getCell(23).getNumericCellValue();
                    System.out.println(fmocs);
                    pstmt.setInt(24, fmocs);
                }
                if (row.getCell(24) != null) {
                    fmok = (int) row.getCell(24).getNumericCellValue();
                    System.out.println(fmok);
                    pstmt.setInt(25, fmok);
                }
                if (row.getCell(25) != null) {
                    borda = (int) row.getCell(25).getNumericCellValue();
                    System.out.println(borda);
                    pstmt.setInt(26, borda);
                }
                if (row.getCell(26) != null) {
                    af = row.getCell(26).getNumericCellValue();
                    System.out.println(af);
                    pstmt.setDouble(27, af);
                }
                if (row.getCell(27) != null) {
                    pcs = row.getCell(27).getNumericCellValue();
                    System.out.println(pcs);
                    pstmt.setDouble(28, pcs);
                }
                if (row.getCell(28) != null) {
                    besug = (int) row.getCell(28).getNumericCellValue();
                    System.out.println(besug);
                    pstmt.setInt(29, besug);
                }
                if (row.getCell(29) != null) {
                    bsf = row.getCell(29).getNumericCellValue();
                    System.out.println(bsf);
                    pstmt.setDouble(30, bsf);
                }
                if (row.getCell(30) != null) {
                    pf = row.getCell(30).getNumericCellValue();
                    System.out.println(pf);
                    pstmt.setDouble(31, pf);
                }
                if (row.getCell(31) != null) {
                    tbo = (int) row.getCell(31).getNumericCellValue();
                    System.out.println(tbo);
                    pstmt.setInt(32, tbo);
                }
                if (row.getCell(32) != null) {
                    gkr = (int) row.getCell(32).getNumericCellValue();
                    System.out.println(gkr);
                    pstmt.setInt(33, gkr);
                }
                if (row.getCell(33) != null) {
                    ife = (int) row.getCell(33).getNumericCellValue();
                    System.out.println(ife);
                    pstmt.setInt(34, ife);
                }
                if (row.getCell(34) != null) {
                    fmfh = (int) row.getCell(34).getNumericCellValue();
                    System.out.println(fmfh);
                    pstmt.setInt(35, fmfh);
                }
                if (row.getCell(35) != null) {
                    bfs = (int) row.getCell(35).getNumericCellValue();
                    System.out.println(bfs);
                    pstmt.setInt(36, bfs);
                }
                if (row.getCell(36) != null) {
                    kil = (int) row.getCell(36).getNumericCellValue();
                    System.out.println(kil);
                    pstmt.setInt(37, kil);
                }
                if (row.getCell(37) != null) {
                    fkht = row.getCell(37).getNumericCellValue();
                    System.out.println(fkht);
                    pstmt.setDouble(38, fkht);
                }
                if (row.getCell(38) != null) {
                    betab = row.getCell(38).getNumericCellValue();
                    System.out.println(betab);
                    pstmt.setDouble(39, betab);
                }
                if (row.getCell(39) != null) {
                    hbo = row.getCell(39).getNumericCellValue();
                    System.out.println(hbo);
                    pstmt.setDouble(40, hbo);
                }
                if (row.getCell(40) != null) {
                    bosz = (int) row.getCell(40).getNumericCellValue();
                    System.out.println(bosz);
                    pstmt.setInt(41, bosz);
                }


                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
            firstRow += 1;
            row = sheet.getRow(firstRow);
        }

    }

    public void insertTuasz1(XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheetAt(3);
        int firstRow = 5;
        XSSFRow row = sheet.getRow(firstRow);
        while (firstRow != 20) {
            double c, h, s, o2, n2, h2o, cl, fluor, hamu, hi;
            String sql = "INSERT INTO tuasz1(c,h,s,o2,n2,h2o,cl,fluor,hamu,hi) VALUES(?,?,?,?,?,?,?,?,?,?)";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                if (row.getCell(0) != null) {
                    c = row.getCell(0).getNumericCellValue();
                    System.out.println(c);
                    pstmt.setDouble(1, c);
                }
                if (row.getCell(1) != null) {
                    h = row.getCell(1).getNumericCellValue();
                    System.out.println(h);
                    pstmt.setDouble(2, h);
                }
                if (row.getCell(2) != null) {
                    s = row.getCell(2).getNumericCellValue();
                    System.out.println(s);
                    pstmt.setDouble(3, s);
                }
                if (row.getCell(3) != null) {
                    o2 = row.getCell(3).getNumericCellValue();
                    System.out.println(o2);
                    pstmt.setDouble(4, o2);

                    if (row.getCell(4) != null) {
                        n2 = row.getCell(4).getNumericCellValue();
                        System.out.println(n2);
                        pstmt.setDouble(5, n2);
                    }
                }
                if (row.getCell(5) != null) {
                    h2o = row.getCell(5).getNumericCellValue();
                    System.out.println(h2o);
                    pstmt.setDouble(6, h2o);
                }
                if (row.getCell(6) != null) {
                    cl = row.getCell(6).getNumericCellValue();
                    System.out.println(cl);
                    pstmt.setDouble(7, cl);
                }
                if (row.getCell(7) != null) {
                    fluor = row.getCell(7).getNumericCellValue();
                    System.out.println(fluor);
                    pstmt.setDouble(8, fluor);
                }
                if (row.getCell(8) != null) {
                    hamu = row.getCell(8).getNumericCellValue();
                    System.out.println(hamu);
                    pstmt.setDouble(9, hamu);
                }
                if (row.getCell(9) != null) {
                    hi = row.getCell(9).getNumericCellValue();
                    System.out.println(hi);
                    pstmt.setDouble(10, hi);
                }

                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
            firstRow += 3;
            row = sheet.getRow(firstRow);
        }

    }

    public void insertTuag1(XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheetAt(3);
        int firstRow = 29;
        XSSFRow row = sheet.getRow(firstRow);
        while (firstRow != 44) {
            double ch4, c2h6, c3h8, c4h10, cxhy, co, h2, co2, n2, o2, h2s, h2o, so2, hi, c, h, ro;
            String sql = "INSERT INTO tuag1(ch4,c2h6,c3h8,c4h10,cxhy,co,h2,co2,n2,o2,h2s,h2o,so2,hi,c,h,ro) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                if (row.getCell(0) != null) {
                    ch4 = row.getCell(0).getNumericCellValue();
                    System.out.println(ch4);
                    pstmt.setDouble(1, ch4);
                }
                if (row.getCell(1) != null) {
                    c2h6 = row.getCell(1).getNumericCellValue();
                    System.out.println(c2h6);
                    pstmt.setDouble(2, c2h6);
                }
                if (row.getCell(2) != null) {
                    c3h8 = row.getCell(2).getNumericCellValue();
                    System.out.println(c3h8);
                    pstmt.setDouble(3, c3h8);
                }
                if (row.getCell(3) != null) {
                    c4h10 = row.getCell(3).getNumericCellValue();
                    System.out.println(c4h10);
                    pstmt.setDouble(4, c4h10);
                }
                if (row.getCell(4) != null) {
                    cxhy = row.getCell(4).getNumericCellValue();
                    System.out.println(cxhy);
                    pstmt.setDouble(5, cxhy);
                }
                if (row.getCell(5) != null) {
                    co = row.getCell(5).getNumericCellValue();
                    System.out.println(co);
                    pstmt.setDouble(6, co);
                }
                if (row.getCell(6) != null) {
                    h2 = row.getCell(6).getNumericCellValue();
                    System.out.println(h2);
                    pstmt.setDouble(7, h2);
                }
                if (row.getCell(7) != null) {
                    co2 = row.getCell(7).getNumericCellValue();
                    System.out.println(co2);
                    pstmt.setDouble(8, co2);
                }
                if (row.getCell(8) != null) {
                    n2 = row.getCell(8).getNumericCellValue();
                    System.out.println(n2);
                    pstmt.setDouble(9, n2);
                }
                if (row.getCell(9) != null) {
                    o2 = row.getCell(9).getNumericCellValue();
                    System.out.println(o2);
                    pstmt.setDouble(10, o2);
                }
                if (row.getCell(10) != null) {
                    h2s = row.getCell(10).getNumericCellValue();
                    System.out.println(h2s);
                    pstmt.setDouble(11, h2s);
                }
                if (row.getCell(11) != null) {
                    h2o = row.getCell(11).getNumericCellValue();
                    System.out.println(h2o);
                    pstmt.setDouble(12, h2o);
                }
                if (row.getCell(12) != null) {
                    so2 = row.getCell(12).getNumericCellValue();
                    System.out.println(so2);
                    pstmt.setDouble(13, so2);
                }
                if (row.getCell(13) != null) {
                    hi = row.getCell(13).getNumericCellValue();
                    System.out.println(hi);
                    pstmt.setDouble(14, hi);
                }
                if (row.getCell(14) != null) {
                    c = row.getCell(14).getNumericCellValue();
                    System.out.println(c);
                    pstmt.setDouble(15, c);
                }
                if (row.getCell(15) != null) {
                    h = row.getCell(15).getNumericCellValue();
                    System.out.println(h);
                    pstmt.setDouble(16, h);
                }
                if (row.getCell(16) != null) {
                    ro = row.getCell(16).getNumericCellValue();
                    System.out.println(ro);
                    pstmt.setDouble(17, ro);
                }

                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
            firstRow += 3;
            row = sheet.getRow(firstRow);
        }

    }

    public void insertTuasz2(XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheetAt(3);
        int firstRow = 19;
        XSSFRow row = sheet.getRow(firstRow);
        while (firstRow != 25) {
            int tuasza;
            String sql = "INSERT INTO tuasz2(tuasza) VALUES(?)";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                if (row.getCell(2) != null) {
                    tuasza = (int) row.getCell(2).getNumericCellValue();
                    System.out.println(tuasza);
                    pstmt.setDouble(1, tuasza);
                }

                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
            firstRow++;
            row = sheet.getRow(firstRow);
        }


    }

    public void insertTuag2(XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheetAt(3);
        int firstRow = 43;
        XSSFRow row = sheet.getRow(firstRow);
        while (firstRow != 49) {
            int tuaga;
            String sql = "INSERT INTO tuag2(tuaga) VALUES(?)";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                if (row.getCell(2) != null) {
                    tuaga = (int) row.getCell(2).getNumericCellValue();
                    System.out.println(tuaga);
                    pstmt.setDouble(1, tuaga);
                }

                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
            firstRow++;
            row = sheet.getRow(firstRow);
        }


    }

    public void insertHatfok(XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheetAt(4);
        int firstRow = 2;
        XSSFRow row = sheet.getRow(firstRow);
        while (firstRow != 9) {
            int hatfok_be;
            String sql = "INSERT INTO hatfok(hatfok_be) VALUES(?)";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                if (row.getCell(2) != null) {
                    hatfok_be = (int) row.getCell(2).getNumericCellValue();
                    System.out.println(hatfok_be);
                    pstmt.setInt(1, hatfok_be);
                }
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
            firstRow++;
            row = sheet.getRow(firstRow);
        }

    }

    public void insertGozpar(XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheetAt(5);
        int firstRow = 2;
        XSSFRow row = sheet.getRow(firstRow);
        while (firstRow != 18) {
            int gozpar_be;
            String sql = "INSERT INTO gozpar(gozpar_be) VALUES(?)";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                if (row.getCell(2) != null) {
                    gozpar_be = (int) row.getCell(2).getNumericCellValue();
                    System.out.println(gozpar_be);
                    pstmt.setInt(1, gozpar_be);
                }
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
            firstRow++;
            row = sheet.getRow(firstRow);
        }

    }

    public void insertVizpar(XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheetAt(6);
        int firstRow = 2;
        XSSFRow row = sheet.getRow(firstRow);
        while (firstRow != 7) {
            int vizpar_be;
            String sql = "INSERT INTO vizpar(vizpar_be) VALUES(?)";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                if (row.getCell(2) != null) {
                    vizpar_be = (int) row.getCell(2).getNumericCellValue();
                    System.out.println(vizpar_be);
                    pstmt.setInt(1, vizpar_be);
                }
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
            firstRow++;
            row = sheet.getRow(firstRow);
        }

    }

    public void insertTuztadat(XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheetAt(7);
        int firstRow = 2;
        XSSFRow row = sheet.getRow(firstRow);
        while (firstRow != 28) {
            Double tuztadat_be;
            String sql = "INSERT INTO tuztadat(tuztadat_be) VALUES(?)";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                if (row.getCell(2) != null) {
                    tuztadat_be = row.getCell(2).getNumericCellValue();
                    System.out.println(tuztadat_be);
                    pstmt.setDouble(1, tuztadat_be);
                }
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
            firstRow++;
            row = sheet.getRow(firstRow);
        }


    }

    public void insertVegyes(XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheetAt(8);

        XSSFRow row = sheet.getRow(1);

        String projekt, datum;
        int lambda;
        String sql = "INSERT INTO vegyes(projekt,datum,lambda) VALUES(?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (row.getCell(1) != null) {
                projekt = row.getCell(1).getStringCellValue();
                System.out.println(projekt);
                pstmt.setString(1, projekt);
            }
            row = sheet.getRow(2);
            if (row.getCell(1) != null) {
                datum = row.getCell(1).getStringCellValue();
                System.out.println(datum);
                pstmt.setString(2, datum);
            }
            row = sheet.getRow(6);
            if (row.getCell(2) != null) {
                lambda = (int) row.getCell(2).getNumericCellValue();
                System.out.println(lambda);
                pstmt.setInt(3, lambda);
            }

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public void insertLogika(XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheetAt(9);
        int firstRow = 8;
        XSSFRow row = sheet.getRow(firstRow);
        while (row.getCell(0).equals(null)) {
            int fcsz,szsz,ffv1,ffv2,fmf1,fmf2,mf21,mf22,mf31,mf32,mf41,mf42;
            String felnev;
            String sql = "INSERT INTO logika(fcsz,szsz,ffv1,ffv2,fmf1,fmf2,mf21,mf22,mf31,mf32,mf41,mf42,felnev) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                if (row.getCell(0) != null) {
                    fcsz = (int) row.getCell(0).getNumericCellValue();
                    System.out.println(fcsz);
                    pstmt.setInt(1, fcsz);
                }
                if (row.getCell(1) != null) {
                    szsz = (int) row.getCell(1).getNumericCellValue();
                    System.out.println(szsz);
                    pstmt.setInt(2, szsz);
                }
                if (row.getCell(2) != null) {
                    ffv1 = (int) row.getCell(2).getNumericCellValue();
                    System.out.println(ffv1);
                    pstmt.setInt(3, ffv1);
                }
                if (row.getCell(3) != null) {
                    ffv2 = (int) row.getCell(3).getNumericCellValue();
                    System.out.println(ffv2);
                    pstmt.setInt(4, ffv2);
                }
                if (row.getCell(4) != null) {
                    fmf1 = (int) row.getCell(4).getNumericCellValue();
                    System.out.println(fmf1);
                    pstmt.setInt(5, fmf1);
                }
                if (row.getCell(5) != null) {
                    fmf2 = (int) row.getCell(5).getNumericCellValue();
                    System.out.println(fmf2);
                    pstmt.setInt(6, fmf2);
                }
                if (row.getCell(6) != null) {
                    mf21 = (int) row.getCell(6).getNumericCellValue();
                    System.out.println(mf21);
                    pstmt.setInt(7, mf21);
                }
                if (row.getCell(7) != null) {
                    mf22 = (int) row.getCell(7).getNumericCellValue();
                    System.out.println(mf22);
                    pstmt.setInt(8, mf22);
                }
                if (row.getCell(8) != null) {
                    mf31 = (int) row.getCell(8).getNumericCellValue();
                    System.out.println(mf31);
                    pstmt.setInt(9, mf31);
                }
                if (row.getCell(9) != null) {
                    mf32 = (int) row.getCell(9).getNumericCellValue();
                    System.out.println(mf32);
                    pstmt.setInt(10, mf32);
                }
                if (row.getCell(10) != null) {
                    mf41 = (int) row.getCell(10).getNumericCellValue();
                    System.out.println(mf41);
                    pstmt.setInt(11, mf41);
                }
                if (row.getCell(11) != null) {
                    mf42 = (int) row.getCell(11).getNumericCellValue();
                    System.out.println(mf42);
                    pstmt.setInt(12, mf42);
                }
                if (row.getCell(12) != null) {
                    felnev = row.getCell(12).getStringCellValue();
                    System.out.println(felnev);
                    pstmt.setString(13, felnev);
                }
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
            firstRow += 1;
            row = sheet.getRow(firstRow);
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


