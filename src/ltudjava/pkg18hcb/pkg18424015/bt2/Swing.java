/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.pkg18hcb.pkg18424015.bt2;

import java.awt.Color;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author phanc
 */
public class Swing extends JFrame{
    JPanel pnLogin;
    JLabel lbHoTen,lbDiaChi;
    public Swing(String tieuDe){
        super(tieuDe);
        
        pnLogin = new JPanel();
        pnLogin.setLayout(new BoxLayout(pnLogin,BoxLayout.Y_AXIS));
        
        lbHoTen = new JLabel();
        lbHoTen.setText("Họ tên: ");
        lbHoTen.setForeground(Color.red);
        
        lbDiaChi = new JLabel();
        lbDiaChi.setText("Địa chỉ: ");
        
        //add lablel vào panel
        pnLogin.add(lbHoTen);
        pnLogin.add(lbDiaChi);
        
        //add panel vào swing
        //this.add(pnLogin);
        Container con = getContentPane();
        con.add(pnLogin);
    }
}
