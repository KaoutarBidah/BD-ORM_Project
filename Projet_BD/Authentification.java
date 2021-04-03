import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Authentification extends JFrame {

	private JFrame frmAuthentification;
	private JTextField textField;
	private JPasswordField passwordField;
	
	Connection cn=null;
	PreparedStatement prepared=null;
	ResultSet resultat=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Authentification window = new Authentification();
					window.frmAuthentification.setVisible(true);
					window.frmAuthentification.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Authentification() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAuthentification = new JFrame();
		frmAuthentification.getContentPane().setBackground(Color.WHITE);
		frmAuthentification.getContentPane().setLayout(null);
		cn=ConnexionMysql.ConnectDB();
		
		JLabel lblNewLabel = new JLabel("Veuillez Se Connecter Pour Continuer...");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(44, 28, 538, 58);
		frmAuthentification.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Utilisateur");
		lblNewLabel_1.setForeground(new Color(139, 0, 0));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1.setBounds(44, 140, 83, 31);
		frmAuthentification.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mot de passe");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_2.setForeground(new Color(139, 0, 0));
		lblNewLabel_2.setBounds(42, 182, 102, 31);
		frmAuthentification.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(152, 145, 175, 24);
		frmAuthentification.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Mot de passe oubli\u00E9 !?");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				IndicationPassword obj = new IndicationPassword();
				obj.setVisible(true);
				obj.setLocationRelativeTo(null);
			}
		});
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblNewLabel_3.setForeground(new Color(0, 0, 255));
		lblNewLabel_3.setBounds(180, 224, 125, 15);
		frmAuthentification.getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("Se Connecter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username=textField.getText().toString();
				
				String password=passwordField.getText().toString();
				
				String sql="select username,password from utilisateurs";
				try {
					prepared=cn.prepareStatement(sql);
					resultat=prepared.executeQuery();
					int i=0;
					if(username.equals("") || password.equals("")) {
						JOptionPane.showMessageDialog(null, "Remplissez les champs vides");
					}else {
					while(resultat.next()){
						String username1=resultat.getString("username");
						String password1=resultat.getString("password");
						System.out.println(username1 + password);
						if(username1.equalsIgnoreCase(username) && password1.equalsIgnoreCase(password)) {
							JOptionPane.showMessageDialog(null,"Connexion Reussite");
							i=1;
							Menu Menu = new Menu();
							Menu.setVisible(true);
							Menu.setLocationRelativeTo(null);
							frmAuthentification.dispose();
						}
					}
					if(i==0)
					JOptionPane.showMessageDialog(null,"Connexion echouée,Informations incorrects");
					}
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					   ex.printStackTrace();
				}
			}
		});
		btnNewButton.setBackground(new Color(139, 0, 0));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(152, 273, 164, 31);
		frmAuthentification.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\USER\\Pictures\\t\u00E9l\u00E9chargement.png"));
		lblNewLabel_4.setBounds(363, 81, 224, 227);
		frmAuthentification.getContentPane().add(lblNewLabel_4);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(154, 189, 173, 24);
		frmAuthentification.getContentPane().add(passwordField);
		frmAuthentification.setTitle("Authentification");
		frmAuthentification.setBounds(100, 100, 629, 373);
		frmAuthentification.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
