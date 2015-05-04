package codegen.init.test;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class TestAddGui {

	public static void main(String[] args) {

	    final JFrame frame = new JFrame("Test");
	    frame.setLayout(new GridLayout(0, 1));

	    frame.add(new JButton(new AbstractAction("Click to add") {
	        public void actionPerformed(ActionEvent e) {

	            SwingUtilities.invokeLater(new Runnable() {
	                @Override
	                public void run() {
	                    frame.add(new JLabel("Bla"));
	                    frame.validate();
	                    frame.repaint();
	                }
	            });
	        }
	    }));

	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(400, 300);
	    frame.setVisible(true);
	}

}
