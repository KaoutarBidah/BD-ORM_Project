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
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.UIManager;

public class Marchandises extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	JTextField textField_1;
	private JTable table;
	Connection cn=null;
	PreparedStatement prepared=null;
	ResultSet resultat=null;
	String numClt=null;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Marchandises frame = new Marchandises();
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
	public Marchandises() {
		setTitle("Gestion des marchandises");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 747, 335);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("CheckBox.light"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cn=ConnexionMysql.ConnectDB();
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Menu Menu = new Menu();
				Menu.setVisible(true);
				Menu.setLocationRelativeTo(null);
				dispose();
			}
		});
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\USER\\Downloads\\back-arrow_icon-icons.com_72866.png"));
		lblNewLabel.setBounds(0, 0, 46, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Num\u00E9ro de client ");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1.setBounds(35, 105, 130, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Code Marchandise");
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_2.setBounds(35, 62, 130, 18);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(175, 62, 111, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(175, 105, 111, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(350, 64, 371, 191);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ligne =table.getSelectedRow();
				numClt=table.getModel().getValueAt(ligne,0).toString();
				String codeM=table.getModel().getValueAt(ligne,1).toString();
				textField.setText(numClt);
				textField_1.setText(codeM);
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Table();
			}
		});
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\USER\\Downloads\\refresh_icon-icons.com_50052.png"));
		lblNewLabel_5.setBounds(685, 22, 46, 31);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Table des marchandises");
		lblNewLabel_6.setForeground(new Color(0, 0, 0));
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_6.setBounds(350, 38, 130, 15);
		contentPane.add(lblNewLabel_6);
		
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
		btnNewButton.setBounds(23, 178, 108, 32);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Ajouter");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String req="insert into marchandise values(?,?)";
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
		btnNewButton_1.setBounds(178, 178, 108, 32);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Supprimer");
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String req="delete from marchandise where codeMarchandise=?";
				try {
					prepared=cn.prepareStatement(req);
					prepared.setString(1,textField.getText().toString());
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
		btnNewButton_2.setBounds(98, 235, 108, 32);
		contentPane.add(btnNewButton_2);
		
		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String req="select * from marchandise where numClient=?";
				try {
					prepared=cn.prepareStatement(req);
					prepared.setString(1,textField_2.getText());
					resultat=prepared.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(resultat));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		textField_2.setBounds(490, 33, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Voir Articles");
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Article A = new Article();
				A.setVisible(true);
				A.setLocationRelativeTo(null);
				dispose();
			}
		});
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 11));
		lblNewLabel_3.setBounds(558, 266, 57, 19);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Article A = new Article();
				A.setVisible(true);
				A.setLocationRelativeTo(null);
				dispose();
			}
		});
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\USER\\Downloads\\play_arrow_icon_153371.png"));
		lblNewLabel_4.setBounds(610, 266, 46, 19);
		contentPane.add(lblNewLabel_4);
	}
	
	
	public void Table() {
		String req="select * from marchandise order by codeMarchandise desc";
		try {
			prepared=cn.prepareStatement(req);
			resultat=prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
