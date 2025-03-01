package org.example.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ConsumerPanel extends JTable {
    public ConsumerPanel(){
        String[] columnNamesData = {"Consumer ID", "Bound Resource", "Status", "Start Delay", "Consume Delay", "Times consumed", "Life Cycles", "Start Time", "End Time"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNamesData, 0);
        JScrollPane scrollPane = new JScrollPane(this);

        this.setModel(tableModel);

        this.getTableHeader().setBackground(new Color(110, 128, 248));
        this.setRowHeight(30);
        this.setMinimumSize(new Dimension(300,200));
    }
}
