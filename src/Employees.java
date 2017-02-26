import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Employees extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField phone;
	private JTextField adress;
	private JTextField salary;
	private JTextField taskId;
	private Connection connection = null;
	private JTextField employeeId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employees frame = new Employees();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void displayData(){
		try {
			String query = "SELECT * FROM Employees";
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
	
	public Employees() {
		connection = MysqlConnection.databaseConnect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 477);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblEmployees = new JLabel("EMPLOYEES");
		lblEmployees.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmployees.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 25));
		lblEmployees.setBounds(248, 11, 235, 32);
		contentPane.add(lblEmployees);
		
		JButton button = new JButton("Back");
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBackground(new Color(238, 232, 170));
		button.setBounds(700, 11, 65, 23);
		contentPane.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainView mainview = new MainView();
				mainview.setVisible(true);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(248, 54, 517, 329);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					String employeeid = (table.getModel().getValueAt(table.getSelectedRow(), 0)).toString();
					String query = "SELECT * FROM Employees where employee_id = ?";
					PreparedStatement statement = connection.prepareStatement(query);
					statement.setString(1, employeeid);
					ResultSet resultset = statement.executeQuery();
					
					while(resultset.next()){
						firstName.setText(resultset.getString("first_name"));
						lastName.setText(resultset.getString("last_name"));
						phone.setText(resultset.getString("phone_number"));
						adress.setText(resultset.getString("address"));
						salary.setText(resultset.getString("salary"));
						taskId.setText(resultset.getString("task_id"));
						employeeId.setText(resultset.getString("employee_id"));	
						
					}
					
					statement.close();
					resultset.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(table);
		table.setBackground(new Color(255, 255, 240));
		table.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFirstName.setBounds(24, 92, 72, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLastName.setBounds(24, 120, 72, 14);
		contentPane.add(lblLastName);
		
		JLabel lblPhoneNumber = new JLabel("Phone number");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPhoneNumber.setBounds(24, 145, 89, 14);
		contentPane.add(lblPhoneNumber);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAddress.setBounds(24, 170, 72, 14);
		contentPane.add(lblAddress);
		
		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSalary.setBounds(24, 195, 72, 14);
		contentPane.add(lblSalary);
		
		JLabel lblTaskId = new JLabel("Task ID");
		lblTaskId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTaskId.setBounds(24, 220, 72, 14);
		contentPane.add(lblTaskId);
		
		firstName = new JTextField();
		firstName.setColumns(10);
		firstName.setBounds(120, 89, 107, 20);
		contentPane.add(firstName);
		
		lastName = new JTextField();
		lastName.setColumns(10);
		lastName.setBounds(120, 117, 107, 20);
		contentPane.add(lastName);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(120, 142, 107, 20);
		contentPane.add(phone);
		
		adress = new JTextField();
		adress.setColumns(10);
		adress.setBounds(120, 167, 107, 20);
		contentPane.add(adress);
		
		salary = new JTextField();
		salary.setColumns(10);
		salary.setBounds(120, 192, 107, 20);
		contentPane.add(salary);
		
		taskId = new JTextField();
		taskId.setColumns(10);
		taskId.setBounds(120, 217, 107, 20);
		contentPane.add(taskId);
		
		JButton button_2 = new JButton("Add");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "INSERT INTO Employees(first_name, last_name, phone_number, address, salary, task_id) VALUES (?,?,?,?,?,?)";
					PreparedStatement statement = connection.prepareStatement(query);
					statement.setString(1, firstName.getText());
					statement.setString(2, lastName.getText());
					statement.setString(3, phone.getText());
					statement.setString(4, adress.getText());
					statement.setString(5, salary.getText());
					statement.setString(6, taskId.getText());
					statement.execute();
					
					firstName.setText("");
					lastName.setText("");
					phone.setText("");
					adress.setText("");
					salary.setText("");
					taskId.setText("");
					employeeId.setText("");
			
					displayData();
					statement.close();

	
				} catch (Exception error) {
					error.printStackTrace();
				}
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_2.setBackground(new Color(238, 232, 170));
		button_2.setBounds(58, 263, 89, 23);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("Delete");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "DELETE FROM Employees where employee_id = '"+ employeeId.getText() + "'  ";
					PreparedStatement statement = connection.prepareStatement(query);
					statement.execute();
					employeeId.setText("");
					firstName.setText("");
					lastName.setText("");
					phone.setText("");
					adress.setText("");
					salary.setText("");
					taskId.setText("");
					
			
					displayData();
					statement.close();
				
	
				} catch (Exception error) {
					error.printStackTrace();
				}
			}
		});
		button_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_3.setBackground(new Color(238, 232, 170));
		button_3.setBounds(58, 297, 89, 23);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("Edit");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "Update Employees SET first_name = '" + firstName.getText() + "', last_name = '" + lastName.getText() + "', phone_number = '" + phone.getText() + "', address = '" + adress.getText() + "', salary = '"+ salary.getText() + "', task_id = '"+ taskId.getText() + "' where employee_id = '"+ employeeId.getText() + "'  ";
					PreparedStatement statement = connection.prepareStatement(query);
					statement.execute();
					employeeId.setText("");
					firstName.setText("");
					lastName.setText("");
					phone.setText("");
					adress.setText("");
					salary.setText("");
					taskId.setText("");
					
					displayData();
					statement.close();

	
				} catch (Exception error) {
					error.printStackTrace();
				}
			}
		});
		button_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_4.setBackground(new Color(238, 232, 170));
		button_4.setBounds(58, 331, 89, 23);
		contentPane.add(button_4);
		
		JLabel lblEmployeeId = new JLabel("Employee ID");
		lblEmployeeId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmployeeId.setBounds(24, 68, 72, 14);
		contentPane.add(lblEmployeeId);
		
		employeeId = new JTextField();
		employeeId.setColumns(10);
		employeeId.setBounds(120, 65, 107, 20);
		contentPane.add(employeeId);
		
		JButton btnTasksInfo = new JButton("Tasks info");
		btnTasksInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "SELECT employees.first_name, employees.last_name, tasks.task_id, tasks.type_of_task  FROM employees INNER JOIN tasks ON employees.task_id=tasks.task_id;";
					PreparedStatement statement = connection.prepareStatement(query);
					ResultSet resultset = statement.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(resultset));
					firstName.setText("");
					lastName.setText("");
					phone.setText("");
					adress.setText("");
					salary.setText("");
					taskId.setText("");
					employeeId.setText("");
					
					statement.close();
					resultset.close();
				} catch (Exception error) {
					error.printStackTrace();
				
				}
			
			}
		});
		btnTasksInfo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnTasksInfo.setBackground(new Color(238, 232, 170));
		btnTasksInfo.setBounds(482, 393, 96, 23);
		contentPane.add(btnTasksInfo);
		
		JButton btnEmployeeInfo = new JButton("Employee info");
		btnEmployeeInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstName.setText("");
				lastName.setText("");
				phone.setText("");
				adress.setText("");
				salary.setText("");
				taskId.setText("");
				employeeId.setText("");
				displayData();
			}
		});
		btnEmployeeInfo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEmployeeInfo.setBackground(new Color(238, 232, 170));
		btnEmployeeInfo.setBounds(341, 393, 119, 23);
		contentPane.add(btnEmployeeInfo);
	}

}
