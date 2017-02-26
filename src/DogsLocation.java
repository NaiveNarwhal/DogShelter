import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DogsLocation extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Connection connection;
	private JTextField dogId;
	private JTextField cage;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DogsLocation frame = new DogsLocation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void displayData(){
		try {
			String query = "SELECT * FROM dogs_location";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultset = statement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultset));
			
			statement.close();
			resultset.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public DogsLocation() {
		connection = MysqlConnection.databaseConnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 477);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblDogsLocation = new JLabel("DOGS LOCATION");
		lblDogsLocation.setHorizontalAlignment(SwingConstants.CENTER);
		lblDogsLocation.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 25));
		lblDogsLocation.setBounds(252, 11, 235, 32);
		contentPane.add(lblDogsLocation);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainView mainview = new MainView();
				mainview.setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBackground(new Color(238, 232, 170));
		button.setBounds(660, 11, 65, 23);
		contentPane.add(button);
		
		dogId = new JTextField();
		dogId.setColumns(10);
		dogId.setBounds(120, 124, 107, 20);
		contentPane.add(dogId);
		
		cage = new JTextField();
		cage.setColumns(10);
		cage.setBounds(120, 155, 107, 20);
		contentPane.add(cage);
		
		JLabel lblDogId = new JLabel("Dog ID");
		lblDogId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDogId.setBounds(29, 127, 72, 14);
		contentPane.add(lblDogId);
		
		JLabel lblCageNumber = new JLabel("Cage number");
		lblCageNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCageNumber.setBounds(29, 158, 81, 14);
		contentPane.add(lblCageNumber);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(268, 87, 415, 259);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String dogid = (table.getModel().getValueAt(table.getSelectedRow(), 0)).toString();
					String query = "SELECT * FROM dogs_location where dog_id = ?";
					PreparedStatement statement = connection.prepareStatement(query);
					statement.setString(1, dogid);
					ResultSet resultset = statement.executeQuery();
					
					while(resultset.next()){
						dogId.setText(resultset.getString("dog_id"));
						cage.setText(resultset.getString("cage_number"));
					}
					
					statement.close();
					resultset.close();
				} catch (Exception error) {
					error.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JButton button_1 = new JButton("Add");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "INSERT INTO dogs_location(dog_id, cage_number) VALUES (?,?)";
					PreparedStatement statement = connection.prepareStatement(query);
					statement.setString(1, dogId.getText());
					statement.setString(2, cage.getText());
					statement.execute();
					
					dogId.setText("");
					cage.setText("");		
					displayData();
					statement.close();

				} catch (Exception error) {
					error.printStackTrace();
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_1.setBackground(new Color(238, 232, 170));
		button_1.setBounds(86, 210, 89, 23);
		contentPane.add(button_1);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "Update Dogs_location SET cage_number  = '" + cage.getText() + "' where dog_id = '"+ dogId.getText() + "'  ";
					PreparedStatement statement = connection.prepareStatement(query);
					statement.execute();
					dogId.setText("");
					cage.setText("");
					
					displayData();
					statement.close();

	
				} catch (Exception error) {
					error.printStackTrace();
				}
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEdit.setBackground(new Color(238, 232, 170));
		btnEdit.setBounds(86, 244, 89, 23);
		contentPane.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "DELETE FROM Dogs_location where dog_id = '"+ dogId.getText() + "'  ";
					PreparedStatement statement = connection.prepareStatement(query);
					statement.execute();
					dogId.setText("");
					cage.setText("");
					displayData();
					statement.close();
				
	
				} catch (Exception error) {
					error.printStackTrace();
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDelete.setBackground(new Color(238, 232, 170));
		btnDelete.setBounds(86, 278, 89, 23);
		contentPane.add(btnDelete);
	}

}
