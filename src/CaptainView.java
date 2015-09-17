import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;

import javax.swing.JDialog;

import java.awt.Dimension;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JTable;

import java.awt.GridBagConstraints;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JScrollPane;


public class CaptainView extends JDialog {

	private JPanel jPanel_CaptainView = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
    private String cid = null;
    
	public CaptainView(String cid) {
		this.cid=cid;
		initialize();
	}


	private void initialize() {
		this.setSize(new Dimension(586, 299));
		this.setTitle("CAPTAIN VIEW");

		// Retrieve Captain data from mysql
		retrieveCaptainData();
		this.setLocationRelativeTo(null);  
		setVisible(true);
	}


	private void retrieveCaptainData() {
		Connection connect = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			// Load the MySQL driver
			Class.forName("com.mysql.jdbc.Driver");

			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost:3307/Airport?user=root");

			// Statements allow to issue SQL queries to the database
			PreparedStatement ps = connect
					.prepareStatement("select * from vcaptain where cid=?");
			ps.setString(1, cid);

			resultSet = ps.executeQuery();

			displayResultSet(resultSet);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}

				if (statement != null) {
					statement.close();
				}

				if (connect != null) {
					connect.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	private void displayResultSet(ResultSet resultSet) throws SQLException {
		// Now get some metadata from the database
		// Result set get the result of the SQL query

			
		resultSet.last();
		
		int numrows = resultSet.getRow();

		resultSet.first();
		
		Object[][] data = new Object[numrows][3];
		
		String[] colnames = {"cid", "pid", "fid"};
		
		
		//while (resultSet.next())
		//{
		for (int i=0; i < numrows; i++)
		{
			data[i][0] = resultSet.getString("cid");
			data[i][1] = resultSet.getString("pid");
			data[i][2] = resultSet.getString("fID");
			
			//System.out.println("data=" + data[i][0] + " " + data[i][0] + " " + data[i][1] + " " + data[i][2] + " " + data[i][3] + data[i][4]);
		}
		JScrollPane jsp = new JScrollPane();
		JTable table = new JTable(data, colnames);
		this.setContentPane(jsp);
		jsp.setViewportView(table);
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
