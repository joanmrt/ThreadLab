package org.example.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ConsumerPanel extends JTable {
    public ConsumerPanel(){
        String[] columnNamesData = {"Consumer ID", "Bound Resource", "Status", "Start Delay", "Consume Delay", "Times consumed", "Life Cycles", "Start Time", "End Time"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNamesData, 0);

        this.setModel(tableModel);

        //Customization
        this.getTableHeader().setBackground(new Color(133, 0, 0));
        this.getTableHeader().setForeground(Color.WHITE);
        this.setRowHeight(30);
        this.setMinimumSize(new Dimension(300,200));
        this.setDefaultRenderer(Object.class, new CustomTableRenderer());
        this.setGridColor(new Color(154, 66, 35));
        this.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
    }
}
