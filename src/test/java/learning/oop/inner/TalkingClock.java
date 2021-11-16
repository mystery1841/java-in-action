package learning.oop.inner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;

public class TalkingClock {
    private int interval;
    private boolean beep;

    public TalkingClock(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }

    public void start() {
        var listener = this.new TimePrinter();
        var timer = new Timer(interval, listener);
        timer.start();
    }

    private class TimePrinter implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            System.out.println("At the tone, the time is "
                    + Instant.ofEpochMilli(event.getWhen()));
            if (TalkingClock.this.beep) Toolkit.getDefaultToolkit().beep();
        }
    }


}
