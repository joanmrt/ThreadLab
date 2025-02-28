package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ResourcePanel extends JTable {
    public ResourcePanel(){
        String[] columnNamesData = {"Resource ID", "Quantity", "MinQ", "MaxQ", "Consumer num", "Producer num", "Underflow", "Overflow", "State"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNamesData, 0);


        this.setModel(tableModel);

        this.getTableHeader().setBackground(new Color(248, 184, 110));
        this.setRowHeight(30);
        this.setMinimumSize(new Dimension(300,200));
    }
}
