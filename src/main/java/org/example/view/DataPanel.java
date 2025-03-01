package org.example.view;

import org.example.view.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DataPanel extends JTable {

    View view;

    public DataPanel(View view){
        this.view = view;

        String[] columnNamesData = {"Dato", "Valor"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNamesData, 0);
        JScrollPane scrollPane = new JScrollPane(this);
        tableModel.addRow(new Object[]{"Total Resources", 0});
        tableModel.addRow(new Object[]{"Total Consumers", 0});
        tableModel.addRow(new Object[]{"Total Producers", 0});
        tableModel.addRow(new Object[]{"Active Threads", 0});
        tableModel.addRow(new Object[]{"Total Time (ms)", 0});

        this.setModel(tableModel);

        // Customizacion
        this.getTableHeader().setBackground(new Color(248, 110, 240));
        this.setRowHeight(30);
        this.setMinimumSize(new Dimension(300,200));
    }
}
