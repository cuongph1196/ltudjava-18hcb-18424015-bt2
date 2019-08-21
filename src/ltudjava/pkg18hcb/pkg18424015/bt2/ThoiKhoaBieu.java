/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.pkg18hcb.pkg18424015.bt2;

import dao.SinhVienDAO;
import dao.ThoiKhoaBieuDAO;
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
import pojo.Thoikhoabieu;
import pojo.ThoikhoabieuId;

/**
 *
 * @author phanc
 */
public class ThoiKhoaBieu extends JPanel implements ActionListener{
    
    JPanel pnClass;
    File selectedFile;
    JButton btnSelect, btnImport, btnCreate;
    JTable table;
    JScrollPane jspDSLop;
    JTextField txtClassImp;
    public JPanel Import() {
    //Class
        pnClass = new JPanel();
        TitledBorder titleClass = new TitledBorder("Danh sách thời khoá biểu");
        pnClass.setBorder(titleClass);
        pnClass.setLayout(new GridLayout(2, 1));
        
        JPanel pnInput = new JPanel();
        pnInput.setLayout(new GridLayout(1, 1));
        
        
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
        

        JPanel pnListSV = new JPanel();
        TitledBorder titleLitsSV = new TitledBorder("Danh sách thời khoá biểu");
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
        return pnClass;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnSelect){
            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int returnValue = jfc.showOpenDialog(ThoiKhoaBieu.this);
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
                    readFile(pathInput,"Schedule");
                    getDanhSachTKB(txtClassImp.getText());
                } catch (IOException ex) {
                    Logger.getLogger(Home_Layout.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin !!!");
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
            while ((i = br.readLine()) != "") {
                String[] item = i.split(",");
                ThoikhoabieuId tkbId = new ThoikhoabieuId(item[1], txtClassImp.getText());
                Thoikhoabieu tkb = new Thoikhoabieu(tkbId, item[2], item[3]);
                ThoiKhoaBieuDAO.themThoiKhoaBieu(tkb);
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            br.close();
        }
    }
    
    public void getDanhSachTKB(String className) {
        //sp.setVisible(true);
        String[] columns = new String[]{
            "Mã môn học",
            "Tên môn học",
            "Phòng học",
            "Lớp"
        };
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);

        List<Thoikhoabieu> listSchedules = null;
        listSchedules = ThoiKhoaBieuDAO.layDanhSachThoiKhoaBieuTheoLop(className);
        listSchedules.forEach(item -> {
            System.out.print(item);
            model.addRow(new Object[]{item.getId().getMaMonHoc(),
                item.getTenMonHoc(),
                item.getPhongHoc(),
                item.getId().getLop()
            });
        });
        table.setModel(model);

    }
}
