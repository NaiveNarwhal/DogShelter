
 import java.awt.BorderLayout;
 import java.sql.*;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;

public class MainView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Connection connection;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
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
	public MainView() {
		connection = MysqlConnection.databaseConnect();
		setBackground(new Color(220, 220, 220));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 477);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("DogShelter Management");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 25));
		lblNewLabel.setBounds(236, 89, 361, 34);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.setBounds(371, 11, 76, 93);
		label.setIcon(new ImageIcon(getClass().getResource("/dog_man.png")));	
		contentPane.add(label);
		
		JButton btnNewButton = new JButton("Dogs");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Dog dogs = new Dog();
				dogs.setVisible(true);
				dogs.displayData();
			}
		});
		btnNewButton.setBackground(new Color(238, 232, 170));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(99, 199, 101, 23);
		contentPane.add(btnNewButton);
		
		JButton btnEmployees = new JButton("Employees");
		btnEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Employees employees = new Employees();
				employees.setVisible(true);
				employees.displayData();
				
			}
		});
	
		btnEmployees.setBackground(new Color(238, 232, 170));
		btnEmployees.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEmployees.setBounds(156, 328, 101, 23);
		contentPane.add(btnEmployees);
		
		JButton btnTasks = new JButton("Tasks");
		btnTasks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Tasks task = new Tasks();
				task.setVisible(true);
				task.displayData();
			}
		});
		btnTasks.setBackground(new Color(238, 232, 170));
		btnTasks.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnTasks.setBounds(315, 328, 104, 23);
		contentPane.add(btnTasks);
		
		JButton btnNewButton_1 = new JButton("Medical data");
		btnNewButton_1.setBackground(new Color(238, 232, 170));
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				MedicalData med = new MedicalData();
				med.setVisible(true);
				med.displayData();
			}
		});
		btnNewButton_1.setBounds(414, 199, 110, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnAdoptedDogs = new JButton("Dogs Arrival");
		btnAdoptedDogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				DogsArrival dogsar = new DogsArrival();
				dogsar.setVisible(true);
				dogsar.displayData();
			}
		});
		btnAdoptedDogs.setBackground(new Color(238, 232, 170));
		btnAdoptedDogs.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAdoptedDogs.setBounds(250, 199, 116, 23);
		contentPane.add(btnAdoptedDogs);
		
		JButton btnDogsLocation = new JButton("Dogs location");
		btnDogsLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				DogsLocation dogsloc = new DogsLocation();
				dogsloc.setVisible(true);
				dogsloc.displayData();
			}
		});
		btnDogsLocation.setBackground(new Color(238, 232, 170));
		btnDogsLocation.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDogsLocation.setBounds(577, 199, 116, 23);
		contentPane.add(btnDogsLocation);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(128, 154, 43, 45);
		label_2.setIcon(new ImageIcon(getClass().getResource("/sitting-dog.png")));	
		contentPane.add(label_2);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(350, 295, 43, 26);
		label_1.setIcon(new ImageIcon(getClass().getResource("/feeding-a-dog.png")));	
		contentPane.add(label_1);
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(504, 287, 43, 34);
		label_3.setIcon(new ImageIcon(getClass().getResource("/family.png")));	
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.setBounds(188, 295, 43, 26);
		label_4.setIcon(new ImageIcon(getClass().getResource("/dog-seatting.png")));	
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setBounds(453, 167, 32, 32);
		label_5.setIcon(new ImageIcon(getClass().getResource("/heart.png")));	
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("");
		label_6.setBounds(617, 167, 32, 26);
		label_6.setIcon(new ImageIcon(getClass().getResource("/pet-house.png")));	
		contentPane.add(label_6);
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				LogView login = new LogView();
				login.setVisible(true);
			}
		});
		btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogOut.setBackground(new Color(238, 232, 170));
		btnLogOut.setBounds(687, 11, 76, 23);
		contentPane.add(btnLogOut);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(414, 199, 5, 5);
		contentPane.add(tabbedPane);
		
		JButton btnAdoptedDogs_1 = new JButton("Adopted dogs");
		btnAdoptedDogs_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdoptedDogs adopt = new AdoptedDogs();
				adopt.setVisible(true);
				adopt.displayData();
			}
		});
		btnAdoptedDogs_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAdoptedDogs_1.setBackground(new Color(238, 232, 170));
		btnAdoptedDogs_1.setBounds(468, 328, 116, 23);
		contentPane.add(btnAdoptedDogs_1);
		
		JLabel l = new JLabel("");
		l.setBounds(299, 162, 24, 37);
		l.setIcon(new ImageIcon(getClass().getResource("/paw-print-.png")));
		contentPane.add(l);
	}
}
