import java.sql.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tasks extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Connection connection;
	private JTable table;
	private JTextField taskId;
	private JTextField type;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tasks frame = new Tasks();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void displayData(){
		try {
			String query = "SELECT * FROM Tasks";
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
	public Tasks() {
		connection = MysqlConnection.databaseConnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 477);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblTasks = new JLabel("TASKS");
		lblTasks.setHorizontalAlignment(SwingConstants.CENTER);
		lblTasks.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 25));
		lblTasks.setBounds(252, 11, 235, 32);
		contentPane.add(lblTasks);
		
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
		button.setBounds(696, 11, 65, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Add");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "INSERT INTO Tasks(type_of_task) VALUES (?)";
					PreparedStatement statement = connection.prepareStatement(query);
					statement.setString(1, type.getText());
					statement.execute();
					
					type.setText("");
					taskId.setText("");
					
					displayData();
					statement.close();

	
				} catch (Exception error) {
					error.printStackTrace();
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_1.setBackground(new Color(238, 232, 170));
		button_1.setBounds(81, 217, 89, 23);
		contentPane.add(button_1);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "Update Tasks SET type_of_task = '" + type.getText() + "' where task_id = '"+ taskId.getText() + "'  ";
					PreparedStatement statement = connection.prepareStatement(query);
					statement.execute();
					type.setText("");
					taskId.setText("");
					
					displayData();
					statement.close();

	
				} catch (Exception error) {
					error.printStackTrace();
				}
			}
			
		});
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEdit.setBackground(new Color(238, 232, 170));
		btnEdit.setBounds(81, 251, 89, 23);
		contentPane.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "DELETE FROM Tasks where task_id = '"+ taskId.getText() + "'  ";
					PreparedStatement statement = connection.prepareStatement(query);
					statement.execute();
					taskId.setText("");
			
					displayData();
					statement.close();
				
	
				} catch (Exception error) {
					error.printStackTrace();
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDelete.setBackground(new Color(238, 232, 170));
		btnDelete.setBounds(81, 285, 89, 23);
		contentPane.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(252, 54, 442, 284);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String taskid = (table.getModel().getValueAt(table.getSelectedRow(), 0)).toString();
					String query = "SELECT * FROM tasks where task_id = ?";
					PreparedStatement statement = connection.prepareStatement(query);
					statement.setString(1, taskid);
					ResultSet resultset = statement.executeQuery();
					
					while(resultset.next()){
						type.setText(resultset.getString("type_of_task"));
						taskId.setText(resultset.getString("task_id"));
					}
					
					statement.close();
					resultset.close();
				} catch (Exception error) {
					error.printStackTrace();
				}
			}

		});
		scrollPane.setViewportView(table);
		
		JLabel lblTaskId = new JLabel("Task ID");
		lblTaskId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTaskId.setBounds(25, 125, 72, 14);
		contentPane.add(lblTaskId);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblType.setBounds(25, 152, 72, 14);
		contentPane.add(lblType);
		
		taskId = new JTextField();
		taskId.setColumns(10);
		taskId.setBounds(93, 122, 107, 20);
		contentPane.add(taskId);
		
		type = new JTextField();
		type.setColumns(10);
		type.setBounds(93, 149, 107, 20);
		contentPane.add(type);
		
		JLabel label = new JLabel("");
		label.setBounds(25, 380, 65, 57);
		label.setIcon(new ImageIcon(getClass().getResource("/feeding.png")));	
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(146, 380, 68, 57);
		label_1.setIcon(new ImageIcon(getClass().getResource("/dog-having-a-bubbles-bath.png")));
		contentPane.add(label_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(278, 380, 72, 57);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/dog-shitting.png")));
		contentPane.add(lblNewLabel);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(415, 373, 72, 64);
		label_2.setIcon(new ImageIcon(getClass().getResource("/man-combing-a-dog.png")));
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(550, 387, 65, 50);
		label_3.setIcon(new ImageIcon(getClass().getResource("/dog-with-owner.png")));
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setBounds(679, 373, 73, 66);
		label_4.setIcon(new ImageIcon(getClass().getResource("/dog_man.png")));
		contentPane.add(label_4);
	}
}
