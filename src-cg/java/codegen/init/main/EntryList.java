/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package codegen.init.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import codegen.init.CodeGeneratorMain;
import codegen.init.ComponentDetailCache;


/**
 *
 * @author ndunn
 */
public class EntryList extends JPanel implements ActionListener,FocusListener{
    private List<Entry> entries;
    
    public List<Entry> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}

	private String fileFolderLocation;
    public String getFileFolderLocation() {
		return fileFolderLocation;
	}

	public void setFileFolderLocation(String fileFolderLocation) {
		this.fileFolderLocation = fileFolderLocation;
	}

	JButton chooseFile;
	
	JFileChooser chooser;
	String choosertitle;
    // Replace with your database stuff
    private static final String[] comboBoxEntries = {FieldItemTypeConstants.ITEM_TYPE_STRING,FieldItemTypeConstants.ITEM_TYPE_INT,
    												FieldItemTypeConstants.ITEM_TYPE_LONG,FieldItemTypeConstants.ITEM_TYPE_FLOAT,
    												FieldItemTypeConstants.ITEM_TYPE_DOUBLE,FieldItemTypeConstants.ITEM_TYPE_DATE};
    JButton button;
    TextField nameField;
    TextField displayField;
    TextField pathField;
    TextField projectPathField;
    public void actionPerformed(ActionEvent evt) {
    	System.out.println("Action performed");
    	ComponentDetailCache componentDetailCache = new ComponentDetailCache();
    	componentDetailCache.setComponentName(nameField.getText());
    	componentDetailCache.setLocation(pathField.getText());
    	componentDetailCache.setProjectLocation(projectPathField.getText());
    	componentDetailCache.setFieldList(this);
    	CodeGeneratorMain codeGeneratorMain = new CodeGeneratorMain();
    	codeGeneratorMain.main(null);
    	JLabel status = new JLabel("Code Generated");
    	add(status);
    	//button.setEnabled(false);
    	System.out.println("component name::"+nameField.getText());
    	for (Entry e : entries) {
            System.out.println("fieldName:::"+e.getTextField().getText());
            System.out.println("fieldType:::"+e.getComboBox().getSelectedItem().toString());
        }
    	refresh();
    }

    public EntryList() {
        this.entries = new ArrayList<Entry>();
        JLabel componentName = new JLabel();
		componentName.setText("Component Name:");
		nameField = new TextField("Type component name", 15);
		nameField.addFocusListener(this);

		
		JPanel componentPanel = new JPanel(new GridBagLayout());
		componentPanel.add(componentName);
		componentPanel.add(nameField);
		componentPanel.add(new JLabel("Component Display Name"));
		componentPanel.add(new TextField("",25));
		add(componentPanel);
		JPanel buttonPanel = new JPanel();
		pathField = new TextField("", 35);
		pathField.disable();
		buttonPanel.add(pathField);
		chooseFile = new GenericRoundedButton( "Choose Location" );
		chooseFile.addActionListener ( new FileChooserAction(pathField) );
		buttonPanel.add(chooseFile);
		
		projectPathField = new TextField("", 35);
		projectPathField.disable();
		buttonPanel.add(projectPathField);
		chooseFile = new GenericRoundedButton( "Choose Project Location" );
		chooseFile.addActionListener ( new FileChooserAction(projectPathField) );
		buttonPanel.add(chooseFile);
		//add ( go );
		
		button = new GenericRoundedButton("Generate Code");
		button.setSize(80, 20);
		button.addActionListener(this);
		buttonPanel.add(button);
        //add(button);
		add(buttonPanel);
        Entry initial = new Entry(new JComboBox(comboBoxEntries), "", this);
        addItem(initial);
    }
    
    
    public void cloneEntry(Entry entry) {
        Object selected = entry.getComboBox().getSelectedItem();
        JComboBox copy = new JComboBox(comboBoxEntries);
        //copy.setSelectedItem(selected);
        Entry theClone = new Entry(copy, "", this);

        addItem(theClone);
    }

    private void addItem(Entry entry) {
        entries.add(entry);

        add(entry);
        refresh();
    }

    public void removeItem(Entry entry) {
        entries.remove(entry);
        remove(entry);
        refresh();
    }

    private void refresh() {
        revalidate();

        if (entries.size() == 1) {
            entries.get(0).enableMinus(false);
        }
        else {
            for (Entry e : entries) {
                e.enableMinus(true);
            }
        }
    }
    public void focusGained(FocusEvent e) {
    	TextField field = (TextField) e.getComponent();
    	if(field.getText().indexOf("Type ")>-1)
    	{
    		field.setText("");
    	}
        displayMessage("Focus gained "+field.getName(), e);
    }

    public void focusLost(FocusEvent e) {
    	TextField field = (TextField) e.getComponent();
    	if(field.getText().length()==0)
    	{
    		field.setText("Type component name");
    	}
        displayMessage("Focus lost", e);
    }

    void displayMessage(String prefix, FocusEvent e) {
    	System.out.println("prefix::"+prefix);
       /* display.append(prefix
                       + (e.isTemporary() ? " (temporary):" : ":")
                       +  e.getComponent().getClass().getName()
                       + "; Opposite component: " 
                       + (e.getOppositeComponent() != null ?
                          e.getOppositeComponent().getClass().getName() : "null")
                       + newline); */
    }
    
    
    public class FileChooserAction extends AbstractAction {
    	
    	private TextField filePathField;
        public FileChooserAction(TextField path) {
        	this.filePathField = path;
        }

        public void actionPerformed(ActionEvent e) {
        	int result;
    		
    		chooser = new JFileChooser ();
    		chooser.setCurrentDirectory ( new java.io.File ( "." ) );
    		chooser.setDialogTitle ( choosertitle );
    		chooser.setFileSelectionMode ( JFileChooser.FILES_AND_DIRECTORIES );
    		//
    		// disable the "All files" option.
    		//
    		chooser.setAcceptAllFileFilterUsed ( true );
    		//
    		if ( chooser.showOpenDialog ( chooser ) == JFileChooser.APPROVE_OPTION )
    		{
    			filePathField.setText(""+chooser.getSelectedFile ());
    			System.out.println ( "getCurrentDirectory(): " + chooser.getCurrentDirectory () );
    			System.out.println ( "getSelectedFile() : " + chooser.getSelectedFile () );
    		}
    		else
    		{
    			System.out.println ( "No Selection " );
    		}
        }
    }

}
