import java.sql.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;


public class LogView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection connection = null;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogView frame = new LogView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogView() {
		connection = MysqlConnection.databaseConnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 467, 325);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Connect");
		btnNewButton.setBackground(new Color(238, 232, 170));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query = "SELECT * FROM Users WHERE username=? AND password=?";
					PreparedStatement statement = connection.prepareStatement(query);
					statement.setString(1, textField.getText());
					statement.setString(2, passwordField.getText());
					ResultSet resultset = statement.executeQuery();
					
				    if(resultset.next()){
				    	dispose();
				    	MainView mainview = new MainView();
				    	mainview.setVisible(true);	
				    }
				    else{
				    	JOptionPane.showMessageDialog(null, "Invalid username or password. Try again.");
				    	textField.setText("");
				    	passwordField.setText("");
				    	
				    }
					statement.close();
					resultset.close();
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		});
		
		JLabel logo = new JLabel("");
		logo.setBounds(156, 215, 24, 28);
		logo.setIcon(new ImageIcon(getClass().getResource("/paw-print-.png")));
		contentPane.add(logo);
		btnNewButton.setBounds(137, 206, 152, 46);
		contentPane.add(btnNewButton);

		
		textField = new JTextField();
		textField.setBounds(156, 101, 139, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(156, 152, 139, 23);
		contentPane.add(passwordField);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblUsername.setBounds(30, 100, 116, 28);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setBounds(30, 148, 116, 28);
		contentPane.add(lblPassword);
		
		JLabel lblDogshelterManagement = new JLabel("DogShelter Management");
		lblDogshelterManagement.setHorizontalAlignment(SwingConstants.CENTER);
		lblDogshelterManagement.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 17));
		lblDogshelterManagement.setBounds(41, 22, 380, 37);
		contentPane.add(lblDogshelterManagement);
		
		JLabel label2 = new JLabel("");
		label2.setIcon(new ImageIcon(getClass().getResource("/origami.png")));
		label2.setBounds(305, 108, 116, 144);
		contentPane.add(label2);
	
		
	}
}
