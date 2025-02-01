package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProducerPanel extends JTable {
    public ProducerPanel(){
        String[] columnNamesData = {"Nombre", "Valor"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNamesData, 0);
        JScrollPane scrollPane = new JScrollPane(this);




        this.setModel(tableModel);

        this.getTableHeader().setBackground(new Color(248, 110, 110));
        this.setRowHeight(30);
        this.setMinimumSize(new Dimension(300,200));
    }
}
