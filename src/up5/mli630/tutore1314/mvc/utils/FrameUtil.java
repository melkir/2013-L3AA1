package up5.mli630.tutore1314.mvc.utils;

import javax.swing.*;

public class FrameUtil {

    public static JFrame createFrame(String title, JPanel panel) {
        JFrame frame = new JFrame(title);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        return frame;
    }

}
