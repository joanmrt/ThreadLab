package org.example.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DataPanel extends JTable {

    public DataPanel(){

        String[] columnNamesData = {"Data", "Value"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNamesData, 0);
        tableModel.addRow(new Object[]{"Total Resources", 0});
        tableModel.addRow(new Object[]{"Total Consumers", 0});
        tableModel.addRow(new Object[]{"Total Producers", 0});
        tableModel.addRow(new Object[]{"Active Threads", 0});
        tableModel.addRow(new Object[]{"Total Time (ms)", "00:00:00"});

        this.setModel(tableModel);

        // Customizacion
        this.getTableHeader().setBackground(new Color(0, 41, 133));
        this.getTableHeader().setForeground(Color.WHITE);
        this.setRowHeight(40);
        this.setMinimumSize(new Dimension(300,200));
        this.setDefaultRenderer(Object.class, new CustomTableRenderer());
        this.setGridColor(new Color(154, 66, 35));
        this.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
    }
}
