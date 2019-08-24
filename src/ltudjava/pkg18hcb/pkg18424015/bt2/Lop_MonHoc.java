/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.pkg18hcb.pkg18424015.bt2;

import dao.LopMonHocDAO;
import dao.SinhVienDAO;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import pojo.Sinhvien;
import pojo.SinhvienMonhoc;
import pojo.SinhvienMonhocId;

/**
 *
 * @author phanc
 */
public class Lop_MonHoc extends JPanel implements ActionListener{
    
    JPanel pnClass;
    JButton btnCreate, btnDelete;
    JTable table;
    JScrollPane jspDSLop;
    JTextField txtClassCre, txtStudentIDCre, txtStudentNameCre, txtGenderCre, txtCMNDCre, txtSubjectCre, txtClassDel, txtMSSVDel, txtSubjectDel;
    
    public JPanel QLLMH() {
    //Class
        pnClass = new JPanel();
        TitledBorder titleClass = new TitledBorder("Danh sách lớp môn học");
        pnClass.setBorder(titleClass);
        pnClass.setLayout(new GridLayout(2, 1));
        
        JPanel pnInput = new JPanel();
        pnInput.setLayout(new GridLayout(1, 2));
        
        JPanel pnCreate = new JPanel();
        pnCreate.setLayout(new GridLayout(10, 2, 2, 2));
        TitledBorder titleCreate = new TitledBorder("Thêm mới");
        pnCreate.setBorder(titleCreate);
        JLabel lblClassCre = new JLabel("Tên lớp");
        txtClassCre = new JTextField(20);
        JLabel lblSubjectCre = new JLabel("Môn học");
        txtSubjectCre = new JTextField(20);
        JLabel lblStudentIDCre = new JLabel("Mã sinh viên");
        txtStudentIDCre = new JTextField(20);
        JLabel lblStudentNameCre = new JLabel("Họ tên");
        txtStudentNameCre = new JTextField(20);
        JLabel lblGenderCre = new JLabel("Giới tính");
        txtGenderCre = new JTextField(20);
        JLabel lblCMNDCre = new JLabel("CMND");
        txtCMNDCre = new JTextField(20);
        btnCreate = new JButton("Lưu");
        pnCreate.add(lblClassCre);
        pnCreate.add(txtClassCre);
        pnCreate.add(lblSubjectCre);
        pnCreate.add(txtSubjectCre);
        pnCreate.add(lblStudentIDCre);
        pnCreate.add(txtStudentIDCre);
        pnCreate.add(lblStudentNameCre);
        pnCreate.add(txtStudentNameCre);
        pnCreate.add(lblGenderCre);
        pnCreate.add(txtGenderCre);
        pnCreate.add(lblCMNDCre);
        pnCreate.add(txtCMNDCre);
        pnCreate.add(btnCreate);
        pnInput.add(pnCreate);
        
        JPanel pnDelete = new JPanel();
        pnDelete.setLayout(new GridLayout(11, 2, 2, 2));
        TitledBorder titleDelete = new TitledBorder("Xoá sinh viên");
        pnDelete.setBorder(titleDelete);
//        JLabel lblClassDel = new JLabel("Tên lớp");
//        txtClassDel = new JTextField(20);
        JLabel lblSubjectDel = new JLabel("Mã môn học");
        txtSubjectDel = new JTextField(20);
        JLabel lblMSSVDel = new JLabel("Mã sinh viên");
        txtMSSVDel = new JTextField(20);
        btnDelete = new JButton("Xoá");
//        pnDelete.add(lblClassDel);
//        pnDelete.add(txtClassDel);
        pnDelete.add(lblSubjectDel);
        pnDelete.add(txtSubjectDel);
        pnDelete.add(lblMSSVDel);
        pnDelete.add(txtMSSVDel);
        pnDelete.add(btnDelete);
        pnInput.add(pnDelete);

        JPanel pnListSV = new JPanel();
        TitledBorder titleLitsSV = new TitledBorder("Danh sách lớp môn học");
        pnListSV.setBorder(titleLitsSV);
        pnListSV.setLayout(new GridLayout(1, 1));
        table = new JTable();
        jspDSLop = new JScrollPane(table);
        pnListSV.add(jspDSLop);
        
        pnClass.add(pnInput);
        pnClass.add(pnListSV);
        
        //Add function
        btnCreate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnCreate.addActionListener(this);
        return pnClass;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnCreate){
            if (!txtSubjectCre.getText().isEmpty() &&!txtStudentIDCre.getText().isEmpty() && !txtStudentNameCre.getText().isEmpty() && !txtClassCre.getText().isEmpty()) {
                SinhvienMonhocId svId = new SinhvienMonhocId(txtStudentIDCre.getText(), txtSubjectCre.getText()) ;
                SinhvienMonhoc svmh = new SinhvienMonhoc(svId, txtStudentNameCre.getText(), txtGenderCre.getText(), txtCMNDCre.getText(), txtClassCre.getText());
                boolean create = LopMonHocDAO.themSinhVienMonHoc(svmh);
                if(create){
                    getDanhSachLopMonHoc(txtSubjectCre.getText());
                    JOptionPane.showMessageDialog(null, "Thêm thành công !!!");
                }else{
                    JOptionPane.showMessageDialog(null, "Thêm thất bại !!!");
                }
            }
        }
        
        if(e.getSource() == btnDelete){
            if (!txtMSSVDel.getText().isEmpty() &&!txtSubjectDel.getText().isEmpty() ) {
                boolean del = LopMonHocDAO.xoaSinhVienMonHoc(txtSubjectDel.getText(), txtMSSVDel.getText());
                if(del){
                    getDanhSachLopMonHoc(txtSubjectDel.getText());
                    JOptionPane.showMessageDialog(null, "Xoá thành công !!!");
                }else{
                    JOptionPane.showMessageDialog(null, "Xoá thất bại !!!");
                }
            }
        }
    }
    public void getDanhSachLopMonHoc(String subjectName) {
        //sp.setVisible(true);
        String[] columns = new String[]{
            "Mã sinh viên",
            "Họ tên",
            "Giới tính",
            "CMND",
            "Lớp",
            "Môn học"
        };
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);

        List<SinhvienMonhoc> listStudentsSubject = null;
        listStudentsSubject = LopMonHocDAO.layDanhSachSinhVienTheoMonHoc(subjectName);
        listStudentsSubject.forEach(item -> {
            model.addRow(new Object[]{item.getId().getMssv(),
                item.getId().getMonHoc(),
                item.getHoTen(),
                item.getGioiTinh(),
                item.getCmnd(),
                item.getLop()
            });
        });
        table.setModel(model);

    }
}
