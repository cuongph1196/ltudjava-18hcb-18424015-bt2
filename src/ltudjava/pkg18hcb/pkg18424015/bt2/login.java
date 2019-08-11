/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.pkg18hcb.pkg18424015.bt2;

import java.awt.Color;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author phanc
 */
public class login extends JFrame{
    JPanel pnLogin;
    JLabel lbUsername,lbPassword;
    JTextField txtUsername;
    JPasswordField txtPassword;
    JButton btnLogin;
    public login(String tieuDe){
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
        btnLogin.setBounds(150, 110, 100, 30);
        
        
        //add lablel và text field vào panel
        pnLogin.add(lbUsername);
        pnLogin.add(lbPassword);
        pnLogin.add(txtUsername);
        pnLogin.add(txtPassword);
        pnLogin.add(btnLogin);
        
        
        //add panel vào swing
        //this.add(pnLogin);
        Container con = getContentPane();
        con.add(pnLogin);
    }
}
