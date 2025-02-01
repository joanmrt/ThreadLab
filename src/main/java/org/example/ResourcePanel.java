package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ResourcePanel extends JTable {
    public ResourcePanel(){
        String[] columnNamesData = {"Nombre", "Valor"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNamesData, 0);
        JScrollPane scrollPane = new JScrollPane(this);
        //tableModel.addRow(new Object[]{"Quantity",controller.getModel().getResources().getQuantity()});
        //this.add(scrollPane);
        this.setModel(tableModel);
        this.setMinimumSize(new Dimension(300,200));
    }
}
