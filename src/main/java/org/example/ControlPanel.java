package org.example;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

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
