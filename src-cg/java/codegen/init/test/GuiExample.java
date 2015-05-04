package codegen.init.test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.applet.*;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import codegen.init.CodeGeneratorMain;

public class GuiExample extends Applet implements ActionListener {

	Button okButton;
	Button wrongButton;
	TextField nameField;
	CheckboxGroup radioGroup;
	Checkbox radio1;
	Checkbox radio2;
	Checkbox radio3;
	private JComboBox Selector;
	private String[] ColorList;
	private Font SansSerif;

	public void init() {
		// Now we will use the FlowLayout
		setLayout(new FlowLayout());
		okButton = new Button("Action!");
		wrongButton = new Button("Don't click!");
		nameField = new TextField("Type here Something", 35);
		radioGroup = new CheckboxGroup();
		radio1 = new Checkbox("Red", radioGroup, false);
		radio2 = new Checkbox("Blue", radioGroup, true);
		radio3 = new Checkbox("Green", radioGroup, false);
		ColorList = new String[9];
		//SansSerif = new Font("SansSerif", Font.BOLD, 14);

		ColorList[0] = "Red";
		ColorList[1] = "Magenta";
		ColorList[2] = "Blue";
		ColorList[3] = "Cyan";
		ColorList[4] = "Green";
		ColorList[5] = "Yellow";
		ColorList[6] = "White";
		ColorList[7] = "Gray";
		ColorList[8] = "Black";

		Selector = new JComboBox(ColorList);
		//Selector.addItemListener (this);
		add(Selector);
		add(okButton);
		add(wrongButton);
		add(nameField);
		add(radio1);
		add(radio2);
		add(radio3);
		
		DefaultTableModel model = new DefaultTableModel();
	    JTable table = new JTable(model);

	    model.addColumn("Col1");
	    model.addColumn("Col2");

	    // Create the first row
	    model.insertRow(0, new Object[] { "r1" });
	    

	    // Append a row
	    model.insertRow(model.getRowCount(), new Object[] { "r5" });
	   /* 
	    JFrame f = new JFrame();
	    f.setSize(300, 300);
	    f.add(new JScrollPane(table));
	    f.setVisible(true);
		*/
	    add(new JScrollPane(table));
		// Attach actions to the components
		okButton.addActionListener(this);
		wrongButton.addActionListener(this);

	}

	// Here we will show the results of our actions
	public void paint(Graphics g) {
		// If the radio1 box is selected then radio1.getState() will
		// return true and this will execute
		if (radio1.getState())
			g.setColor(Color.red);
		// If it was not red we'll try if it is blue
		else if (radio2.getState())
			g.setColor(Color.blue);
		// Since always one radiobutton must be selected it must be green
		else
			g.setColor(Color.green);

		// Now that the color is set you can get the text out the TextField
		// like this
		g.drawString(nameField.getText(), 20, 100);

	}

	// When the button is clicked this method will get automatically called
	// This is where you specify all actions.

	public void actionPerformed(ActionEvent evt) {
		// Here we will ask what component called this method
		if (evt.getSource() == okButton) {
			// So it was the okButton, then let's perform his actions
			// Let the applet perform Paint again.
			// That will cause the aplet to get the text out of the textField
			// again and show it.
			repaint();
			CodeGeneratorMain.main(null);
		}
		// Actions of the wrongButton
		else if (evt.getSource() == wrongButton) {

			// Change the text on the button for fun
			wrongButton.setLabel("Not here!");
			// Changes the text in the TextField
			nameField.setText("That was the wrong button!");
			// Lets the applet show that message.
			repaint();
		}
	}

}

// That gives you an idea of how to implement actions.
// Note that clicking the radiobuttons only shows it's effect when the
// applet is repainted. That is because no ActionListener is added
// to the radio buttons. You can do that in the same way as the buttons.

// Next example is about MouseListener, a way to let the user interact with
// mouse-clicks.

// Go to MouseClickExample.java