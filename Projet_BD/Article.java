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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.UIManager;

public class Article extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private String codeArticle;
	Connection cn=null;
	PreparedStatement prepared=null;
	ResultSet resultat=null;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Article frame = new Article();
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
	public Article() {
		setTitle("Article");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 756, 399);
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
				Marchandises M = new Marchandises();
				M.setVisible(true);
				M.setLocationRelativeTo(null);
				dispose();
			}
		});
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\USER\\Downloads\\back-arrow_icon-icons.com_72866.png"));
		lblNewLabel.setBounds(0, 0, 46, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nature Article");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1.setBounds(25, 126, 123, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre d'unit\u00E9s");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_2.setBounds(30, 166, 118, 18);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(158, 126, 112, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(158, 166, 112, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(307, 67, 406, 282);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ligne =table.getSelectedRow();
				codeArticle=table.getModel().getValueAt(ligne,0).toString();
				String codeM=table.getModel().getValueAt(ligne,1).toString();
				String natureArticle=table.getModel().getValueAt(ligne,2).toString();
				String nbrU=table.getModel().getValueAt(ligne,3).toString();
				textField.setText(natureArticle);
				textField_1.setText(nbrU);
				textField_3.setText(codeM);
				textField_4.setText(codeArticle);
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Table();
			}
		});
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\USER\\Downloads\\refresh_icon-icons.com_50052.png"));
		lblNewLabel_3.setBounds(684, 31, 46, 25);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Table des articles");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_4.setBounds(307, 31, 129, 25);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Nouveau");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_3.setText("");
				textField_4.setText("");
			}
		});
		btnNewButton.setBackground(new Color(139, 0, 0));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnNewButton.setBounds(25, 212, 112, 35);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Ajouter");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String req="insert into article(codeArticle,codeMarchandise,natureArticle,nombreUnites)values(?,?,?,?)";
				try {
					prepared=cn.prepareStatement(req);
					prepared.setString(1,textField_4.getText().toString());
					prepared.setString(2,textField_3.getText().toString());
					prepared.setString(3,textField.getText().toString());
					prepared.setString(4,textField_1.getText().toString());
					if(!textField.getText().equals("") && !textField_1.getText().equals("") &&!textField_3.getText().equals("") && !textField_4.getText().equals("") ) {
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
		btnNewButton_1.setBounds(175, 212, 112, 35);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Supprimer");
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String req="delete from article where codeArticle=?";
				try {
					prepared=cn.prepareStatement(req);
					prepared.setString(1,textField_4.getText().toString());
					if(!textField.getText().equals("") && !textField_1.getText().equals("") && !textField_3.getText().equals("") && !textField_4.getText().equals("") ) {
						prepared.execute();
						JOptionPane.showMessageDialog(null,"La suppression est effectuée avec sucées");
						Table();	
					}else {
						JOptionPane.showMessageDialog(null, "Remplissez les champs vides");
					}
					textField.setText("");
					textField_1.setText("");
					textField_3.setText("");
					textField_4.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBackground(new Color(139, 0, 0));
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_2.setBounds(25, 292, 112, 35);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Modifier");
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String req="update article set  natureArticle=? , nombreUnites=? where codeArticle = '"+codeArticle+"'";
				try {
					prepared=cn.prepareStatement(req);
					prepared.setString(1,textField.getText().toString());
					prepared.setString(2,textField_1.getText().toString());
					if(!textField.getText().equals("") && !textField_1.getText().equals("") && !textField_3.getText().equals("") && !textField_4.getText().equals("") ) {
						prepared.execute();
						JOptionPane.showMessageDialog(null,"La modification est effectuée avec sucées");
						Table();	
					}else {
						JOptionPane.showMessageDialog(null, "Remplissez les champs vides");
					}
					textField.setText("");
					textField_1.setText("");
					textField_3.setText("");
					textField_4.setText("");
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBackground(new Color(139, 0, 0));
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		btnNewButton_3.setBounds(175, 292, 112, 35);
		contentPane.add(btnNewButton_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(414, 31, 109, 25);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Search();
			
			}
		});
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\USER\\Downloads\\search_find_magnificglasses_23318.png"));
		lblNewLabel_5.setBounds(533, 22, 46, 45);
		contentPane.add(lblNewLabel_5);
		
		textField_3 = new JTextField();
		textField_3.setBounds(158, 82, 112, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(158, 47, 112, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Code Marchandise");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(25, 85, 136, 18);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Code Article");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1_2.setBounds(25, 49, 123, 18);
		contentPane.add(lblNewLabel_1_2);
	}
	public void Table() {
		String req="select codeArticle,codeMarchandise,natureArticle,nombreUnites from article";
		try {
			prepared=cn.prepareStatement(req);
			resultat=prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Search() {
		String req="select codeArticle,codeMarchandise,natureArticle,nombreUnites from article where codeMarchandise=?";
		try {
			prepared=cn.prepareStatement(req);
			prepared.setString(1,textField_2.getText().toString());
			resultat=prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
