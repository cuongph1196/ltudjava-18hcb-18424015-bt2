/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.pkg18hcb.pkg18424015.bt2;

import dao.SinhVienDAO;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import pojo.Sinhvien;

/**
 *
 * @author phanc
 */
public class DangNhap extends JFrame implements ActionListener{
    JPanel pnLogin;
    JLabel lbUsername,lbPassword;
    JTextField txtUsername;
    JPasswordField txtPassword;
    JButton btnLogin, btnExit;
    public DangNhap(String tieuDe){
        super(tieuDe);
        
        pnLogin = new JPanel();
        TitledBorder title = new TitledBorder("Đăng nhập");
        pnLogin.setBorder(title);
//        pnLogin.setLayout(new BoxLayout(pnLogin,BoxLayout.Y_AXIS));
        pnLogin.setLayout(null);
        
        //label
        lbUsername = new JLabel();
        lbUsername.setText("Tên đăng nhập: ");
//        lbUsername.setForeground(Color.red);
        lbUsername.setBounds(15, 20, 100, 30);
        lbPassword = new JLabel();
        lbPassword.setText("Mật khẩu: ");
        lbPassword.setBounds(15, 60, 100, 30);
        
        //text field
        txtUsername = new JTextField();
        txtUsername.setBounds(115, 20, 250, 30);
        txtPassword = new JPasswordField();
        txtPassword.setBounds(115, 60, 250, 30);
        
        //button
        btnLogin = new JButton();
        btnLogin.setText("Đăng nhập");
        btnLogin.setBounds(100, 110, 100, 30);
        btnExit = new JButton();
        btnExit.setText("Thoát");
        btnExit.setBounds(200, 110, 100, 30);
        
        
        //add lablel và text field vào panel
        pnLogin.add(lbUsername);
        pnLogin.add(lbPassword);
        pnLogin.add(txtUsername);
        pnLogin.add(txtPassword);
        pnLogin.add(btnLogin);
        pnLogin.add(btnExit);
        
        
        //add panel vào swing
        //this.add(pnLogin);
        Container con = getContentPane();
        con.add(pnLogin);
        
        btnLogin.addActionListener(this);
        btnExit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnLogin){
            String tk = txtUsername.getText().toString();
            char[] pass = txtPassword.getPassword();
            String mk = new String(pass);
            Sinhvien sv = SinhVienDAO.layThongTinSinhVien(tk);
            if(sv!= null){
                if( mk.equals(sv.getMatKhau())){
                    System.out.println("Đăng nhập thành công");
//                    JOptionPane.showMessageDialog(null,"Đăng nhập thành công !!!");
                    //main
                    Home_Layout swing = new Home_Layout("Main");
                    swing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    swing.setVisible(true);
                    swing.setSize(500, 300);
                    this.setVisible(false);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Đăng nhập thất bại !!!");
                }
            }else{
                JOptionPane.showMessageDialog(null,"Tài khoản không tồn tại !!!");
                System.out.println("Tài khoản không tồn tại");
            }
        }
        if(e.getSource() == btnExit){
            System.exit(0);
        }
    }
}
