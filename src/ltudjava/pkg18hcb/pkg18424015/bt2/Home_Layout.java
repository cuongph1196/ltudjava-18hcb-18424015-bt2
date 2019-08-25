/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.pkg18hcb.pkg18424015.bt2;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author phanc
 */
public class Home_Layout extends JFrame{
    JMenuBar jmbMain;
    JMenu jmClass, jmSchedule, jmSetting,jmClassSubject, jmScore;
    JMenuItem jmiImport, jmiTKB, jmiClSub,jmiClassList, jmiClSubList, jmiTKBList, jimScoreImp, jimScoreList, jimMyScore,jimLogout, jimChangePass;
    JPanel pnMain;
    JLabel lbFile;
    JButton btnSelect, btnImport, btnExit;
    public Home_Layout(String tieuDe, String userName, String role){
        super(tieuDe);
        System.out.println(role);
        jmbMain = new JMenuBar();
        jmClass = new JMenu("Lớp học");
        jmSchedule = new JMenu("Thời Khoá Biểu");
        jmClassSubject = new JMenu("Lớp Môn Học");
        jmScore = new JMenu("Điểm");
        jmSetting = new JMenu("Tuỳ chọn");
//        jmLogout = new JMenu(new AbstractAction("Đăng xuất") {
//            public void actionPerformed(ActionEvent e) {
//                System.out.println(".actionPerformed()");
//                DangNhap login = new DangNhap("Login");
//                login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                login.setVisible(true);
//                login.setSize(400, 200);
//            }
//        });
//        //Class
        Lop lp = new Lop();
        JPanel pnClass = lp.Import();
        
        DSLop dsl = new DSLop();
        JPanel pnClassList = dsl.ListSV();
        
        //Schedule
        ThoiKhoaBieu tkb = new ThoiKhoaBieu();
        JPanel pnSchedule = tkb.Import();
        
        DSThoiKhoaBieu dstkb = new DSThoiKhoaBieu();
        JPanel pnScheduleList = dstkb.ListTKB();
        
        //Class Subject
        Lop_MonHoc lmh = new Lop_MonHoc();
        JPanel pnClassSubject = lmh.QLLMH();
        
        DSLopMonHoc dsls = new DSLopMonHoc();
        JPanel pnClassSubjectList = dsls.ListSVMH();
        
        //Score
        DiemSV d = new DiemSV();
        JPanel pnScore = d.Import();
        
        DSDiem dsd = new DSDiem();
        JPanel pnScoreList = dsd.ListDiem();
        
        XemDiemCaNhan md = new XemDiemCaNhan();
        JPanel pnMyScore = md.DiemCaNhan(userName);
        
        //Change Pass
        DoiMatKhau dmk = new DoiMatKhau();
        JPanel pnChangePass = dmk.DoiMatKhau(userName);
        
        // add vào card chung
        pnMain = new JPanel(new CardLayout());
        pnMain.add(pnClass, "Class");
        pnMain.add(pnSchedule, "Schedule");
        pnMain.add(pnClassSubject, "ClassSubject");
        pnMain.add(pnClassList, "ClassList");
        pnMain.add(pnClassSubjectList, "ClassSubjectList");
        pnMain.add(pnScheduleList, "ScheduleList");
        pnMain.add(pnScore, "Score");
        pnMain.add(pnScoreList, "ScoreList");
        pnMain.add(pnMyScore, "MyScore");
        pnMain.add(pnChangePass, "ChangePass");
        
        CardLayout cl = (CardLayout) (pnMain.getLayout());
        jmiImport = new JMenuItem(new AbstractAction("Import danh sách lớp") {
            public void actionPerformed(ActionEvent e) {
                cl.show(pnMain, "Class");
            }
        });
        jmiTKB = new JMenuItem(new AbstractAction("Import thời khoá biểu") {
            public void actionPerformed(ActionEvent e) {
                cl.show(pnMain, "Schedule");
            }
        });
        
        jmiClSub = new JMenuItem(new AbstractAction("Quản lý lớp môn học") {
            public void actionPerformed(ActionEvent e) {
                cl.show(pnMain, "ClassSubject");
            }
        });
        
        jmiClassList = new JMenuItem(new AbstractAction("Danh sách lớp") {
            public void actionPerformed(ActionEvent e) {
                cl.show(pnMain, "ClassList");
            }
        });
        
        jmiClSubList = new JMenuItem(new AbstractAction("Danh sách lớp môn học") {
            public void actionPerformed(ActionEvent e) {
                cl.show(pnMain, "ClassSubjectList");
            }
        });
        
        jmiTKBList = new JMenuItem(new AbstractAction("Danh sách thời khoá biểu") {
            public void actionPerformed(ActionEvent e) {
                cl.show(pnMain, "ScheduleList");
            }
        });
        
        jimScoreImp = new JMenuItem(new AbstractAction("Import Điểm") {
            public void actionPerformed(ActionEvent e) {
                cl.show(pnMain, "Score");
            }
        });
        
        jimScoreList = new JMenuItem(new AbstractAction("Danh sách điểm") {
            public void actionPerformed(ActionEvent e) {
                cl.show(pnMain, "ScoreList");
            }
        });
        
        jimMyScore = new JMenuItem(new AbstractAction("Điểm cá nhân") {
            public void actionPerformed(ActionEvent e) {
                cl.show(pnMain, "MyScore");
            }
        });
        
        jimLogout = new JMenuItem(new AbstractAction("Đăng xuất") {
            public void actionPerformed(ActionEvent e) {
//                Container con = getContentPane();
//                con.setVisible(false);
                dispose();
                DangNhap login = new DangNhap("Login");
                login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                login.setVisible(true);
                login.setSize(400, 200);
            }
        });
        
        jimChangePass = new JMenuItem(new AbstractAction("Đổi mật khẩu") {
            public void actionPerformed(ActionEvent e) {
                cl.show(pnMain, "ChangePass");
            }
        });
        
        
        if(role.equals("GV")){
        jmClass.add(jmiImport);
        jmClass.add(jmiClassList);
        jmSchedule.add(jmiTKB);
        jmClassSubject.add(jmiClSub);
        jmClassSubject.add(jmiClSubList);
        jmScore.add(jimScoreImp);
        jmScore.add(jimScoreList);
        }
        jmSchedule.add(jmiTKBList);
        jmScore.add(jimMyScore);
        jmSetting.add(jimLogout);
        jmSetting.add(jimChangePass);
        
        if(role.equals("GV")){
            jmbMain.add(jmClass);
            jmbMain.add(jmClassSubject);
        }
        jmbMain.add(jmSchedule);
        jmbMain.add(jmScore);
        jmbMain.add(jmSetting);
        
        this.setJMenuBar(jmbMain);
        this.add(pnMain);
        
//        Container con = getContentPane();
//        con.add(BorderLayout.NORTH, jmbMain);
    }
}
