/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.pkg18hcb.pkg18424015.bt2;

import dao.DiemDAO;
import dao.TaiKhoanDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import pojo.Taikhoan;

/**
 *
 * @author phanc
 */
public class DoiMatKhau extends JPanel implements ActionListener{
    JPanel pnChangePass;
    JLabel lbPassword, lbRePassword;
    JTextField txtUsername;
    JPasswordField txtPassword,txtRePassword;
    JButton btnChangePass;
    
    public JPanel DoiMatKhau(String userName){
        
        pnChangePass = new JPanel();
        TitledBorder title = new TitledBorder("Đổi mật khẩu");
        pnChangePass.setBorder(title);
        pnChangePass.setLayout(null);
        
        //label
        lbPassword = new JLabel();
        lbPassword.setText("Nhập mật khẩu: ");
//        lbUsername.setForeground(Color.red);
        lbPassword.setBounds(15, 20, 100, 30);
        lbRePassword = new JLabel();
        lbRePassword.setText("Xác nhận mật khẩu: ");
        lbRePassword.setBounds(15, 60, 100, 30);
        
        //text field
        txtUsername = new JTextField();
        txtUsername.setText(userName);
        txtUsername.setVisible(false);
        
        txtPassword = new JPasswordField();
        txtPassword.setBounds(115, 20, 250, 30);
        txtRePassword = new JPasswordField();
        txtRePassword.setBounds(115, 60, 250, 30);
        
        //button
        btnChangePass = new JButton();
        btnChangePass.setText("Đổi mật khẩu");
        btnChangePass.setBounds(100, 110, 200, 30);
        
        
        //add lablel và text field vào panel
        pnChangePass.add(lbPassword);
         pnChangePass.add(lbRePassword);
        pnChangePass.add(txtUsername);
        pnChangePass.add(txtPassword);
        pnChangePass.add(txtRePassword);
        pnChangePass.add(btnChangePass);
        
        
        //add panel vào swing
        this.add(pnChangePass);
        btnChangePass.addActionListener(this);
        
        return pnChangePass;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnChangePass){
            char[] pw = txtPassword.getPassword();
            String pass = new String(pw);
            char[] rpw = txtRePassword.getPassword();
            String repass = new String(rpw);
            if (!txtUsername.getText().isEmpty() && !pass.isEmpty() && !repass.isEmpty()) {
                Taikhoan tk = TaiKhoanDAO.layThongTinTK(txtUsername.getText());
                tk.setMssv(txtUsername.getText());
                tk.setMatKhau(pass);
                boolean create = TaiKhoanDAO.capNhatTaiKhoan(tk);
                if(create){
//                    DangNhap login = new DangNhap("Login");
//                    login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                    login.setVisible(true);
//                    login.setSize(400, 200);
                    
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công !!!");
                }else{
                    JOptionPane.showMessageDialog(null, "Cập nhật thất bại !!!");
                }
            }
        }
    }
    
}
