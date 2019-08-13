/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.pkg18hcb.pkg18424015.bt2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author phanc
 */
public class Import_DanhSachLop extends JFrame implements ActionListener{
    JPanel pnLogin;
    JLabel lbFile;
    JButton btnSelect, btnImport, btnExit;
    
    public Import_DanhSachLop(String tieuDe){
        super("Danh sách lớp");
        
        pnLogin = new JPanel();
        TitledBorder title = new TitledBorder("Import");
        pnLogin.setBorder(title);
        pnLogin.setLayout(null);
        
        lbFile = new JLabel();
        lbFile.setText("Chọn file cần import: ");
        lbFile.setBounds(10, 10, 200, 30);
        
        btnSelect = new JButton();
        btnSelect.setText("Chọn file");
        btnSelect.setBounds(210, 10, 100, 30);
        
        //
        pnLogin.add(lbFile);
        pnLogin.add(btnSelect);
        
        //
        this.add(pnLogin);
        
        //
        btnSelect.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = jfc.showOpenDialog(null);
        // int returnValue = jfc.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                System.out.println(selectedFile.getAbsolutePath());
        }
    }
}
