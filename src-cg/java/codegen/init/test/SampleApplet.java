package codegen.init.test;

import java.awt.*;
import javax.swing.*;

public class SampleApplet extends JApplet {
    
    //============================================================ main
    public static void main(String[] args) {
        //... Create an initialize the applet.
        JApplet theApplet = new SampleApplet();
        //theApplet.init();         // Needed if overridden in applet
        //theApplet.start();        // Needed if overridden in applet
        
        //... Create a window (JFrame) and make applet the content pane.
        JFrame window = new JFrame("Sample Applet and Application");
        window.setContentPane(theApplet);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();              // Arrange the components.
        //System.out.println(theApplet.getSize());
        window.setVisible(true);    // Make the window visible.
    }
    
    //=============================================== Applet constructor
    public SampleApplet() {
        add(new JLabel("This is both an Applet and Application!"));
    }
}
