/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.pkg18hcb.pkg18424015.bt2;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author phanc
 */
public class MenuBar extends JFrame{
    JMenuBar jmenubar;
    JMenu jFile, jEdit;
    JMenuItem iNew, iOpen;
    public MenuBar(String tieuDe){
        super(tieuDe);
        
        jmenubar = new JMenuBar();
        jFile = new JMenu("File");
        jEdit = new JMenu("Edit");
        iNew = new JMenuItem("Thêm mới");
        iOpen = new JMenuItem("Mở thư mục");
        
        jFile.add(iNew);
        jEdit.add(iOpen);
        
        jmenubar.add(jFile);
        jmenubar.add(jEdit);
        
        this.setJMenuBar(jmenubar);
//        Container con = getContentPane();
//        con.add(BorderLayout.NORTH, jmenubar);
    }
}
