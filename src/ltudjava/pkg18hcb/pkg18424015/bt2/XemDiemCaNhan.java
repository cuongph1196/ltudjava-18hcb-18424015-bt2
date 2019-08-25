/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.pkg18hcb.pkg18424015.bt2;

import dao.DiemDAO;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import pojo.Diem;

/**
 *
 * @author phanc
 */
public class XemDiemCaNhan extends JPanel implements ActionListener{
    JPanel pnScore;
    JButton btnSelect;
    JTable table;
    JScrollPane jspDSDiem;
    JTextField txtMSSV, txtSubject;
    
    public JPanel DiemCaNhan(String userName) {
    //Class
        pnScore = new JPanel();
        TitledBorder titleScore = new TitledBorder("Danh sách điểm");
        pnScore.setBorder(titleScore);
        pnScore.setLayout(null);
        
        JPanel pnInput = new JPanel();
        pnInput.setLayout(new GridLayout(1, 1));
        
        JPanel pnSelect = new JPanel();
        TitledBorder titleSelect = new TitledBorder("Tìm kiếm");
        pnSelect.setBorder(titleSelect);
        pnSelect.setLayout(null);
        
//        JLabel lblMSSV = new JLabel();
//        lblMSSV.setText("MSSV: ");
//        lblMSSV.setBounds(20, 20, 100, 30);
        txtMSSV = new JTextField();
        txtMSSV.setBounds(20, 20, 200, 30);
        txtMSSV.setText(userName);
        txtMSSV.disable();
//        txtMSSV.setVisible(false);

        JLabel lblSub = new JLabel();
        lblSub.setText("Môn học: ");
        lblSub.setBounds(250, 20, 100, 30);
        txtSubject = new JTextField();
        txtSubject.setBounds(370, 20, 200, 30);
        
        btnSelect = new JButton("Tìm kiếm");
        btnSelect.setBounds(650, 20, 100, 30);
        
//        pnSelect.add(lblMSSV);
        pnSelect.add(txtMSSV);
        pnSelect.add(lblSub);
        pnSelect.add(txtSubject);
        pnSelect.add(btnSelect);
        pnInput.add(pnSelect);
        
        JPanel pnListSV = new JPanel();
        TitledBorder titleLitsSV = new TitledBorder("Danh sách lớp");
        pnListSV.setBorder(titleLitsSV);
        pnListSV.setLayout(new GridLayout(1, 1));
        table = new JTable();
        jspDSDiem = new JScrollPane(table);
        pnListSV.add(jspDSDiem);
        
        
        pnInput.setBounds(10, 20, 765, 150);
        pnListSV.setBounds(10, 180, 765, 400);
        pnScore.add(pnInput);
        pnScore.add(pnListSV);
        
        //Add function
        btnSelect.addActionListener(this);
        return pnScore;
    }
    
    public void getDiemCaNhan(String mssv, String sub) {
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
        if(!sub.isEmpty()){
            listScores = DiemDAO.layDanhSachDiemTheoMSSVMonHoc(mssv,sub);
        }else{
            listScores = DiemDAO.layDanhSachDiemTheoMSSV(mssv);
        }
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

    @Override
    public void actionPerformed(ActionEvent e) {
        //getDiemCaNhan(txtMSSV.getText());
        if(e.getSource() == btnSelect){
            getDiemCaNhan(txtMSSV.getText(), txtSubject.getText());
        }
    }
}
