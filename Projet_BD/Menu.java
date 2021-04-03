import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 796, 418);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("CheckBox.light"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Trafic Ferroviaire");
		lblNewLabel.setForeground(new Color(139, 0, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(289, 11, 178, 31);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utilisateurs user = new Utilisateurs();
				user.setVisible(true);
				user.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\USER\\Pictures\\multiple-users.png"));
		btnNewButton.setBounds(21, 98, 207, 180);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Gestion des utilisateurs");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Utilisateurs user = new Utilisateurs();
				user.setVisible(true);
				user.setLocationRelativeTo(null);
				dispose();
			}
		});
		lblNewLabel_1.setForeground(new Color(139, 0, 0));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_1.setBounds(44, 289, 164, 29);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clients clt = new Clients();
				clt.setVisible(true);
				clt.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\USER\\Pictures\\client.png"));
		btnNewButton_1.setBounds(553, 98, 200, 180);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("Gestion des clients");
		lblNewLabel_2.setForeground(new Color(139, 0, 0));
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Clients clt = new Clients();
				clt.setVisible(true);
				clt.setLocationRelativeTo(null);
				dispose();
			}
		});
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_2.setBounds(589, 297, 164, 21);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Marchandises mar = new Marchandises();
				mar.setVisible(true);
				mar.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\USER\\Pictures\\t\u00E9l\u00E9chargement.jfif"));
		btnNewButton_2.setBounds(288, 98, 207, 180);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_3 = new JLabel("Gestion des marchandises");
		lblNewLabel_3.setForeground(new Color(139, 0, 0));
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Marchandises mar = new Marchandises();
				mar.setVisible(true);
				mar.setLocationRelativeTo(null);
				dispose();
			}
		});
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_3.setBounds(309, 293, 173, 21);
		contentPane.add(lblNewLabel_3);
	}
}
