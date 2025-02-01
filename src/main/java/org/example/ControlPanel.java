package org.example;

import javax.swing.*;


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
        play.setText("Ok");
        this.stop = stop;
        stop.setText("Cancel");
    }

    public ControlPanel(){
        this.play = new Play();
        play.setText("Play");
        this.stop = new Stop();
        stop.setText("Stop");
    }
}
