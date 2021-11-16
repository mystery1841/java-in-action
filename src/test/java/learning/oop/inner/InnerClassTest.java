package learning.oop.inner;

import javax.swing.*;

public class InnerClassTest {
    public static void main(String[] args) {
        var clock = new TalkingClock(1000, true);
        clock.start();
        // keep program running until the user selects "OK"
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }

}
