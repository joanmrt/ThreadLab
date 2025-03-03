package org.example.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ResourcePanel extends JTable {
    public ResourcePanel(){
        String[] columnNamesData = {"Resource ID", "Quantity", "MinQ", "MaxQ", "Consumer num", "Producer num", "Underflow", "Overflow", "State"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNamesData, 0);
        this.setModel(tableModel);

        //Customization
        this.getTableHeader().setBackground(new Color(133, 0, 131));
        this.getTableHeader().setForeground(Color.WHITE);
        this.setRowHeight(30);
        this.setMinimumSize(new Dimension(300,200));
        this.setDefaultRenderer(Object.class, new CustomTableRenderer());
        this.setGridColor(new Color(154, 66, 35));
        this.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
    }
}
