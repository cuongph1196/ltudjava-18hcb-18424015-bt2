/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.Controller;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Sinhvien;
import dao.SinhVienDAO;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author phanc
 */
public class SinhVienDAO {
    public static List<Sinhvien> layDanhSachSinhVien() {
        List<Sinhvien> ds = null;
        Session session = Controller.getSessionFactory().openSession();
        try {
            String hql = "select sv from Sinhvien sv";
            Query query = session.createQuery(hql);
            ds = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
    
    public static Sinhvien layThongTinSinhVien(String maSinhVien) {
        Sinhvien sv = null;
        Session session = Controller.getSessionFactory().openSession();
        try {
            sv = (Sinhvien) session.get(Sinhvien.class, maSinhVien);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return sv;
    }
    
//    public static Sinhvien layThongTinSinhVien(String maSinhVien) {
//        Sinhvien sv = null;
//        Session session = Controller.getSessionFactory().openSession();
//        try {
////            sv = (Sinhvien) session.get(Sinhvien.class, maSinhVien);
//            String hql = "from Sinhvien sv where sv.mssv=:maSinhVien";
//            Query query = session.createQuery(hql);
//            query.setString("maSinhVien", maSinhVien);
//            sv=(Sinhvien)query;
////            for(int i=0; i<ds.size(); i++){
////                sv = ds.get(i);
////                String mssv=sv.getMssv();
////                String hoTen=sv.getHoTen();
////                String matKhau=sv.getMatKhau();
////            }
//        } catch (HibernateException ex) {
//            //Log the exception
//            System.err.println(ex);
//        } finally {
//            session.close();
//        }
//        return sv;
//    }
    
    public static Sinhvien kiemTraDangNhap(String maSinhVien, String matKhau) {
        Sinhvien sv = null;
        Session session = Controller.getSessionFactory().openSession();
        try {
            String query = "select sv.mssv from Sinhvien sv where sv.mssv='" + maSinhVien + "' and sv.matkhau='" + matKhau + "'";
            sv = (Sinhvien) session.get(query,sv);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return sv;
    }
    
    public static boolean themSinhVien(Sinhvien sv) {
        Session session = Controller.getSessionFactory().openSession();
        if (SinhVienDAO.layThongTinSinhVien(sv.getMssv())!=null) {
        return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(sv);
            transaction.commit();
        } catch (HibernateException ex) {
            //Log the exception
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
    
    public static boolean capNhatThongTinSinhVien(Sinhvien sv) {
        Session session = Controller.getSessionFactory().openSession();
        if (SinhVienDAO.layThongTinSinhVien(sv.getMssv()) == null) {
        return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(sv);
            transaction.commit();
        } catch (HibernateException ex) {
            //Log the exception
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
    
    public static boolean xoaSinhVien(String maSinhVien) {
        Session session = Controller.getSessionFactory().openSession();
        Sinhvien sv = SinhVienDAO.layThongTinSinhVien(maSinhVien);
        if(sv==null){
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(sv);
            transaction.commit();
        } catch (HibernateException ex) {
            //Log the exception
            transaction.rollback();
            System.err.println(ex);
        } finally {
            session.close();
        }
        return true;
    }
}
