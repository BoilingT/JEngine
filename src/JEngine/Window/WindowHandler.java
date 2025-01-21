package JEngine.Window;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public class WindowHandler extends JFrame {

    public WindowHandler(int width, int height, String title){
        setTitle(title);
        setPreferredSize(new Dimension(width, height));
        setSize(new Dimension(width, height));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        System.out.println("Window Initialized");
    }

    public void Show() {
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void Close(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        System.exit(0);
    }
}
