package org.example.view;

import javax.swing.*;
import java.awt.*;

public class Play extends JButton {
    public Play(){
        this.setText("Play");
        this.setBackground(new Color(99, 147, 2));
        this.setForeground(Color.WHITE);
        this.setFont(new Font("Arial", Font.BOLD, 15));
    }
}