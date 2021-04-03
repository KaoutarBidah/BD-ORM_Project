import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

public class Clients extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	Connection cn=null;
	PreparedStatement prepared=null;
	ResultSet resultat=null;
	private String nom=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clients frame = new Clients();
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
	public Clients() {
		setTitle("Gestion des clients");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 783, 375);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("CheckBox.light"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cn=ConnexionMysql.ConnectDB();
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(45, 31, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Menu Menu = new Menu();
				Menu.setVisible(true);
				Menu.setLocationRelativeTo(null);
				dispose();
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\USER\\Downloads\\back-arrow_icon-icons.com_72866.png"));
		lblNewLabel_1.setBounds(0, 0, 46, 34);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nom Client");
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_2.setBounds(25, 57, 86, 28);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(121, 61, 140, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Adresse");
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_3.setBounds(25, 97, 66, 28);
		contentPane.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(121, 100, 140, 24);
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
		btnNewButton.setBounds(22, 197, 103, 28);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Ajouter");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String req="insert into client(nomClient,adresse) values(?,?)";
				try {
					prepared=cn.prepareStatement(req);
					prepared.setString(1,textField.getText().toString());
					prepared.setString(2,textField_1.getText().toString());
					if(!textField.getText().equals("") && !textField_1.getText().equals("") ) {
						prepared.execute();
						JOptionPane.showMessageDialog(null,"L'ajout est effectué  avec sucées");
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
		btnNewButton_1.setBounds(171, 197, 103, 28);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Supprimer");
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String req="delete from client where nomClient=? and adresse=?";
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
		btnNewButton_2.setBackground(new Color(139, 0, 0));
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_2.setBounds(22, 262, 103, 28);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Modifier");
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String req="update client set  nomClient=? , adresse=? where nomClient= '"+nom+"'";
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
		btnNewButton_3.setBackground(new Color(139, 0, 0));
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_3.setBounds(174, 262, 100, 28);
		contentPane.add(btnNewButton_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(397, 56, 336, 245);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ligne =table.getSelectedRow();
				nom=table.getModel().getValueAt(ligne,0).toString();
				String adr=table.getModel().getValueAt(ligne,1).toString();
				textField.setText(nom);
				textField_1.setText(adr);
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_4 = new JLabel("Table des Clients");
		lblNewLabel_4.setForeground(new Color(0, 0, 0));
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_4.setBounds(395, 31, 103, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Table();
			}
		});
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\USER\\Downloads\\refresh_icon-icons.com_50052.png"));
		lblNewLabel_5.setBounds(687, 23, 46, 34);
		contentPane.add(lblNewLabel_5);
	}
	
	public void Table() {
		String req="select nomClient,adresse from client";
		try {
			prepared=cn.prepareStatement(req);
			resultat=prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}
