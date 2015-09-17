import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;

import javax.swing.JDialog;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class AirportLogin extends JDialog {

	private JTextField jTextField = null;
	private JPanel jPanel_Login = null;
	private JLabel jLabel_ID = null;
	private JButton jButton_Login = null;
	
	private ActionHandler actionListener = new ActionHandler();  //  @jve:decl-index=0:
    
    	private class ActionHandler implements ActionListener {

    	        public void actionPerformed(ActionEvent e) {
    	            if (e.getSource() == AirportLogin.this.getJButton_Login())
    	                submit();
    	        }
    	    }

	
	
	public AirportLogin() {
		// TODO Auto-generated constructor stub
	
		initialize();
	}


	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setSize(new Dimension(347, 218));
        this.setContentPane(getJPanel_Login());
        this.setTitle("Airport Login");
        
        getJButton_Login().addActionListener(actionListener);
        
        this.setLocationRelativeTo(null);  
        this.setVisible(true);
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
		}
		return jTextField;
	}

	/**
	 * This method initializes jPanel_Login	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel_Login() {
		if (jPanel_Login == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 2;
			gridBagConstraints2.gridy = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.weightx = 1.0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			jLabel_ID = new JLabel();
			jLabel_ID.setText("ID:");
			jPanel_Login = new JPanel();
			jPanel_Login.setLayout(new GridBagLayout());
			jPanel_Login.add(jLabel_ID, gridBagConstraints);
			jPanel_Login.add(getJTextField(), gridBagConstraints1);
			jPanel_Login.add(getJButton_Login(), gridBagConstraints2);
		}
		return jPanel_Login;
	}

	/**
	 * This method initializes jButton_Login	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton_Login() {
		if (jButton_Login == null) {
			jButton_Login = new JButton();
			jButton_Login.setText("Submit");
		}
		return jButton_Login;
	}
	
	private void submit()
	{
		String id = getJTextField().getText();
		if (id.length() > 5) {
			JOptionPane.showMessageDialog(this, "id number can't be more than 5 characters");
		    return;
		}
		
		if (id.charAt(0) == 'C')
			new CaptainView(id);
		else 
			new StaffMemberView(id);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
