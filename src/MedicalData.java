import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.sql.*;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MedicalData extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Connection connection;
	private JTable table;
	private JTextField medId;
	private JTextField diseases;
	private JTextField sterilization;
	private JTextField vet;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MedicalData frame = new MedicalData();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void displayData(){
		try {
			String query = "SELECT * FROM medical_story";
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
	public MedicalData() {
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
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainView mainview = new MainView();
				mainview.setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBackground(new Color(238, 232, 170));
		button.setBounds(713, 11, 65, 23);
		contentPane.add(button);
		
		JLabel lblMedicalData = new JLabel("MEDICAL DATA");
		lblMedicalData.setHorizontalAlignment(SwingConstants.CENTER);
		lblMedicalData.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 25));
		lblMedicalData.setBounds(261, 15, 235, 32);
		contentPane.add(lblMedicalData);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(261, 69, 472, 306);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String medid = (table.getModel().getValueAt(table.getSelectedRow(), 3)).toString();
					String query = "SELECT * FROM medical_story where med_id = ?";
					PreparedStatement statement = connection.prepareStatement(query);
					statement.setString(1, medid);
					ResultSet resultset = statement.executeQuery();
					
					while(resultset.next()){
						medId.setText(resultset.getString("med_id"));
						diseases.setText(resultset.getString("diseases"));
						sterilization.setText(resultset.getString("sterilization"));
						vet.setText(resultset.getString("veterinarian"));
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
					String query = "INSERT INTO medical_story(med_id, diseases, sterilization, veterinarian) VALUES (?,?,?,?)";
					PreparedStatement statement = connection.prepareStatement(query);
					statement.setString(1, medId.getText());
					statement.setString(2, diseases.getText());
					statement.setString(3, sterilization.getText());
					statement.setString(4, vet.getText());
					statement.execute();
					
					medId.setText("");
					diseases.setText("");
					sterilization.setText("");
					vet.setText("");		
					displayData();
					statement.close();

				} catch (Exception error) {
					error.printStackTrace();
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_1.setBackground(new Color(238, 232, 170));
		button_1.setBounds(73, 232, 89, 23);
		contentPane.add(button_1);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "Update medical_story SET diseases  = '" + diseases.getText() + "', sterilization  = '" + sterilization.getText() + "', veterinarian  = '" + vet.getText() + "' where med_id = '"+ medId.getText() + "'  ";
					PreparedStatement statement = connection.prepareStatement(query);
					statement.execute();
					medId.setText("");
					diseases.setText("");
					sterilization.setText("");
					vet.setText("");
					
					displayData();
					statement.close();

	
				} catch (Exception error) {
					error.printStackTrace();
				}
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEdit.setBackground(new Color(238, 232, 170));
		btnEdit.setBounds(73, 266, 89, 23);
		contentPane.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "DELETE FROM DOGS where med_id = '"+ medId.getText() + "'  ";
					PreparedStatement statement = connection.prepareStatement(query);
					statement.execute();
					medId.setText("");
					diseases.setText("");
					sterilization.setText("");
					vet.setText("");
					
					displayData();
					statement.close();
				
				} catch (Exception error) {
					error.printStackTrace();
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDelete.setBackground(new Color(238, 232, 170));
		btnDelete.setBounds(73, 300, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnMedicalData = new JButton("Medical Data ");
		btnMedicalData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayData();
			}
		});
		btnMedicalData.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnMedicalData.setBackground(new Color(238, 232, 170));
		btnMedicalData.setBounds(361, 387, 109, 23);
		contentPane.add(btnMedicalData);
		
		JButton btnMoreDetails = new JButton("More details");
		btnMoreDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "SELECT dogs.name, medical_story.diseases, medical_story.sterilization, medical_story.veterinarian  FROM dogs INNER JOIN medical_story ON dogs.dog_id=medical_story.med_id;";
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
		btnMoreDetails.setBounds(477, 387, 109, 23);
		contentPane.add(btnMoreDetails);
		
		medId = new JTextField();
		medId.setColumns(10);
		medId.setBounds(105, 70, 107, 20);
		contentPane.add(medId);
		
		diseases = new JTextField();
		diseases.setColumns(10);
		diseases.setBounds(105, 101, 107, 20);
		contentPane.add(diseases);
		
		sterilization = new JTextField();
		sterilization.setColumns(10);
		sterilization.setBounds(105, 131, 107, 20);
		contentPane.add(sterilization);
		
		vet = new JTextField();
		vet.setColumns(10);
		vet.setBounds(105, 162, 107, 20);
		contentPane.add(vet);
		
		JLabel lblMedicalId = new JLabel("Veterinarian");
		lblMedicalId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMedicalId.setBounds(22, 165, 72, 14);
		contentPane.add(lblMedicalId);
		
		JLabel lblSterilization = new JLabel("Sterilization");
		lblSterilization.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSterilization.setBounds(22, 134, 72, 14);
		contentPane.add(lblSterilization);
		
		JLabel lblDiseases = new JLabel("Diseases");
		lblDiseases.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDiseases.setBounds(22, 104, 72, 14);
		contentPane.add(lblDiseases);
		
		JLabel lblMedicalId_1 = new JLabel("Medical ID");
		lblMedicalId_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMedicalId_1.setBounds(22, 73, 72, 14);
		contentPane.add(lblMedicalId_1);
	}
}
