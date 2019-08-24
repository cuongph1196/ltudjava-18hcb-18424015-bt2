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
import pojo.Diem;
import pojo.DiemId;
import pojo.Sinhvien;
import pojo.Thoikhoabieu;

/**
 *
 * @author phanc
 */
public class DiemDAO {
    public static List<Diem> layDanhSachDiem() {
        List<Diem> ds = null;
        Session session = Controller.getSessionFactory().openSession();
        try {
            String hql = "select d from Diem d";
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
    
    public static List<Diem> layDanhSachDiemTheoMSSV(String mssv) {
        List<Diem> ds = null;
        Session session = Controller.getSessionFactory().openSession();
        try {
            String hql = "select d from Diem d where d.id.mssv=:mssv";
            Query query = session.createQuery(hql);
            query.setString("mssv", mssv);
            ds = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
    
    public static List<Diem> layDanhSachDiemTheoLopMonHoc(String tenLop, String tenMonHoc) {
        List<Diem> ds = null;
        Session session = Controller.getSessionFactory().openSession();
        try {
            String hql = "select d from Diem d where d.id.lop=:lop and d.id.monHoc=:monhoc";
            Query query = session.createQuery(hql);
            query.setString("lop", tenLop);
            query.setString("monhoc", tenMonHoc);
            ds = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
    public static Diem layThongTinDiem(String mssv, String maLop, String maMonHoc) {
        Diem d = null;
        Session session = Controller.getSessionFactory().openSession();
        try {
            DiemId dId = new DiemId(mssv, maLop, maMonHoc);
            d = (Diem) session.get(Diem.class, dId);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return d;
    }
    
    public static boolean themDiem(Diem d) {
        Session session = Controller.getSessionFactory().openSession();
        if (DiemDAO.layThongTinDiem(d.getId().getMssv(),d.getId().getLop(),d.getId().getMonHoc())!=null) {
        return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(d);
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
    
    public static boolean capNhatThongTinSinhVien(Diem d) {
        Session session = Controller.getSessionFactory().openSession();
        if (DiemDAO.layThongTinDiem(d.getId().getMssv(),d.getId().getLop(),d.getId().getMonHoc())!=null) {
        return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(d);
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
    
    public static boolean xoaSinhVien(String mssv, String maLop, String maMonHoc) {
        Session session = Controller.getSessionFactory().openSession();
        Diem d = DiemDAO.layThongTinDiem(mssv, maLop, maMonHoc);
        if(d==null){
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(d);
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
