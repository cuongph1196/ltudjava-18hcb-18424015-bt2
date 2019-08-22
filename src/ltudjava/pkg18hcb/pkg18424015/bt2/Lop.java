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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import pojo.Sinhvien;

/**
 *
 * @author phanc
 */
public class Lop extends JPanel implements ActionListener{
    JPanel pnClass;
    File selectedFile;
    JButton btnSelect, btnImport, btnCreate;
    JTable table;
    JScrollPane jspDSLop;
    JTextField txtClassImp, txtClassCre, txtStudentIDCre, txtStudentNameCre, txtGenderCre, txtCMNDCre;
    public JPanel Import() {
    //Class
        pnClass = new JPanel();
        TitledBorder titleClass = new TitledBorder("Danh sách lớp");
        pnClass.setBorder(titleClass);
        pnClass.setLayout(new GridLayout(2, 1));
        
        JPanel pnInput = new JPanel();
        pnInput.setLayout(new GridLayout(1, 2));
        
        
        JPanel pnImport = new JPanel();
        pnImport.setLayout(new GridLayout(10, 2, 2, 2));
        TitledBorder titleImport = new TitledBorder("Import");
        pnImport.setBorder(titleImport);
        JLabel lblClassImp = new JLabel("Tên lớp");
        txtClassImp = new JTextField(20);
        btnSelect = new JButton("Chọn file");
        btnImport = new JButton("Import");
        pnImport.add(lblClassImp);
        pnImport.add(txtClassImp);
        pnImport.add(btnSelect);
        pnImport.add(btnImport);
        pnInput.add(pnImport);
        
        JPanel pnCreate = new JPanel();
        pnCreate.setLayout(new GridLayout(11, 2, 2, 2));
        TitledBorder titleCreate = new TitledBorder("Thêm mới");
        pnCreate.setBorder(titleCreate);
        JLabel lblClassCre = new JLabel("Tên lớp");
        txtClassCre = new JTextField(20);
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

        JPanel pnListSV = new JPanel();
        TitledBorder titleLitsSV = new TitledBorder("Danh sách lớp");
        pnListSV.setBorder(titleLitsSV);
        pnListSV.setLayout(new GridLayout(1, 1));
        table = new JTable();
        jspDSLop = new JScrollPane(table);
        pnListSV.add(jspDSLop);
        
        pnClass.add(pnInput);
        pnClass.add(pnListSV);
        
        //Add function
        btnSelect.addActionListener(this);
        btnImport.addActionListener(this);
        btnCreate.addActionListener(this);
        return pnClass;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnSelect){
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int returnValue = jfc.showOpenDialog(Lop.this);
            // int returnValue = jfc.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                selectedFile = jfc.getSelectedFile();
                System.out.println(selectedFile.getAbsolutePath());
            }
        }
        if(e.getSource() == btnImport){
            if (!txtClassImp.getText().isEmpty() && selectedFile != null) {
                String pathInput = selectedFile.getAbsolutePath();
                try {
                    readFile(pathInput,"Class");
//                    getDanhSachSV(txtClassImp.getText());
                } catch (IOException ex) {
                    Logger.getLogger(Home_Layout.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin !!!");
            }
        }
        if(e.getSource() == btnCreate){
            if (!txtStudentIDCre.getText().isEmpty() && !txtStudentNameCre.getText().isEmpty() && !txtClassCre.getText().isEmpty()) {
                Sinhvien sv = new Sinhvien(txtStudentIDCre.getText(), null, txtStudentNameCre.getText(), txtGenderCre.getText(), txtCMNDCre.getText(), txtClassCre.getText(), null, null);
                boolean create = SinhVienDAO.themSinhVien(sv);
                if(create){
                    getDanhSachSV(txtClassCre.getText());
                    JOptionPane.showMessageDialog(null, "Thêm thành công !!!");
                }else{
                    JOptionPane.showMessageDialog(null, "Thêm thất bại !!!");
                }
            }
        }
    }
    
    public void readFile(String pathInput, String type) throws FileNotFoundException, IOException{
        BufferedReader br = null;
        try{
            FileReader fr = new FileReader(pathInput);
            //br = new BufferedReader(new InputStreamReader(new FileInputStream(pathInput), StandardCharsets.UTF_8));
            br = new BufferedReader(fr);
            String i;
            br.readLine();
            boolean create = false;
            while ((i = br.readLine()) != "") {
                System.out.print(i);
                String[] item = i.split(",");
                Sinhvien sv = new Sinhvien(item[1], null, item[2], item[3], item[4], txtClassImp.getText(), null, null);
//                SinhVienDAO.themSinhVien(sv);
                create = SinhVienDAO.themSinhVien(sv);
                getDanhSachSV(txtClassImp.getText());
            }
            
//            if(create == true){
//                getDanhSachSV(txtClassImp.getText());
//                JOptionPane.showMessageDialog(null, "Thêm thành công !!!");
//            }else{
//                JOptionPane.showMessageDialog(null, "Thêm thất bại !!!");
//            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            br.close();
        }
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
}
