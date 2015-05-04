/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package codegen.init.main;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 *
 * @author ndunn
 */
public class Entry extends JPanel {
    private JComboBox comboBox;
    private JTextField textField;
    private JTextField displayField;
    private JButton plus;
    private JButton minus;
    private JLabel itemCombo;
    private JLabel itemName;
    private JLabel itemDisplayName;
    private EntryList parent;

    public Entry(JComboBox comboBox, String textFieldText, EntryList list) {
        this.comboBox = comboBox;
        this.parent = list;
        this.plus = new JButton(new AddEntryAction());
        this.minus = new JButton(new RemoveEntryAction());
        itemCombo = new JLabel("Field Type");
        itemName = new JLabel("Field Name");
        itemDisplayName = new JLabel("Field Display Name");
        
        this.textField = new JTextField(10);
        this.textField.setText(textFieldText);
        this.displayField = new JTextField(10);
        //this.displayField.setText(textFieldText);
        add(this.plus);
        add(this.minus);
        add(itemCombo);
        add(this.comboBox);
        add(itemName);
        add(this.textField);
        add(itemDisplayName);
        add(this.displayField);
    }

    public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

	public JComboBox getComboBox() {
        return comboBox;
    }

    public class AddEntryAction extends AbstractAction {

        public AddEntryAction() {
            super("+");
        }

        public void actionPerformed(ActionEvent e) {
            parent.cloneEntry(Entry.this);
        }

    }

    public class RemoveEntryAction extends AbstractAction {

        public RemoveEntryAction() {
            super("-");
        }

        public void actionPerformed(ActionEvent e) {
            parent.removeItem(Entry.this);
        }
    }

    public void enableAdd(boolean enabled) {
        this.plus.setEnabled(enabled);
    }
    public void enableMinus(boolean enabled) {
        this.minus.setEnabled(enabled);
    }
}
