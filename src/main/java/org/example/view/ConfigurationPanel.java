package org.example.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ConfigurationPanel extends JTable {
    public ConfigurationPanel(){

        String[] columnNamesData = {"Parámetro", "Valor"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNamesData, 0);
        JScrollPane scrollPane = new JScrollPane(this);
        tableModel.addRow(new Object[]{"Total ResourceType", 1});
        tableModel.addRow(new Object[]{"Max Quantity", 300});
        tableModel.addRow(new Object[]{"Min Quantity", 0});
        tableModel.addRow(new Object[]{"Nº of Producers", 10});
        tableModel.addRow(new Object[]{"Nº of Consumers", 10});
        tableModel.addRow(new Object[]{"Start Delay Max (ms)", 100});
        tableModel.addRow(new Object[]{"Start Delay Min (ms)", 2});
        tableModel.addRow(new Object[]{"Produce Max Time (ms)", 500});
        tableModel.addRow(new Object[]{"Produce Min Time (ms)", 10});
        tableModel.addRow(new Object[]{"Consume Max Time (ms)", 500});
        tableModel.addRow(new Object[]{"Consume Min Time (ms)", 10});
        tableModel.addRow(new Object[]{"Life Cycle enabled", "false"});
        tableModel.addRow(new Object[]{"Cycles", 1});
        tableModel.addRow(new Object[]{"Guarded Region", "false"});
        tableModel.addRow(new Object[]{"Protect negative stock", "false"});




        this.setModel(tableModel);
        // Customizacion
        this.getColumnModel().getColumn(0).setPreferredWidth(200);
        this.getTableHeader().setBackground(new Color(202, 248, 110));
        this.setRowHeight(30);
        this.setMinimumSize(new Dimension(300,200));
    }
}
