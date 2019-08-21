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
import pojo.Thoikhoabieu;
import pojo.ThoikhoabieuId;

/**
 *
 * @author phanc
 */
public class ThoiKhoaBieuDAO {
    public static List<Thoikhoabieu> layDanhSachThoiKhoaBieuTheoLop(String tenLop) {
        List<Thoikhoabieu> ds = null;
        Session session = Controller.getSessionFactory().openSession();
        try {
            String hql = "select tkb from Thoikhoabieu tkb where tkb.lop=:lop";
            Query query = session.createQuery(hql);
            query.setString("lop", tenLop);
            ds = query.list();
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return ds;
    }
    
    public static Thoikhoabieu layThongTinThoiKhoaBieu(String maLop, String maMonHoc) {
        Thoikhoabieu tkb = null;
        Session session = Controller.getSessionFactory().openSession();
        try {
            ThoikhoabieuId tkbId = new ThoikhoabieuId(maMonHoc, maLop);
            tkb = (Thoikhoabieu) session.get(Thoikhoabieu.class, tkbId);
        } catch (HibernateException ex) {
            //Log the exception
            System.err.println(ex);
        } finally {
            session.close();
        }
        return tkb;
    }
    
    public static boolean themThoiKhoaBieu(Thoikhoabieu tkb) {
        Session session = Controller.getSessionFactory().openSession();
        if (ThoiKhoaBieuDAO.layThongTinThoiKhoaBieu(tkb.getId().getLop(),tkb.getId().getMaMonHoc())!=null) {
        return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(tkb);
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
