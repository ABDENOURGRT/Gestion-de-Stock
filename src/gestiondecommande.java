import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class gestiondecommande extends JFrame {
	DataBasesCnct DB=new DataBasesCnct("tpPOO", "tpPOO");
	private JPanel contentPane;
	private JTable tablecom;
    void fermer() {
    	dispose();
    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gestiondecommande frame = new gestiondecommande();
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
	public gestiondecommande() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("GESTION DES COMMANDES");
		lblNewLabel.setBounds(299, 11, 395, 86);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(479, 138, 435, 341);
		contentPane.add(scrollPane);
		
		tablecom = new JTable();
		scrollPane.setViewportView(tablecom);
		
		JLabel lblNewLabel_1 = new JLabel("LA LISTE DES COMMANDES");
		lblNewLabel_1.setBounds(579, 93, 241, 34);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("AJOUTER");
		btnNewButton.setBounds(141, 159, 112, 37);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajoutcom obj=new ajoutcom();
				obj.setVisible(true);			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnModifier = new JButton("MODIFIER");
		btnModifier.setBounds(141, 229, 112, 37);
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifiercom obj=new modifiercom();
				obj.setVisible(true);
			}
		});
		contentPane.add(btnModifier);
		
		JButton btnSupprimer = new JButton("SUPPRIMER");
		btnSupprimer.setBounds(141, 301, 112, 37);
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suppcom obj=new suppcom();
				obj.setVisible(true);
			}
		});
		contentPane.add(btnSupprimer);
		
		JButton btnAfficher = new JButton("AFFICHER");
		btnAfficher.setBounds(141, 372, 112, 37);
		btnAfficher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				affichage obj=new affichage();
				obj.setVisible(true);
			}
		});
		contentPane.add(btnAfficher);
		
		JButton btnQuitter = new JButton("QUITTER");
		btnQuitter.setBounds(28, 491, 112, 37);
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame=new JFrame("EXIT");
				if(JOptionPane.showConfirmDialog(frame,"confirmer si vous voulez quitter","EXIT",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		contentPane.add(btnQuitter);
		
		JButton btnNewButton_1 = new JButton("Actualiser");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//ce button permet d'afficher la table commande
				//il calcule le montant total d'une commande 
				
				try {
				
					Statement stm=DB.con.createStatement();
					String w="select id_com from commande ";
					ResultSet r=stm.executeQuery(w);
					while(r.next()) {
						int a=r.getInt("id_com");//recuperer chaque id_com d'une commande de la table commande
						int som=0;
						try {
							String x="select sum(montant) as total from details_commande where id_com=? ";
							PreparedStatement pk = DB.con.prepareStatement(x); 
							pk.setInt(1, a);
							ResultSet t=pk.executeQuery();
							while(t.next()) {
								som=t.getInt("total");//calculer la somme des montants de la table details_commande d'une commande
							}
							try {
								String n="update commande set montant=? where id_com=? ";
								PreparedStatement ph = DB.con.prepareStatement(n); 
								ph.setInt(1, som);
								ph.setInt(2, a);//metre à jour le champ montant dans la table commande apres le calcule du montant total
								ph.executeQuery();
							}catch (SQLException e1) {
						        e1.printStackTrace();
					         }
						}catch (SQLException e1) {
					        e1.printStackTrace();
				         }
						} 
					}catch (SQLException e1) {
				        e1.printStackTrace();
			         }
				updatetablecom();
			}
		});
		btnNewButton_1.setBounds(480, 115, 105, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnDetails = new JButton("DETAILS");
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				detailscom obj=new detailscom();
				obj.setVisible(true);
			}
		});
		btnDetails.setBounds(649, 498, 112, 37);
		contentPane.add(btnDetails);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fermer();
				Menu obj=new Menu();
				obj.frame.setVisible(true);
				obj.frame.setLocationRelativeTo(null);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\GARTI Abdenour\\Desktop\\poo\\retour.png"));
		btnNewButton_2.setBackground(new Color(240, 240, 240));
		btnNewButton_2.setBounds(22, 11, 51, 46);
		contentPane.add(btnNewButton_2);
	}
	public void updatetablecom() {
		
		//cette procedure permet d'afficher la table commande
		
		String s="select * from commande";
		try {
			Statement stmt=DB.con.createStatement();
			ResultSet rs=stmt.executeQuery(s);
		    tablecom.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(SQLException e1){ 
			e1.printStackTrace();
			
		}
	}
}
