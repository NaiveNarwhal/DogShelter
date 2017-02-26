import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;
import java.sql.*;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JComboBox;

public class Dog extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField name;
	private JTextField breed;
	private JTextField gender;
	private JTextField birthDate;
	private JTextField size;
	private Connection connection;
	private JTextField medicalId;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dog frame = new Dog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void displayData(){
		try {
			String query = "SELECT * FROM Dogs";
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
	public Dog() {
		connection = MysqlConnection.databaseConnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 477);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("DOGS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 30));
		lblNewLabel.setBounds(283, 22, 184, 36);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(238, 99, 516, 257);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					String dogid = (table.getModel().getValueAt(table.getSelectedRow(), 0)).toString();
					String query = "SELECT * FROM Dogs where dog_id = ?";
					PreparedStatement statement = connection.prepareStatement(query);
					statement.setString(1, dogid);
					ResultSet resultset = statement.executeQuery();
					
					while(resultset.next()){
						gender.setText(resultset.getString("gender"));
						name.setText(resultset.getString("name"));
						birthDate.setText(resultset.getString("date_birth"));
						breed.setText(resultset.getString("breed"));
						size.setText(resultset.getString("size"));
						medicalId.setText(resultset.getString("medical_id"));
						textField.setText(resultset.getString("dog_id"));	
						
					}
					
					statement.close();
					resultset.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		table.setBackground(new Color(255, 255, 240));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBackground(new Color(238, 232, 170));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainView mainview = new MainView();
				mainview.setVisible(true);
			}
		});
		btnNewButton.setBounds(687, 11, 67, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(20, 77, 55, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblBreed = new JLabel("Breed");
		lblBreed.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBreed.setBounds(20, 102, 55, 14);
		contentPane.add(lblBreed);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblGender.setBounds(20, 127, 55, 14);
		contentPane.add(lblGender);
		
		JLabel lblBirthDate = new JLabel("Birth date");
		lblBirthDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBirthDate.setBounds(20, 153, 67, 14);
		contentPane.add(lblBirthDate);
		
		JLabel lblSize = new JLabel("Size");
		lblSize.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSize.setBounds(20, 178, 55, 14);
		contentPane.add(lblSize);
		
		JLabel lblMedicalid = new JLabel("Medical ID");
		lblMedicalid.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMedicalid.setBounds(20, 211, 67, 14);
		contentPane.add(lblMedicalid);
		
		medicalId = new JTextField();
		medicalId.setColumns(10);
		medicalId.setBounds(92, 208, 107, 20);
		contentPane.add(medicalId);
		
		name = new JTextField();
		name.setBounds(92, 74, 107, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		breed = new JTextField();
		breed.setColumns(10);
		breed.setBounds(92, 99, 107, 20);
		contentPane.add(breed);
		
		gender = new JTextField();
		gender.setColumns(10);
		gender.setBounds(92, 124, 107, 20);
		contentPane.add(gender);
		
		birthDate = new JTextField();
		birthDate.setColumns(10);
		birthDate.setBounds(92, 149, 107, 20);
		contentPane.add(birthDate);
		
		size = new JTextField();
		size.setColumns(10);
		size.setBounds(92, 177, 107, 20);
		contentPane.add(size);
		
		JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "INSERT INTO Dogs(gender,name, date_birth, breed, size, medical_id) VALUES (?,?,?,?,?,?)";
					PreparedStatement statement = connection.prepareStatement(query);
					statement.setString(1, gender.getText());
					statement.setString(2, name.getText());
					statement.setString(3, birthDate.getText());
					statement.setString(4, breed.getText());
					statement.setString(5, size.getText());
					statement.setString(6, medicalId.getText());
					statement.execute();
					textField.setText("");
					gender.setText("");
					name.setText("");
					birthDate.setText("");
					breed.setText("");
					size.setText("");
					medicalId.setText("");
					displayData();
					statement.close();

	
				} catch (Exception error) {
					error.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBackground(new Color(238, 232, 170));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(52, 287, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "DELETE FROM DOGS where dog_id = '"+ textField.getText() + "'  ";
					PreparedStatement statement = connection.prepareStatement(query);
					statement.execute();
					textField.setText("");
					gender.setText("");
					name.setText("");
					birthDate.setText("");
					breed.setText("");
					size.setText("");
					medicalId.setText("");
					displayData();
					statement.close();
				
	
				} catch (Exception error) {
					error.printStackTrace();
				}
			}
		});
		btnDelete.setBackground(new Color(238, 232, 170));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDelete.setBounds(52, 321, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "Update Dogs SET gender = '" + gender.getText() + "',name = '" + name.getText() + "', date_birth = '" + birthDate.getText() + "', breed = '" + breed.getText() + "', size = '"+ size.getText() + "', medical_id = '"+ medicalId.getText() + "' where dog_id = '"+ textField.getText() + "'  ";
					PreparedStatement statement = connection.prepareStatement(query);
					statement.execute();
					textField.setText("");
					gender.setText("");
					name.setText("");
					birthDate.setText("");
					breed.setText("");
					size.setText("");
					medicalId.setText("");
	
					displayData();
					statement.close();
				
	
				} catch (Exception error) {
					error.printStackTrace();
				}
			}
		});
		
		btnEdit.setBackground(new Color(238, 232, 170));
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEdit.setBounds(52, 355, 89, 23);
		contentPane.add(btnEdit);
		
		JButton btnNewButton_2 = new JButton("Dogs location info");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "SELECT dogs.name, dogs_location.cage_number  FROM dogs LEFT JOIN dogs_location ON dogs.dog_id=dogs_location.dog_id;";
					PreparedStatement statement = connection.prepareStatement(query);
					ResultSet resultset = statement.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(resultset));
					textField.setText("");
					gender.setText("");
					name.setText("");
					birthDate.setText("");
					breed.setText("");
					size.setText("");
					medicalId.setText("");
					
					statement.close();
					resultset.close();
				} catch (Exception error) {
					error.printStackTrace();
				
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBackground(new Color(238, 232, 170));
		btnNewButton_2.setBounds(458, 378, 144, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel dogId = new JLabel("Dog ID");
		dogId.setFont(new Font("Tahoma", Font.BOLD, 11));
		dogId.setBounds(20, 242, 55, 14);
		contentPane.add(dogId);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(92, 239, 107, 20);
		contentPane.add(textField);
		
		JButton btnDogsInfo = new JButton("Dogs info");
		btnDogsInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayData();
				textField.setText("");
				gender.setText("");
				name.setText("");
				birthDate.setText("");
				breed.setText("");
				size.setText("");
				medicalId.setText("");
			}
		});
		btnDogsInfo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDogsInfo.setBackground(new Color(238, 232, 170));
		btnDogsInfo.setBounds(359, 378, 89, 23);
		contentPane.add(btnDogsInfo);
		
		JLabel image = new JLabel("");
		image.setBounds(177, 381, 67, 57);
		image.setIcon(new ImageIcon(getClass().getResource("dog.png")));	
		contentPane.add(image);
		
		JLabel label = new JLabel("");
		label.setBounds(701, 393, 46, 36);
		label.setIcon(new ImageIcon(getClass().getResource("doggy.png")));
		contentPane.add(label);
		
	
	}
}
