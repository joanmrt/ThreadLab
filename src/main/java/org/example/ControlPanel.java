package org.example;

import javax.swing.*;
import java.awt.*;


public class ControlPanel extends JPanel {
    private Play play;
    private Stop stop;

    public Play getPlay() {
        return play;
    }

    public void setPlay(Play play) {
        this.play = play;
    }

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }

    public ControlPanel(Play play, Stop stop) {
        this.play = play;
        this.stop = stop;
        this.play.setText("Ok");
        this.stop.setText("Cancel");


    }

    public ControlPanel(){
        this.play = new Play();
        play.setText("Play");
        this.stop = new Stop();
        stop.setText("Stop");
        this.play.setBackground(new Color(157, 210, 70));
        this.stop.setBackground(new Color(255, 56, 56));
    }
}
