package org.example.view;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class ControlPanel extends JPanel {
    private Play play;
    private Stop stop;

    public ControlPanel(){
        this.play = new Play();
        this.stop = new Stop();
    }
}
