/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.pkg18hcb.pkg18424015.bt2;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import dao.SinhVienDAO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import pojo.Sinhvien;

/**
 *
 * @author phanc
 */
public class Ltudjava18hcb18424015Bt2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        List<Sinhvien> ds = SinhVienDAO.layDanhSachSinhVien();
//        for(int i=0; i<ds.size(); i++){
//            Sinhvien sv=ds.get(i);
//            System.out.println("MSSV: "+sv.getMssv());
//            System.out.println("Họ và tên: "+sv.getHoTen());
//            System.out.println("CMND: "+ sv.getCmnd());
//        }
        
//        Sinhvien sv = SinhVienDAO.layThongTinSinhVien("SV0001");
//        if(sv!= null){
//            System.out.println("MSSV: " + sv.getMssv());
//            System.out.println("Họ và tên: " + sv.getHoTen());
//            System.out.println("CMND: " + sv.getCmnd());
//        }else{
//            System.out.println("Sinh viên 0312143 không tồn tại");
//        }

//            Sinhvien sv = new Sinhvien();
//            sv.setMssv("0312171");
//            sv.setHoTen("Tạ Tấn Thêm");
//            sv.setCmnd("312548597");
////            Calendar calendar = Calendar.getInstance();
////            calendar.set(1985, 5, 18);
////            Date d = calendar.getTime();
////            sv.setNgaySinh(d);
////            sv.setDiaChi("Vũng Liêm – Vĩnh Long");
//            boolean kq = SinhVienDAO.themSinhVien(sv);
//            if (kq == true) {
//              System.out.println("Thêm thành công");
//            } else {
//              System.out.println("Thêm thất bại");
//            }

//            Sinhvien sv = SinhVienDAO.layThongTinSinhVien("0312171");
//            if (sv != null) {
//                sv.setHoTen("Tạ Tấn Thêm");
//                sv.setCmnd("123456789");
//                boolean kq = SinhVienDAO.capNhatThongTinSinhVien(sv);
//                if (kq == true) {
//                    System.out.println("Cập nhật thành công");
//                } else {
//                    System.out.println("Cập nhật thất bại");
//                }
//            }

//        boolean kq = SinhVienDAO.xoaSinhVien("0312171");
//        if (kq == true) {
//            System.out.println("Xóa thành công");
//        } else {
//            System.out.println("Xóa thất bại");
//        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
//                JFrame swing = new JFrame("Login");
//                swing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                swing.setVisible(true);
//                swing.setSize(400, 300);
                login login = new login("Login");
                login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                login.setVisible(true);
                login.setSize(400, 200);
                //login.setMenuBar();
            }
        });
        
        
    }
}
