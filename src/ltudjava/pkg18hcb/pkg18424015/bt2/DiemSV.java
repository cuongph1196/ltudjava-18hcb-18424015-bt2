/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.pkg18hcb.pkg18424015.bt2;

import dao.DiemDAO;
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
import pojo.Diem;
import pojo.DiemId;
import pojo.Sinhvien;

/**
 *
 * @author phanc
 */
public class DiemSV extends JPanel implements ActionListener{
    JPanel pnScore;
    File selectedFile;
    JButton btnSelect, btnImport, btnCreate;
    JTable table;
    JScrollPane jspDSLop;
    JTextField txtClassImp,txtSubjectImp, txtClassCre, txtStudentIDCre, txtStudentNameCre, txtGenderCre, txtCMNDCre;
    
    public JPanel Import() {
    //Class
        pnScore = new JPanel();
        TitledBorder titleClass = new TitledBorder("Danh sách lớp");
        pnScore.setBorder(titleClass);
        pnScore.setLayout(new GridLayout(2, 1));
        
        JPanel pnInput = new JPanel();
        pnInput.setLayout(new GridLayout(1, 2));
        
        
        JPanel pnImport = new JPanel();
        pnImport.setLayout(new GridLayout(10, 2, 2, 2));
        TitledBorder titleImport = new TitledBorder("Import");
        pnImport.setBorder(titleImport);
        
        JLabel lblClassImp = new JLabel("Tên lớp");
        txtClassImp = new JTextField(20);
        JLabel lblSubjectImp = new JLabel("Tên môn học");
        txtSubjectImp = new JTextField(20);
        btnSelect = new JButton("Chọn file");
        btnImport = new JButton("Import");
        pnImport.add(lblClassImp);
        pnImport.add(txtClassImp);
        pnImport.add(lblSubjectImp);
        pnImport.add(txtSubjectImp);
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

        JPanel pnListScore = new JPanel();
        TitledBorder titleLitsScore = new TitledBorder("Danh sách điểm");
        pnListScore.setBorder(titleLitsScore);
        pnListScore.setLayout(new GridLayout(1, 1));
        table = new JTable();
        jspDSLop = new JScrollPane(table);
        pnListScore.add(jspDSLop);
        
        pnScore.add(pnInput);
        pnScore.add(pnListScore);
        
        //Add function
        btnSelect.addActionListener(this);
        btnImport.addActionListener(this);
        btnCreate.addActionListener(this);
        return pnScore;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnSelect){
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int returnValue = jfc.showOpenDialog(DiemSV.this);
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
                    readFile(pathInput);
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
                    getDanhSachDiem(txtClassCre.getText(),txtSubjectImp.getText());
                    JOptionPane.showMessageDialog(null, "Thêm thành công !!!");
                }else{
                    JOptionPane.showMessageDialog(null, "Thêm thất bại !!!");
                }
            }
        }
    }
    
    public void readFile(String pathInput) throws FileNotFoundException, IOException{
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
                DiemId dId = new DiemId(item[1], txtClassImp.getText(), txtSubjectImp.getText());
                Diem diem = new Diem(dId, item[2], Float.parseFloat(item[3]), Float.parseFloat(item[4]), Float.parseFloat(item[5]), Float.parseFloat(item[6]));
                create = DiemDAO.themDiem(diem);
                getDanhSachDiem(txtClassImp.getText(), txtSubjectImp.getText());
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            br.close();
        }
    }
    
    public void getDanhSachDiem(String className, String subjectName) {
        //sp.setVisible(true);
        String[] columns = new String[]{
            "Mã sinh viên",
            "Họ tên",
            "Điểm GK",
            "Điểm CK",
            "Điểm khác",
            "Điểm tổng",
            "Lớp",
            "Môn học"
        };
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);

        List<Diem> listScores = null;
        listScores = DiemDAO.layDanhSachDiemTheoLopMonHoc(className,subjectName);
        listScores.forEach(item -> {
            model.addRow(new Object[]{item.getId().getMssv(),
                item.getHoTen(),
                item.getDiemGk(),
                item.getDiemGk(),
                item.getDiemKhac(),
                item.getDiemTong(),
                item.getId().getLop(),
                item.getId().getMonHoc()
            });
        });
        table.setModel(model);

    }
    
}
