package codegen.init.test;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.sun.org.apache.xerces.internal.impl.RevalidationHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;

public class AddComponentOnJFrameAtRuntime extends JFrame implements
		ActionListener {
	JPanel panel;
	JPanel filedPanel;
	private JComboBox Selector;
	private String[] ColorList;
	TextField nameField;
	JScrollPane fieldScroll;

	public AddComponentOnJFrameAtRuntime() {
		super("Create new component source code");
		//setLayout(new BorderLayout());
		setLayout(new GridLayout(0, 1));
		GridBagConstraints c = new GridBagConstraints();
		panel = new JPanel(new GridBagLayout());
		panel.setLayout(new GridBagLayout());
		filedPanel = new JPanel(new GridBagLayout());
		filedPanel.setLayout(new GridLayout(0, 1));
		JLabel componentName = new JLabel();
		componentName.setText("Component Name:");
		nameField = new TextField("Type component name", 35);
		panel.add(componentName);
		panel.add(nameField);
		JButton button = new JButton("Add Field");
		panel.add(button);
		c = new GridBagConstraints();
		add(panel, BorderLayout.CENTER);
		fieldScroll = new JScrollPane(filedPanel);
		add(fieldScroll);
		//add(filedPanel, BorderLayout.AFTER_LAST_LINE);
		JLabel fieldName = new JLabel();
		JLabel fieldType = new JLabel();
		fieldName.setText("Field Name:");
		fieldType.setText("Field Type:");
		nameField = new TextField("Type here Something", 35);
		ColorList = new String[9];
		// SansSerif = new Font("SansSerif", Font.BOLD, 14);

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
		filedPanel.add(fieldName);
		filedPanel.add(nameField);
		filedPanel.add(fieldType);
		filedPanel.add(Selector);
		
		button.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		filedPanel.setSize(300, 500);
		filedPanel.setVisible(true);
		fieldScroll.revalidate();
		setSize(500, 500);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent evt) {
		// panel.add(new JButton("Button"));
		GridBagConstraints c = new GridBagConstraints();

		JLabel fieldName = new JLabel();
		JLabel fieldType = new JLabel();
		fieldName.setText("Field Name:");
		fieldType.setText("Field Type:");
		nameField = new TextField("Type here Something", 35);
		

		Selector = new JComboBox(ColorList);
		filedPanel.add(fieldName);
		filedPanel.add(nameField);
		filedPanel.add(fieldType);
		filedPanel.add(Selector);
		fieldScroll.revalidate();
		fieldScroll.repaint();
		filedPanel.revalidate();
		filedPanel.repaint();
		repaint();

		validate();
	}

	public static void main(String[] args) {
		AddComponentOnJFrameAtRuntime acojfar = new AddComponentOnJFrameAtRuntime();
	}
}
