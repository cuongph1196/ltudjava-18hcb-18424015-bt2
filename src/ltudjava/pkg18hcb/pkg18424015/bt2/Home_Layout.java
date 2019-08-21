/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.pkg18hcb.pkg18424015.bt2;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author phanc
 */
public class Home_Layout extends JFrame{
    JMenuBar jmbMain;
    JMenu jmClass, jmSchedule, jmLogout;
    JMenuItem jmiImport, jmiTKB;
    JPanel pnMain;
    JLabel lbFile;
    JButton btnSelect, btnImport, btnExit;
    public Home_Layout(String tieuDe){
        super(tieuDe);
        jmbMain = new JMenuBar();
        jmClass = new JMenu("Lớp học");
        jmSchedule = new JMenu("Thời Khoá Biểu");
        jmLogout = new JMenu(new AbstractAction("Đăng xuất") {
            public void actionPerformed(ActionEvent e) {
                DangNhap login = new DangNhap("Login");
                login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                login.setVisible(true);
                login.setSize(400, 200);
            }
        });
//        //Class
        Lop lp = new Lop();
        JPanel pnClass = lp.Import();
        
        //Schedule
        ThoiKhoaBieu tkb = new ThoiKhoaBieu();
        JPanel pnSchedule = tkb.Import();
        
        
        // add vào card chung
        pnMain = new JPanel(new CardLayout());
        pnMain.add(pnClass, "Class");
        pnMain.add(pnSchedule, "Schedule");
        
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
        
        jmClass.add(jmiImport);
        jmSchedule.add(jmiTKB);
        jmbMain.add(jmClass);
        jmbMain.add(jmSchedule);
        this.setJMenuBar(jmbMain);
        this.add(pnMain);
        
//        Container con = getContentPane();
//        con.add(BorderLayout.NORTH, jmbMain);
    }
}
