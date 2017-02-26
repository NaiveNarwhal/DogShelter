import java.sql.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DogsArrival extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Connection connection;
	private JTable table;
	private JTextField dogId;
	private JTextField date;
	private JTextField reason;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DogsArrival frame = new DogsArrival();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void displayData(){
		try {
			String query = "SELECT * FROM dog_arrival";
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
	public DogsArrival() {
		connection = MysqlConnection.databaseConnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 477);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				MainView mainview = new MainView();
				mainview.setVisible(true);	
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBackground(new Color(238, 232, 170));
		button.setBounds(687, 15, 65, 23);
		contentPane.add(button);
		
		JLabel lblDogArrival = new JLabel("DOGS ARRIVAL");
		lblDogArrival.setHorizontalAlignment(SwingConstants.CENTER);
		lblDogArrival.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 25));
		lblDogArrival.setBounds(269, 15, 235, 32);
		contentPane.add(lblDogArrival);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(242, 71, 487, 293);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					String dogid = (table.getModel().getValueAt(table.getSelectedRow(), 0)).toString();
					String query = "SELECT * FROM dog_arrival where dog_id = ?";
					PreparedStatement statement = connection.prepareStatement(query);
					statement.setString(1, dogid);
					ResultSet resultset = statement.executeQuery();
					
					while(resultset.next()){
						dogId.setText(resultset.getString("dog_id"));
						date.setText(resultset.getString("date_of_arrival"));
						reason.setText(resultset.getString("reason"));
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
					String query = "INSERT INTO dog_arrival(dog_id, date_of_arrival, reason) VALUES (?,?,?)";
					PreparedStatement statement = connection.prepareStatement(query);
					statement.setString(1, dogId.getText());
					statement.setString(2, date.getText());
					statement.setString(3, reason.getText());
					statement.execute();
					
					dogId.setText("");
					date.setText("");
					reason.setText("");		
					displayData();
					statement.close();

				} catch (Exception error) {
					error.printStackTrace();
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_1.setBackground(new Color(238, 232, 170));
		button_1.setBounds(74, 235, 89, 23);
		contentPane.add(button_1);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "Update Dog_arrival SET date_of_arrival  = '" + date.getText() + "', reason  = '" + reason.getText() + "' where dog_id = '"+ dogId.getText() + "'  ";
					PreparedStatement statement = connection.prepareStatement(query);
					statement.execute();
					dogId.setText("");
					date.setText("");
					reason.setText("");
					
					displayData();
					statement.close();

	
				} catch (Exception error) {
					error.printStackTrace();
				}
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEdit.setBackground(new Color(238, 232, 170));
		btnEdit.setBounds(74, 269, 89, 23);
		contentPane.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "DELETE FROM dog_arrival where dog_id = '"+ dogId.getText() + "'  ";
					PreparedStatement statement = connection.prepareStatement(query);
					statement.execute();
					dogId.setText("");
					date.setText("");
					reason.setText("");
					
					displayData();
					statement.close();
				
	
				} catch (Exception error) {
					error.printStackTrace();
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDelete.setBackground(new Color(238, 232, 170));
		btnDelete.setBounds(74, 303, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnDogsArrivalInfo = new JButton("Dogs arrival info");
		btnDogsArrivalInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayData();
			}
		});
		btnDogsArrivalInfo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDogsArrivalInfo.setBackground(new Color(238, 232, 170));
		btnDogsArrivalInfo.setBounds(340, 374, 133, 23);
		contentPane.add(btnDogsArrivalInfo);
		
		JButton btnMoreDetails = new JButton("More details");
		btnMoreDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "SELECT dogs.name, dogs.breed, dog_arrival.date_of_arrival, dog_arrival.reason  FROM dogs INNER JOIN dog_arrival ON dogs.dog_id=dog_arrival.dog_id;";
					PreparedStatement statement = connection.prepareStatement(query);
					ResultSet resultset = statement.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(resultset));
					
					statement.close();
					resultset.close();
				} catch (Exception error) {
					error.printStackTrace();
				
				}
			}
		});
		btnMoreDetails.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMoreDetails.setBackground(new Color(238, 232, 170));
		btnMoreDetails.setBounds(483, 374, 107, 23);
		contentPane.add(btnMoreDetails);
		
		dogId = new JTextField();
		dogId.setColumns(10);
		dogId.setBounds(93, 91, 107, 20);
		contentPane.add(dogId);
		
		date = new JTextField();
		date.setColumns(10);
		date.setBounds(93, 122, 107, 20);
		contentPane.add(date);
		
		reason = new JTextField();
		reason.setColumns(10);
		reason.setBounds(93, 153, 107, 20);
		contentPane.add(reason);
		
		JLabel label = new JLabel("Dog ID");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(20, 94, 72, 14);
		contentPane.add(label);
		
		JLabel lblDateOfArrival = new JLabel("Date");
		lblDateOfArrival.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDateOfArrival.setBounds(20, 125, 89, 14);
		contentPane.add(lblDateOfArrival);
		
		JLabel lblReason = new JLabel("Reason");
		lblReason.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblReason.setBounds(20, 156, 72, 14);
		contentPane.add(lblReason);
	}
}
