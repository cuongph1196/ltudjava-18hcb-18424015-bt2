/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.pkg18hcb.pkg18424015.bt2;

import dao.SinhVienDAO;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import pojo.Sinhvien;

/**
 *
 * @author phanc
 */
public class DSLop extends JPanel implements ActionListener{
    JPanel pnClass;
    JButton btnSelect;
    JTable table;
    JScrollPane jspDSLop;
    JTextField txtClass;
    public JPanel ListSV() {
    //Class
        pnClass = new JPanel();
        TitledBorder titleClass = new TitledBorder("Danh sách lớp");
        pnClass.setBorder(titleClass);
//        pnClass.setLayout(new GridLayout(2, 1));
        pnClass.setLayout(null);
        
        JPanel pnInput = new JPanel();
        pnInput.setLayout(new GridLayout(1, 1));
        
        JPanel pnSelect = new JPanel();
        TitledBorder titleSelect = new TitledBorder("Tìm kiếm");
        pnSelect.setBorder(titleSelect);
        pnSelect.setLayout(null);
        
        JLabel lblClass = new JLabel();
        lblClass.setText("Tên lớp: ");
        lblClass.setBounds(20, 20, 100, 30);
        txtClass = new JTextField();
        txtClass.setBounds(120, 20, 250, 30);
        
        btnSelect = new JButton("Tìm kiếm");
        btnSelect.setBounds(500, 20, 100, 30);
        
        pnSelect.add(lblClass);
        pnSelect.add(txtClass);
        pnSelect.add(btnSelect);
        pnInput.add(pnSelect);
        
        JPanel pnListSV = new JPanel();
        TitledBorder titleLitsSV = new TitledBorder("Danh sách lớp");
        pnListSV.setBorder(titleLitsSV);
        pnListSV.setLayout(new GridLayout(1, 1));
        table = new JTable();
        jspDSLop = new JScrollPane(table);
        pnListSV.add(jspDSLop);
        
        
        pnInput.setBounds(10, 20, 765, 150);
        pnListSV.setBounds(10, 180, 765, 450);
        pnClass.add(pnInput);
        pnClass.add(pnListSV);
        
        //Add function
        btnSelect.addActionListener(this);
        return pnClass;
    }
    
    public void getDanhSachSV(String className) {
        //sp.setVisible(true);
        String[] columns = new String[]{
            "Mã sinh viên",
            "Họ tên",
            "Giới tính",
            "CMND",
            "Lớp"
        };
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);

        List<Sinhvien> listStudents = null;
        listStudents = SinhVienDAO.layDanhSachSinhVienTheoLop(className);
        listStudents.forEach(item -> {
            model.addRow(new Object[]{item.getMssv(),
                item.getHoTen(),
                item.getGioiTinh(),
                item.getCmnd(),
                item.getLop()
            });
        });
        table.setModel(model);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnSelect){
            getDanhSachSV(txtClass.getText());
        }
    }
}
