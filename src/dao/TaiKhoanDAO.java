/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.Controller;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Diem;
import pojo.DiemId;
import pojo.Taikhoan;

/**
 *
 * @author phanc
 */
public class TaiKhoanDAO {
    public static Taikhoan layThongTinTK(String mssv) {
        Taikhoan tk = null;
        Session session = Controller.getSessionFactory().openSession();
        try {
            tk = (Taikhoan) session.get(Taikhoan.class, mssv);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return tk;
    }
    
    public static boolean themTaiKhoan(Taikhoan tk) {
        Session session = Controller.getSessionFactory().openSession();
        if (TaiKhoanDAO.layThongTinTK(tk.getMssv())!=null) {
        return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(tk);
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
    
    public static boolean capNhatTaiKhoan(Taikhoan tk) {
        Session session = Controller.getSessionFactory().openSession();
        if (TaiKhoanDAO.layThongTinTK(tk.getMssv())==null) {
        return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(tk);
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
