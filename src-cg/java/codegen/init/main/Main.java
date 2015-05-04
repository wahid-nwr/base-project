package codegen.init.main;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


/**
 *
 * @author ndunn
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JScrollPane fieldScroll;
        EntryList panel = new EntryList();        
        panel.setLayout(new GridLayout(0, 1));
        fieldScroll = new JScrollPane(panel);        
        frame.getContentPane().add(fieldScroll);
        
        frame.pack();
        frame.setSize(750, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
