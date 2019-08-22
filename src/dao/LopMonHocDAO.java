/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.Controller;
import java.util.List;
import ltudjava.pkg18hcb.pkg18424015.bt2.Lop_MonHoc;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Sinhvien;
import pojo.SinhvienMonhoc;
import pojo.SinhvienMonhocId;
import pojo.Thoikhoabieu;
import pojo.ThoikhoabieuId;

/**
 *
 * @author phanc
 */
public class LopMonHocDAO {
    public static List<SinhvienMonhoc> layDanhSachSinhVienTheoMonHoc(String tenMonhoc) {
        List<SinhvienMonhoc> ds = null;
        Session session = Controller.getSessionFactory().openSession();
        try {
            String hql = "select svmh from SinhvienMonhoc svmh "; //where svmh.monHoc=:monhoc
            Query query = session.createQuery(hql);
//            query.setString("monhoc", tenMonhoc);
            ds = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
    
    public static SinhvienMonhoc layThongTinSinhVien(String maMonHoc, String maMSSV) {
        SinhvienMonhoc tkb = null;
        Session session = Controller.getSessionFactory().openSession();
        try {
            SinhvienMonhocId svmhId = new SinhvienMonhocId(maMSSV, maMonHoc);
            tkb = (SinhvienMonhoc) session.get(SinhvienMonhoc.class, svmhId);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return tkb;
    }
    
    public static boolean themSinhVienMonHoc(SinhvienMonhoc svmh) {
        Session session = Controller.getSessionFactory().openSession();
        if (LopMonHocDAO.layThongTinSinhVien(svmh.getId().getMssv(),svmh.getId().getMonHoc())!=null) {
        return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(svmh);
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
    public static boolean xoaSinhVienMonHoc(String maMonHoc, String maMSSV) {
        Session session = Controller.getSessionFactory().openSession();
        SinhvienMonhoc svmh = LopMonHocDAO.layThongTinSinhVien(maMonHoc,maMSSV);
        if(svmh==null){
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(svmh);
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
