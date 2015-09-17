import javax.swing.JDialog;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.TableModel;

public class FlightTable extends JDialog {

	private String flightNo = null;
	public FlightTable(String flightNo) {
		super();
		this.flightNo = flightNo;
		initialize();
	}

	private void initialize() {
		this.setSize(new Dimension(586, 299));
		this.setTitle("Flight Table");

		// Retrieve flight data from mysql
		retrieveFlightData();
		this.setLocationRelativeTo(null);  
		setVisible(true);
	}


	private void retrieveFlightData() {
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
					.prepareStatement("select seat, terminal, flight_no, a_airport, d_airport from vpassenger where flight_no=?");
			ps.setString(1, flightNo);

			resultSet = ps.executeQuery();

			writeResultSet(resultSet);

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
	
	private void writeResultSet(ResultSet resultSet) throws SQLException {
		// Now get some metadata from the database
		// Result set get the result of the SQL query

		System.out.println("The columns in the table are: ");

		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
			System.out.println("Column " + i + " "	+ resultSet.getMetaData().getColumnName(i));
		}

				
		resultSet.last();
		
		int numrows = resultSet.getRow();

		System.out.println("FlightNo=" + flightNo);
		System.out.println("numrows=" + numrows);

		resultSet.first();
		
		Object[][] data = new Object[numrows][5];
		
		String[] colnames = {"seat", "terminal", "flight_no", "a_airport", "d_airport"};
		
		
		//while (resultSet.next())
		//{
		for (int i=0; i < numrows; i++)
		{
			data[i][0] = resultSet.getString("seat");
			data[i][1] = resultSet.getString("terminal");
			data[i][2] = resultSet.getInt("flight_no");
			data[i][3] = resultSet.getString("a_airport");
			data[i][4] = resultSet.getString("d_airport");
			
			System.out.println("data=" + data[i][0] + " " + data[i][0] + " " + data[i][1] + " " + data[i][2] + " " + data[i][3] + data[i][4]);
		}
		JScrollPane jsp = new JScrollPane();
		JTable table = new JTable(data, colnames);
		this.setContentPane(jsp);
		jsp.setViewportView(table);
	}
} 
