import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import java.awt.Dimension;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;


public class Airport extends JFrame {

	private JPanel jPanel = null;
	private JLabel jLabel_FlightNo = null;
	private JTextField jTextField_FlightNo = null;
	private JButton jButton_Login = null;
    private ActionHandler actionListener = new ActionHandler();  //  @jve:decl-index=0:
	private JButton jButton_Submit = null;
	private JButton jButton_Exit = null;

    private class ActionHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == Airport.this.getJButton_Login())
                showLogin();
            else  if (e.getSource() == Airport.this.getJButton_Submit())
                showFlightTable();
            else  if (e.getSource() == Airport.this.getJButton_Exit())
                exit();
        }
    }

	public Airport() throws HeadlessException {
		// TODO Auto-generated constructor stub
	
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setSize(new Dimension(600, 400));
        this.setContentPane(getJPanel());
        this.setTitle("Airport");
		
        getJButton_Login().addActionListener(actionListener);
        getJButton_Submit().addActionListener(actionListener);
        getJButton_Exit().addActionListener(actionListener);

        this.setLocationRelativeTo(null);  
        
        this.setVisible(true);
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 2;
			gridBagConstraints5.fill = GridBagConstraints.BOTH;
			gridBagConstraints5.gridy = 3;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 2;
			gridBagConstraints4.fill = GridBagConstraints.BOTH;
			gridBagConstraints4.gridy = 2;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 2;
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.gridy = 2;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 2;
			jLabel_FlightNo = new JLabel();
			jLabel_FlightNo.setText("Enter Flight No:");
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(jLabel_FlightNo, gridBagConstraints);
			jPanel.add(getJTextField_FlightNo(), gridBagConstraints1);
			jPanel.add(getJButton_Login(), gridBagConstraints2);
			jPanel.add(getJButton_Submit(), gridBagConstraints4);
			jPanel.add(getJButton_Exit(), gridBagConstraints5);
		}
		return jPanel;
	}

	/**
	 * This method initializes jTextField_FlightNo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField_FlightNo() {
		if (jTextField_FlightNo == null) {
			jTextField_FlightNo = new JTextField();
		}
		return jTextField_FlightNo;
	}

	/**
	 * This method initializes jButton_Login	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton_Login() {
		if (jButton_Login == null) {
			jButton_Login = new JButton();
			jButton_Login.setText("Login");
		}
		return jButton_Login;
	}

	/**
	 * This method initializes jButton_Submit	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton_Submit() {
		if (jButton_Submit == null) {
			jButton_Submit = new JButton();
			jButton_Submit.setText("Submit");
		}
		return jButton_Submit;
	}

	/**
	 * This method initializes jButton_Exit	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton_Exit() {
		if (jButton_Exit == null) {
			jButton_Exit = new JButton();
			jButton_Exit.setText("Exit");
		}
		return jButton_Exit;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Airport();
	}

	private void showLogin()
	{
		new AirportLogin();
		
		
	}
	
	private void showFlightTable()
	{
			String id = getJTextField_FlightNo().getText();
			if (id.length() > 5) {
				JOptionPane.showMessageDialog(this, "flight number can't be more than 5 characters");
			    return;
			}
		new FlightTable( getJTextField_FlightNo().getText() );
	}
	
	private void submit() {
		// TODO Auto-generated method stub
		
	}

	private void exit()
	{
        System.exit(0);
	}
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
