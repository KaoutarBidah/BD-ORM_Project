import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.UIManager;

public class IndicationPassword extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
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
					IndicationPassword frame = new IndicationPassword();
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
	public IndicationPassword() {
		setTitle("R\u00E9cup\u00E9ration du mot de passe");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 553, 264);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("CheckBox.light"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		cn=ConnexionMysql.ConnectDB();
		
		JLabel lblNewLabel = new JLabel("Utilisateur");
		lblNewLabel.setForeground(new Color(165, 42, 42));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(89, 75, 78, 17);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String username=textField.getText().toString();
				String sql="select password from utilisateurs where username like ? ";
				try {
					
					prepared=cn.prepareStatement(sql);
					prepared.setString(1, username);
					resultat =prepared.executeQuery();
					if (resultat.next()) {
					String pass= resultat.getString("password");
					String pass1 =pass.substring(0,2);
					textField_1.setText("les 2 premieres nombres du mot de passe sont :"+pass1+"****");
					}
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		textField.setBounds(192, 71, 168, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		textField_1.setEnabled(false);
		textField_1.setEditable(false);
		textField_1.setBounds(45, 131, 430, 68);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Entrez votre nom d'utilisateur pour r\u00E9cup\u00E9rer votre mot de passe..");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(45, 21, 445, 26);
		contentPane.add(lblNewLabel_1);
	}
}
