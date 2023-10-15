import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//cette fenetre nous donne le choix d'afficher touts les details qu'on veut par ex les commandes satisfaites etc...
//il suffit juste de cliquer sur le button qu'on veut s'avoir ses details


public class affichage extends JFrame {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					affichage frame = new affichage();
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
	public affichage() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 601, 412);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("AFFICHAGE");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(95, 11, 395, 86);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("SATISFAITES");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comsatisfaites obj=new comsatisfaites();
				obj.setVisible(true);
			}
		});
		btnNewButton.setBounds(28, 108, 159, 37);
		contentPane.add(btnNewButton);
		
		JButton btnModifier = new JButton("EN INSTANCE");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comeninstance obj=new comeninstance();
				obj.setVisible(true);
			}
		});
		btnModifier.setBounds(28, 174, 159, 37);
		contentPane.add(btnModifier);
		
		JButton btnSupprimer = new JButton("PRODUIT PLUS VENDU");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				produitplus obj=new produitplus();
				obj.setVisible(true);
			}
		});
		btnSupprimer.setBounds(196, 239, 192, 37);
		contentPane.add(btnSupprimer);
		
		JButton btnAfficher = new JButton("AFFICHER");
		btnAfficher.setBounds(141, 372, 112, 37);
		contentPane.add(btnAfficher);
		
		JButton btnQuitter = new JButton("QUITTER");
		btnQuitter.setBounds(28, 491, 112, 37);
		contentPane.add(btnQuitter);
		
		JButton btnAchatsProduit = new JButton("ACHATS PRODUIT");
		btnAchatsProduit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				achatsproduit obj=new achatsproduit();
				obj.setVisible(true);
			}
		});
		btnAchatsProduit.setBounds(369, 108, 159, 37);
		contentPane.add(btnAchatsProduit);
		
		JButton btnNewButton_2 = new JButton("ACHATS PAR VILLE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				achatsville obj=new achatsville();
				obj.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(369, 174, 159, 37);
		contentPane.add(btnNewButton_2);
		
		JButton btnProduitMoin = new JButton("PRODUIT MOINS VENDU");
		btnProduitMoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				produitmoins obj=new produitmoins();
				obj.setVisible(true);
			}
		});
		btnProduitMoin.setBounds(196, 309, 192, 37);
		contentPane.add(btnProduitMoin);
	}

}
