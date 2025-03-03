package org.example.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomTableRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (!isSelected) { // Apply alternate colors only if not selected
                if (row % 2 == 0) {
                    c.setBackground(new Color(250, 215, 99));
                } else {
                    c.setBackground(new Color(236, 201, 95));
                }
            }

            return c;
        }
    }
