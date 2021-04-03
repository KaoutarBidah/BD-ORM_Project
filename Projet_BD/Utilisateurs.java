import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class Utilisateurs extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	Connection cn=null;
	PreparedStatement prepared=null;
	ResultSet resultat=null;
	private JTable table;
	private String user=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Utilisateurs frame = new Utilisateurs();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Utilisateurs() {
		setTitle("Gestion des utilisateurs");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 671, 363);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("CheckBox.light"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cn=ConnexionMysql.ConnectDB();
		
		JLabel lblNewLabel = new JLabel("Utilisateur");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 71, 115, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mot de passe");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 118, 112, 18);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(111, 74, 174, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(111, 117, 174, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Nouveau");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
			}
		});
		btnNewButton.setBackground(new Color(139, 0, 0));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnNewButton.setBounds(10, 179, 115, 34);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Ajouter");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String req="insert into utilisateurs(username,password) values(?,?)";
				try {
					prepared=cn.prepareStatement(req);
					prepared.setString(1,textField.getText().toString());
					prepared.setString(2,textField_1.getText().toString());
					if(!textField.getText().equals("") && !textField_1.getText().equals("") ) {
						prepared.execute();
						JOptionPane.showMessageDialog(null,"L'utilisateur est inséré avec sucées");
						Table();	
					}else {
						JOptionPane.showMessageDialog(null, "Remplissez les champs vides");
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBackground(new Color(139, 0, 0));
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_1.setBounds(180, 179, 115, 34);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Supprimer");
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String req="delete from utilisateurs where username=? and password=?";
				try {
					prepared=cn.prepareStatement(req);
					prepared.setString(1,textField.getText().toString());
					prepared.setString(2,textField_1.getText().toString());
					if(!textField.getText().equals("") && !textField_1.getText().equals("") ) {
						prepared.execute();
						JOptionPane.showMessageDialog(null,"La suppression est effectuée avec sucées");
						Table();	
					}else {
						JOptionPane.showMessageDialog(null, "Remplissez les champs vides");
					}
					textField.setText("");
					textField_1.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
	
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_2.setBackground(new Color(139, 0, 0));
		btnNewButton_2.setBounds(10, 249, 116, 34);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Modifier");
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String req="update utilisateurs set  username=? , password=? where username= '"+user+"'";
				try {
					prepared=cn.prepareStatement(req);
					prepared.setString(1,textField.getText().toString());
					prepared.setString(2,textField_1.getText().toString());
					if(!textField.getText().equals("") && !textField_1.getText().equals("") ) {
						prepared.execute();
						JOptionPane.showMessageDialog(null,"La modification est effectuée avec sucées");
						Table();	
					}else {
						JOptionPane.showMessageDialog(null, "Remplissez les champs vides");
					}
					textField.setText("");
					textField_1.setText("");
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_3.setBackground(new Color(139, 0, 0));
		btnNewButton_3.setBounds(180, 249, 116, 34);
		contentPane.add(btnNewButton_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(346, 48, 275, 235);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int ligne =table.getSelectedRow();
				user=table.getModel().getValueAt(ligne,0).toString();
				String password=table.getModel().getValueAt(ligne,1).toString();
				textField.setText(user);
				textField_1.setText(password);
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\USER\\Downloads\\refresh_icon-icons.com_50052.png"));
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Table();
			}
		});
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_2.setBounds(587, 11, 34, 26);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Table des utilisateurs");
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_3.setBounds(346, 23, 115, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Menu Menu = new Menu();
				Menu.setVisible(true);
				Menu.setLocationRelativeTo(null);
				dispose();
			}
		});
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\USER\\Downloads\\back-arrow_icon-icons.com_72866.png"));
		lblNewLabel_4.setBounds(0, 0, 46, 37);
		contentPane.add(lblNewLabel_4);
	}
	
	public void Table() {
		String req="select username,password from utilisateurs";
		try {
			prepared=cn.prepareStatement(req);
			resultat=prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}
