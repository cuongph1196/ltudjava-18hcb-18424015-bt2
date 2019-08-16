/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.pkg18hcb.pkg18424015.bt2;

import dao.SinhVienDAO;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileSystemView;
import pojo.Sinhvien;

/**
 *
 * @author phanc
 */
public class Home_Layout extends JFrame implements ActionListener{
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
        //Class
        JPanel pnClass = new JPanel();
        TitledBorder titleClass = new TitledBorder("Import danh sách lớp");
        pnClass.setBorder(titleClass);
        
        lbFile = new JLabel();
        lbFile.setText("Chọn file cần import: ");
        lbFile.setBounds(10, 10, 200, 30);
        
        btnSelect = new JButton();
        btnSelect.setText("Chọn file");
        btnSelect.setBounds(210, 10, 100, 30);
        
        pnClass.add(lbFile);
        pnClass.add(btnSelect);
        
        
        //Schedule
        JPanel pnSchedule = new JPanel();
        TitledBorder titleSchedule = new TitledBorder("Import thời khoá biểu");
        pnSchedule.setBorder(titleSchedule);
        
        
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


        //Add function
        btnSelect.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnSelect){
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int returnValue = jfc.showOpenDialog(Home_Layout.this);
            // int returnValue = jfc.showSaveDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                System.out.println(selectedFile.getAbsolutePath());
                String pathInput = selectedFile.getAbsolutePath();
                try {
                    ReadFile(pathInput,"Class");
                } catch (IOException ex) {
                    Logger.getLogger(Home_Layout.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public void ReadFile(String pathInput, String type) throws FileNotFoundException, IOException{
        BufferedReader br = null;
        try{
        FileReader fr = new FileReader(pathInput);
        //br = new BufferedReader(new InputStreamReader(new FileInputStream(pathInput), StandardCharsets.UTF_8));
        br = new BufferedReader(fr);
        String i;
        br.readLine();
        while ((i = br.readLine()) != "") {
            System.out.print(i);
            String[] item = i.split(",");
            Sinhvien sv = new Sinhvien(item[1], null, item[2], item[3], item[4], null, null);
            SinhVienDAO.themSinhVien(sv);
        }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            br.close();
        }
    }
}
