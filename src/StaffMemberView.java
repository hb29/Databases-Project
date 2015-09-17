import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;

import javax.swing.JDialog;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridBagConstraints;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StaffMemberView extends JDialog {

	private JPanel jPanel_StaffMemberView = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;

	private String sid = null;
    
	public StaffMemberView(String sid) {
		this.sid=sid;
		initialize();
	}


	private void initialize() {
		this.setSize(new Dimension(586, 299));
		this.setTitle("Staff Member View");

		// Retrieve Captain data from mysql
		retrieveStaffMemberData();
		this.setLocationRelativeTo(null);  
		setVisible(true);
	}


	private void retrieveStaffMemberData() {
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
					.prepareStatement("select * from vstaffmem where paid=?");
			ps.setString(1, sid);

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
		
		Object[][] data = new Object[numrows][5];
		
		String[] colnames = {"smid", "flightNo", "Passenger Name", "VIP?", "Contact Info"};
		
		
		//while (resultSet.next())
		//{
		for (int i=0; i < numrows; i++)
		{
			data[i][0] = resultSet.getString("paid");
			data[i][1] = resultSet.getString("flight_no");
			data[i][2] = resultSet.getString("name");
			data[i][3] = resultSet.getString("notVIP");
			data[i][4] = resultSet.getString("contact_info");
			
			//System.out.println("data=" + data[i][0] + " " + data[i][0] + " " + data[i][1] + " " + data[i][2] + " " + data[i][3] + data[i][4]);
		}
		JScrollPane jsp = new JScrollPane();
		JTable table = new JTable(data, colnames);
		this.setContentPane(jsp);
		jsp.setViewportView(table);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
